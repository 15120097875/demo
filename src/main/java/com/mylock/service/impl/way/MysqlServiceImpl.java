package com.mylock.service.impl.way;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mylock.constant.Constant;
import com.mylock.dto.StoreDto;
import com.mylock.entity.Store;
import com.mylock.handler.CheckException;
import com.mylock.mapper.StoreMapper;
import com.mylock.service.AsyncService;
import com.mylock.service.StoreService;
import com.mylock.service.way.MysqlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class MysqlServiceImpl implements MysqlService {

    private final StoreMapper storeMapper;

    private final StoreService storeService;

    private StringRedisTemplate redisTemplate;

    /**
     * 购买商品 使用数据库行锁
     *
     * @param storeDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean sad(StoreDto storeDto) {
        Store store = storeMapper.selectById(storeDto.getPid());
        if (store == null || store.getNum() < 0) {
            throw new CheckException("库存不足");
        }
        storeMapper.updateNum(storeDto);

//        使用了事务，单机环境下线程安全，但是会阻塞其它线程，效率低，并且不能解决跨进程的问题
//        storeMapper.updateNum(storeDto);
//        if (storeMapper.selectById(storeDto.getPid()).getNum() < 0) {
//            throw new CheckException("库存不足");
//        }

        // 业务代码
        storeService.work();

        // 统计销售总数
        redisTemplate.opsForValue().increment("mysql-sad", storeDto.getDelta());
        return true;
    }

    /**
     * 购买商品 使用数据库乐观锁
     *
     * @param storeDto
     * @return
     */
    @Override
    public boolean happy(StoreDto storeDto) {
//         统计请求次数
        redisTemplate.opsForValue().increment("requestNum");

//        更新库存，因为更新和查询是两个操作，虽然不会超卖，但是并发下会有 有库存仍然购买失败的情况。
//        storeMapper.updateNum(storeDto);
//        if (storeMapper.selectById(storeDto.getPid()).getNum() < 0) {
//            // 回滚库存
//            storeDto.setDelta(-storeDto.getDelta());
//            storeMapper.updateNum(storeDto);
//            throw new CheckException("库存不足");
//        }

//        推荐的方案，使用到了cas的思想进行的更新
//        update store set num = num - #{delta} where pid = #{pid} and num - 1 >= #{delta}
        int update = storeMapper.updateNumCas(storeDto);
        if (update == 0) {
            throw new CheckException("库存不足");
        }

        // 业务代码
        storeService.work();

        // 统计销售总数
        redisTemplate.opsForValue().increment("mysql-happy", storeDto.getDelta());
        return true;
    }

}

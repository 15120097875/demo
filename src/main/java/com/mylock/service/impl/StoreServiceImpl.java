package com.mylock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mylock.dto.StoreDto;
import com.mylock.constant.Constant;
import com.mylock.entity.Store;
import com.mylock.handler.CheckException;
import com.mylock.mapper.StoreMapper;
import com.mylock.service.StoreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    private final StringRedisTemplate redisTemplate;

    private final StoreMapper storeMapper;

    /**
     * 购买商品
     *
     * @param storeDto
     * @return
     */
    @Override
    public boolean pay(StoreDto storeDto) {
//        Store store = storeMapper.selectById(storeDto.getPid());
//        store.setNum(store.getNum() - storeDto.getDelta());
//        if (store.getNum() < 0) {
//            throw new CheckException("库存不足");
//        }
//        storeMapper.updateById(store);

// --------------------------

        // 虽然更新库存的数量应用了数据库的行锁，但是查询和更新是两个操作，仍然会有超卖问题
        Store store = storeMapper.selectById(storeDto.getPid());
        if (store.getNum() - storeDto.getDelta() < 0) {
            throw new CheckException("库存不足");
        }
        storeMapper.updateNum(storeDto);

// --------------------------

        // 执行业务代码
        this.work();

        // 统计销售总数
        redisTemplate.opsForValue().increment("nolock", storeDto.getDelta());
        return true;
    }

    /**
     * 模拟业务代码
     * @param millis 毫秒值
     */
    @Override
    public void work(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void work() {
        work(0);
    }

    /**
     * 初始化库存
     */
    @Override
    public boolean init(Store store) {
        log.info("初始化库存");
        redisTemplate.opsForValue().set(Constant.productKey(store.getPid()), String.valueOf(store.getNum()), 24, TimeUnit.HOURS);
        if (storeMapper.updateById(store) == 0) {
            storeMapper.insert(store);
        }
        return true;
    }

}

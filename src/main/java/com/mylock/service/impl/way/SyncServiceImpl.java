package com.mylock.service.impl.way;

import com.mylock.dto.StoreDto;
import com.mylock.entity.Store;
import com.mylock.handler.CheckException;
import com.mylock.mapper.StoreMapper;
import com.mylock.service.StoreService;
import com.mylock.service.way.SyncService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SyncServiceImpl implements SyncService {

    private final StringRedisTemplate redisTemplate;

    private final StoreMapper storeMapper;

    private final StoreService storeService;

    /**
     * 购买商品
     *
     * @param storeDto
     * @return
     */
    @Override
    public boolean pay(StoreDto storeDto) {
        // 使用同步代码块实现
        synchronized (this) {
            log.info("同步代码块方式支付：{}", storeDto);
            Store store = storeMapper.selectById(storeDto.getPid());
            if (store.getNum() - storeDto.getDelta() < 0) {
                throw new CheckException("库存不足");
            }
            storeMapper.updateNum(storeDto);

            // 业务代码
            storeService.work();

            redisTemplate.opsForValue().increment("sync", storeDto.getDelta());
        }
        return true;
    }

}

package com.mylock.service.impl.way;

import com.mylock.constant.Constant;
import com.mylock.dto.StoreDto;
import com.mylock.handler.CheckException;
import com.mylock.service.AsyncService;
import com.mylock.service.StoreService;
import com.mylock.service.way.RedisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final StringRedisTemplate redisTemplate;

    private final AsyncService asyncService;

    private final StoreService storeService;

    /**
     * 购买商品
     *
     * @param storeDto
     * @return
     */
    @Override
    public boolean pay(StoreDto storeDto) {
        log.info("开始支付：{}", storeDto);
        String key = Constant.productKey(storeDto.getPid());
        // 首先扣减redis的库存
        // 校验
        Boolean exists = redisTemplate.hasKey(key);
        if (exists == null || !exists) {
            throw new CheckException("库存不足");
        }
        Long increment = redisTemplate.opsForValue().increment(key, -storeDto.getDelta());
        if (increment == null || increment < 0) {
            redisTemplate.opsForValue().increment(key, storeDto.getDelta());
            throw new CheckException("库存不足");
        }

        // 业务代码 生成订单，调用支付接口，支付成功。。。
        storeService.work();

        // 异步更新库存
        asyncService.updateNum(storeDto);

        // 统计销售总数
        redisTemplate.opsForValue().increment("redis", storeDto.getDelta());
        return true;
    }

}

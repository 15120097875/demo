package com.mylock.controller;

import com.mylock.handler.CheckException;
import com.mylock.util.R;
import lombok.AllArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redission")
@AllArgsConstructor
public class RedissionController {

    /**
     * redission
     *
     */
    @GetMapping("/demo")
    public R<?> ide() throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setLockWatchdogTimeout(30000);
        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("redisLock");

        //支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
//        boolean flag = lock.tryLock(1000, 20000, TimeUnit.MILLISECONDS);
        boolean flag = lock.tryLock();
        if(!flag) {
            throw new CheckException("重复请求！");
        }
        try{

            // do something
            Thread.sleep(30000);

        } finally {
            if(lock.isLocked() && lock.isHeldByCurrentThread()) {
                //解锁
                lock.unlock();
            }
//            lock.unlock();
        }

        return R.ok();
    }


}

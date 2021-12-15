package com.mylock.controller;

import com.mylock.annotation.Ide;
import com.mylock.handler.CheckException;
import com.mylock.annotation.Exclusion;
import com.mylock.util.R;
import com.mylock.util.RedisLock;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
//@AllArgsConstructor
public class RedisController {

    private final RedisLock redisLock;

    private StringRedisTemplate redisTemplate;

    public RedisController(RedisLock redisLock, StringRedisTemplate redisTemplate) {
        this.redisLock = redisLock;
        this.redisTemplate = redisTemplate;
    }

    private int i = 0;

    /**
     * 实现幂等性
     */
    @GetMapping("/ide")
    public R<?> ide() {
        String key = "userId:110";
        String value = String.valueOf(System.currentTimeMillis());
        boolean result = redisLock.lock(key, value, 60);
        try {
            if (!result) {
                throw new CheckException("请勿重复访问");
            }

            // 执行业务
            work(10000);

            return R.ok(i++);
        } finally {
            redisLock.unlock(key, value);
        }
    }

    /**
     * 实现幂等性
     */
    @GetMapping("/ide2")
    @Ide
    public R<?> ide2(String userId) {

        // 执行业务
        work(5000);

        return R.ok(i++);
    }


    /**
     * key覆盖案例
     */
    @GetMapping("/demo/{userId}")
    public R<?> demo(@PathVariable Long userId) {
        String key = "userId:" + userId;
        String value = Thread.currentThread().getName();
        boolean result = redisLock.lock(key, value, 60);
        try {
            if (!result) {
                throw new CheckException("请勿重复访问");
            }
            // 执行业务
            work(5000);

            return R.ok("执行成功");
        } finally {
//            redisTemplate.delete(key);
            redisLock.unlock(key, value);
        }
    }

    // 两个操作互斥

    /**
     * 互斥1
     */
    @GetMapping("/exclusion/0")
    @Exclusion
    public R<?> exclusion() {
        work(10000);
        return R.ok();
    }

    /**
     * 互斥2
     */
    @GetMapping("/exclusion/1")
    @Exclusion("1")
    public R<?> exclusion2() {
        work(10000);
        return R.ok();
    }

    /**
     * 模拟业务代码
     *
     * @param millis 毫秒值
     */
    private void work(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

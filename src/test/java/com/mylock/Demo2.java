package com.mylock;

import com.mylock.dto.StoreDto;
import com.mylock.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class Demo2 {

    @Autowired
    private StoreService storeService;

    @Test
    public void test() {
//        商城购买库存扣减案例
//        第一：管理员将菜品的库存整理好存入到redis
//        第二：用户购买产品，库存扣减。

//        实现方式：
//      初始化库存

        // 方案一：synchorzion
//        将库存存入到数据库，通过synchorzion控制

        // 方案二：数据库行锁
//        将库存存入到数据库，通过数据库行锁控制

        // 方案三：
//        将库存存入到数据库，通过数据库乐观锁控制

        // 方案四：分布式锁
        // 第一步，扣减redis中对应商品的数量的库存
        // 第二步，异步更新至数据库
//        storeService.init();
//        exec();
        for (int i = 0; i < 100; i++) {
            new Thread(this::exec, "线程：" + i).start();
        }

    }


    public void exec() {
        log.info("开始库存扣减");
        storeService.pay(new StoreDto(101, 1));
    }
}

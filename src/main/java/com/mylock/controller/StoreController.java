package com.mylock.controller;

import com.mylock.dto.StoreDto;
import com.mylock.entity.Store;
import com.mylock.service.StoreService;
import com.mylock.service.way.MysqlService;
import com.mylock.service.way.RedisService;
import com.mylock.service.way.SyncService;
import com.mylock.util.R;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/store")
@AllArgsConstructor
@Api(value = "/store", tags = "商店api")
public class StoreController {

    private final StoreService storeService;
    private final SyncService syncService;
    private final MysqlService mysqlService;
    private final RedisService redisService;

    @ApiOperation(value = "查询", notes = "查询库存")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "int", value = "商品id", required = true)
    })
    @GetMapping("/get/{id}")
    public R<?> get(@PathVariable Integer id) {
        return R.ok(storeService.getById(id));
    }

    /**
     * 不使用锁造成的问题
     *
     * @param
     * @return
     */
    @PostMapping("/pay")
    public R<?> pay(@Valid @RequestBody StoreDto storeDto) {
        return R.ok(storeService.pay(storeDto));
    }

    /**
     * 购买商品（同步代码块方式）
     *
     * @param
     * @return
     */
    @PostMapping("/syncpay")
    public R<?> syncPay(@Valid @RequestBody StoreDto storeDto) {
        return R.ok(syncService.pay(storeDto));
    }

    /**
     * 购买商品（数据库行锁方式）
     *
     * @param
     * @return
     */
    @PostMapping("/mysqlsad")
    public R<?> sad(@Valid @RequestBody StoreDto storeDto) {
        return R.ok(mysqlService.sad(storeDto));
    }

    /**
     * 购买商品（数据库乐观锁方式）
     *
     * @param
     * @return
     */
    @PostMapping("/mysqlhappy")
    public R<?> happy(@Valid @RequestBody StoreDto storeDto) {
        return R.ok(mysqlService.happy(storeDto));
    }


    /**
     * 购买商品（redis方式）
     *
     * @param
     * @return
     */
    @PostMapping("/redispay")
    public R<?> redisPay(@Valid @RequestBody StoreDto storeDto) {
        return R.ok(redisService.pay(storeDto));
    }

    /**
     * 初始化库存
     *
     * @return
     */
    @PostMapping("/init")
    public R<?> init(@RequestBody Store store) {
        return R.ok(storeService.init(store));
    }

    // redis锁
    public static void main(String[] args) {



    }

}

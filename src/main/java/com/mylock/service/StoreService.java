package com.mylock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mylock.dto.StoreDto;
import com.mylock.entity.Store;

public interface StoreService extends IService<Store> {

    boolean init(Store store);

    boolean pay(StoreDto storeDto);

    void work(long millis);

    void work();

}

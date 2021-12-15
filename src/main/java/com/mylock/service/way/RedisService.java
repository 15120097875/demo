package com.mylock.service.way;

import com.mylock.dto.StoreDto;

public interface RedisService {

    boolean pay(StoreDto storeDto);

}

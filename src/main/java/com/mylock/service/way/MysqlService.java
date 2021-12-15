package com.mylock.service.way;

import com.mylock.dto.StoreDto;

public interface MysqlService {

    boolean sad(StoreDto storeDto);

    boolean happy(StoreDto storeDto);

}

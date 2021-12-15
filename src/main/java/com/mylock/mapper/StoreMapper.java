package com.mylock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylock.dto.StoreDto;
import com.mylock.entity.Store;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper extends BaseMapper<Store> {

    int updateNum(StoreDto storeDto);

    int updateNumCas(StoreDto storeDto);

}

package com.mylock.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mylock.dto.StoreDto;
import com.mylock.entity.Store;
import com.mylock.mapper.StoreMapper;
import com.mylock.service.AsyncService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AsyncServiceImpl implements AsyncService {

    private final StoreMapper storeMapper;

    /**
     * 异步更新库存
     *
     * @param storeDto
     */
    @Async
    @Override
    public void updateNum(StoreDto storeDto) {
        log.info("异步更新库存");
        storeMapper.update(null, new UpdateWrapper<Store>().setSql("num = num - " + storeDto.getDelta()).eq("pid", storeDto.getPid()));
    }

}

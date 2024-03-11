package com.mylock.vo;

import lombok.Data;

import java.util.List;

/**
 * 列表入参
 * @author WSF
 * */
@Data
public class SearchQueryVo {

    /**
     * 筛选值
     */
    private List<CustomQueryBaseVo> SearchQuery;
}

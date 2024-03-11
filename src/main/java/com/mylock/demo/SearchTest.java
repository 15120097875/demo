package com.mylock.demo;

import com.mylock.vo.CustomQueryBaseVo;
import com.mylock.vo.SearchQueryVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chaihao
 */
public class SearchTest {


    public static void main(String[] args) {
        SearchQueryVo searchQueryVo = new SearchQueryVo();
        List<CustomQueryBaseVo> customQueryBaseVoList = new ArrayList<>();
        CustomQueryBaseVo customQueryBaseVo = new CustomQueryBaseVo();
        customQueryBaseVo.setFieldName("trade_id");
        customQueryBaseVo.setTableName("bm_case_list_info");
        customQueryBaseVo.setInputType("1");
        customQueryBaseVo.setText("123");
        customQueryBaseVoList.add(customQueryBaseVo);
        CustomQueryBaseVo customQueryBaseVo2 = new CustomQueryBaseVo();
        customQueryBaseVo2.setFieldName("fpsj");
        customQueryBaseVo2.setTableName("bm_case_list_info");
        customQueryBaseVo2.setInputType("7");
        customQueryBaseVo2.setText("123,456");
        customQueryBaseVoList.add(customQueryBaseVo2);

        CustomQueryBaseVo customQueryBaseVo3 = new CustomQueryBaseVo();
        customQueryBaseVo3.setFieldName("ptpje");
        customQueryBaseVo3.setTableName("bm_case_list_info");
        customQueryBaseVo3.setInputType("3");
        customQueryBaseVo3.setText("123,456");
        customQueryBaseVoList.add(customQueryBaseVo3);


        CustomQueryBaseVo customQueryBaseVo4 = new CustomQueryBaseVo();
        customQueryBaseVo4.setFieldName("trade_id");
        customQueryBaseVo4.setTableName("json");
        customQueryBaseVo4.setInputType("1");
        customQueryBaseVo4.setText("123");
        customQueryBaseVoList.add(customQueryBaseVo4);
        CustomQueryBaseVo customQueryBaseVo5 = new CustomQueryBaseVo();
        customQueryBaseVo5.setFieldName("fpsj");
        customQueryBaseVo5.setTableName("json");
        customQueryBaseVo5.setInputType("7");
        customQueryBaseVo5.setText("123,456");
        customQueryBaseVoList.add(customQueryBaseVo5);

        CustomQueryBaseVo customQueryBaseVo6 = new CustomQueryBaseVo();
        customQueryBaseVo6.setFieldName("ptpje");
        customQueryBaseVo6.setTableName("json");
        customQueryBaseVo6.setInputType("3");
        customQueryBaseVo6.setText("123,456");
        customQueryBaseVoList.add(customQueryBaseVo6);
        searchQueryVo.setSearchQuery(customQueryBaseVoList);
        String querySql = "";
        String queryJsonSql = "";
        List<CustomQueryBaseVo> searchQuery = searchQueryVo.getSearchQuery();
        Map<String, List<CustomQueryBaseVo>> collect = searchQuery.stream().collect(Collectors.groupingBy(CustomQueryBaseVo::getTableName));

        for(String  key: collect.keySet()){
            if("json".equals(key)){
                queryJsonSql = getQueryJsonSql(searchQuery, queryJsonSql);
            }else{
                querySql = getQuerySql(searchQuery, querySql);
            }
        }
        System.out.println(querySql);
        System.out.println(queryJsonSql);
    }

    private static String getQuerySql(List<CustomQueryBaseVo> searchQuery, String querySql) {
        for(CustomQueryBaseVo customQuery : searchQuery){
            if("1".equals(customQuery.getInputType()) || "5".equals(customQuery.getInputType())){
                querySql += " and "+"info."+customQuery.getFieldName()+" = '"+customQuery.getText()+"'";
            }
            if("3".equals(customQuery.getInputType())){
                if(customQuery.getText().contains(",")) {
                    String[] split = customQuery.getText().split(",");
                    querySql += " and "+"info."+customQuery.getFieldName() + " in (";
                    for (int i = 0; i < split.length; i++) {
                        querySql += "'" + split[i] + "',";
                    }
                    querySql = querySql.substring(0, querySql.length() - 1);
                    querySql += ")";
                }
            }
            if("7".equals(customQuery.getInputType())){
                if(customQuery.getText().contains(",")){
                    String[] split = customQuery.getText().split(",");
                    querySql += " and "+"info."+customQuery.getFieldName()+" >= '"+split[0]+"'";
                    querySql += " and "+"info."+customQuery.getFieldName()+" <= '"+split[1]+"'";
                }

            }
        }
        return querySql;
    }

    private static String getQueryJsonSql(List<CustomQueryBaseVo> searchQuery, String queryJsonSql) {
        for(CustomQueryBaseVo customQuery : searchQuery){
            if("1".equals(customQuery.getInputType()) || "5".equals(customQuery.getInputType())){
                queryJsonSql += " and JSON_EXTRACT(json.data, '$.\""+customQuery.getFieldName()+"\"')  = '"+customQuery.getText()+"'";
            }
            if("3".equals(customQuery.getInputType())){
                if(customQuery.getText().contains(",")) {
                    String[] split = customQuery.getText().split(",");
                    queryJsonSql += " and JSON_EXTRACT(json.data, '$.\""+customQuery.getFieldName()+"\"')" + " in (";
                    for (int i = 0; i < split.length; i++) {
                        queryJsonSql += "'" + split[i] + "',";
                    }
                    queryJsonSql = queryJsonSql.substring(0, queryJsonSql.length() - 1);
                    queryJsonSql += ")";
                }
            }
            if("7".equals(customQuery.getInputType())){
                if(customQuery.getText().contains(",")){
                    String[] split = customQuery.getText().split(",");
                    queryJsonSql += " and JSON_EXTRACT(json.data, '$.\""+customQuery.getFieldName()+"\"')  >= '"+split[0]+"'";
                    queryJsonSql += " and JSON_EXTRACT(json.data, '$.\""+customQuery.getFieldName()+"\"')  <= '"+split[1]+"'";
                }
            }
        }
        return queryJsonSql;
    }
}

package com.mylock.demo;

import cn.hutool.core.util.ObjectUtil;
import com.mylock.entity.Aj;
import com.mylock.entity.User;
import com.mylock.entity.UserFenan;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chaihao
 */
public class testfenan {
    public static void main(String[] args) {
        List<Aj> ajList = new ArrayList<>();
        Aj aJ1 = new Aj();
        aJ1.setWaje(new BigDecimal(60232.70));
        ajList.add(aJ1);
        Aj aJ2 = new Aj();
        aJ2.setWaje(new BigDecimal(21980.23));
        ajList.add(aJ2);
        Aj aJ3 = new Aj();
        aJ3.setWaje(new BigDecimal(48623.48));
        ajList.add(aJ3);
        Aj aJ4 = new Aj();
        aJ4.setWaje(new BigDecimal(94360.37));
        ajList.add(aJ4);
        Aj aJ5 = new Aj();
        aJ5.setWaje(new BigDecimal(67396.45));
        ajList.add(aJ5);

        Aj aJ6 = new Aj();
        aJ6.setWaje(new BigDecimal(67396.45));
        ajList.add(aJ6);

        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("张三");
        user1.setBl("30");
        userList.add(user1);
        User user2 = new User();
        user2.setUsername("李四");
        user2.setBl("30");
        userList.add(user2);
        User user3 = new User();
        user3.setUsername("王五");
        user3.setBl("20");
        userList.add(user3);

        User user4 = new User();
        user4.setUsername("马六");
        user4.setBl("10");
        userList.add(user4);
        User user5 = new User();
        user5.setUsername("赵七");
        user5.setBl("10");
        user5.setGender("男");
        userList.add(user5);
        userList.sort(Comparator.comparing(User::getBl).reversed());
        userList.stream().forEach(System.out::println);


//        Map<String, List<User>> stringListMap = userList.stream().collect(Collectors.groupingBy(User::getUsername));
//        stringListMap.forEach((key,value) ->{
//            if(value.size() < 2){
//                System.out.println("原告信息sheet页"+key+"：原告数量小于2");
//            }
//        });


//        getCaseNumList2(ajList, userList);
//        getCaseMoneyAmountList(ajList, userList);
        getCaseMoneyAmountNumberList(ajList, userList);
    }

    //先取出每个人员需要分的数量 给他分完  可能会有案件漏分 先不用了
    private static void getCaseNumList(List<Aj> ajList, List<User> userList) {
        Collections.shuffle(ajList);
        int totalValue = ajList.size();
        List<UserFenan> userFenanList = new ArrayList<>();
        Map<String,List<Aj>> ajMap = new HashMap<>();
        for(User user : userList){
            if(null != user.getBl()){
                UserFenan userFenan = new UserFenan();
                userFenan.setUserName(user.getUsername());
                List<Aj> ajs = new ArrayList<>();
                float bl = Float.parseFloat(user.getBl());
                int funds = Math.round((float) totalValue * bl / 100);
                System.out.println(user.getUsername()+":"+funds);
                BigDecimal userTotal = new BigDecimal(0);
                Integer userTotalNum = 0;
                while(funds > 0 && ajList.size() > 0 ){
                    userTotalNum++;
                    userTotal = userTotal.add(ajList.get(0).getWaje());
                    ajs.add(ajList.get(0));
                    ajList.remove(0);
                    funds--;
                }
                userFenan.setCase_price(userTotal.setScale(2,BigDecimal.ROUND_UP));
                userFenan.setCaseNum(userTotalNum);
                userFenanList.add(userFenan);
                ajMap.put(user.getUsername(),ajs);
            }
        }
        userFenanList.stream().forEach(x -> System.out.println("数量："+x));
    }

    /**
     * 按照数量分配
     * @param ajList
     * @param userList
     */

    private static void getCaseNumList2(List<Aj> ajList, List<User> userList) {
        Collections.shuffle(ajList);
        Collections.shuffle(userList);
        int totalValue = ajList.size();
        //记录每个用户需要分配的总案件数
        Map<String, Integer> userNumMap = getStringIntegerMap(userList, totalValue);
        List<UserFenan> userFenanList = new ArrayList<>();
        Map<String,List<Aj>> ajMap = new HashMap<>();
        for(User user : userList){
            if(null != user.getBl()){
                UserFenan userFenan = new UserFenan();
                userFenan.setUserName(user.getUsername());
                List<Aj> ajs = new ArrayList<>();
                int funds = userNumMap.get(user.getUsername());
                System.out.println(user.getUsername()+":"+funds);
                BigDecimal userTotal = new BigDecimal(0);
                Integer userTotalNum = 0;
                while(funds > 0 && ajList.size() > 0 ){
                    userTotalNum++;
                    userTotal = userTotal.add(ajList.get(0).getWaje());
                    ajs.add(ajList.get(0));
                    ajList.remove(0);
                    funds--;
                }
                userFenan.setCase_price(userTotal.setScale(2,BigDecimal.ROUND_UP));
                userFenan.setCaseNum(userTotalNum);
                userFenanList.add(userFenan);
                ajMap.put(user.getUsername(),ajs);
            }
        }
        userFenanList.stream().forEach(x -> System.out.println("数量："+x));
    }


    /**
     * 按照金额分配
     * @param ajList
     * @param userList
     */
    private static void getCaseMoneyAmountList(List<Aj> ajList, List<User> userList) {
        Collections.shuffle(ajList);
//        Collections.shuffle(userList);
        BigDecimal totalValue = ajList.stream().map( Aj::getWaje ).reduce(BigDecimal.ZERO, BigDecimal::add);
        List<UserFenan> userFenanList = new ArrayList<>();
        Map<String,List<Aj>> ajMap = new HashMap<>();
        for(User user : userList){
            if(null != user.getBl()){
                UserFenan userFenan = new UserFenan();
                userFenan.setUserName(user.getUsername());
                List<Aj> ajs = new ArrayList<>();
                BigDecimal bl = new BigDecimal(user.getBl());
                BigDecimal funds = totalValue.multiply(bl).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                System.out.println(user.getUsername()+":"+funds);
                BigDecimal userTotal = new BigDecimal(0);
                Integer userTotalNum = 0;
                while(funds.doubleValue() > 0 && ajList.size() > 0 ){
                    userTotalNum++;
                    userTotal = userTotal.add(ajList.get(0).getWaje());
                    funds  = funds.subtract(ajList.get(0).getWaje());
                    ajs.add(ajList.get(0));
                    ajList.remove(0);
                }
                userFenan.setCase_price(userTotal.setScale(2,BigDecimal.ROUND_UP));
                userFenan.setCaseNum(userTotalNum);
                userFenanList.add(userFenan);
                ajMap.put(user.getUsername(),ajs);
            }
        }
        userFenanList.stream().forEach(x -> System.out.println("金额："+x));
    }


    /**
     * 按照金额数量均分
     * @param ajList
     * @param userList
     */
    private static void getCaseMoneyAmountNumberList(List<Aj> ajList, List<User> userList) {
        Collections.shuffle(ajList);
//        Collections.shuffle(userList);
        BigDecimal totalValue = ajList.stream().map( Aj::getWaje ).reduce(BigDecimal.ZERO, BigDecimal::add);
        int totalNumberValue = ajList.size();

        //记录每个用户需要分配的总案件数
        Map<String, Integer> userNumMap = getStringIntegerMap(userList, totalNumberValue);
        List<UserFenan> userFenanList = new ArrayList<>();
        Map<String,List<Aj>> ajMap = new HashMap<>();
        for(User user : userList){
            if(null != user.getBl()){
                UserFenan userFenan = new UserFenan();
                userFenan.setUserName(user.getUsername());
                List<Aj> ajs = new ArrayList<>();
                //计算当前人员根据比例得出的金额
                BigDecimal bl = new BigDecimal(user.getBl());
                BigDecimal funds = totalValue.multiply(bl).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                System.out.println(user.getUsername()+":"+funds);
                //计算当前人员根据比例得出的案件数量
                int numberFunds = userNumMap.get(user.getUsername());

                BigDecimal userTotal = new BigDecimal(0);
                Integer userTotalNum = 0;
                //金额和数量任意达到比例时 不再分配
                while((funds.doubleValue() > 0 && numberFunds > 0) && ajList.size() > 0 ){
                    userTotalNum++;
                    userTotal = userTotal.add(ajList.get(0).getWaje());
                    funds  = funds.subtract(ajList.get(0).getWaje());
                    numberFunds--;
                    ajs.add(ajList.get(0));
                    ajList.remove(0);
                }
                userFenan.setCase_price(userTotal.setScale(2,BigDecimal.ROUND_UP));
                userFenan.setCaseNum(userTotalNum);
                userFenanList.add(userFenan);
                ajMap.put(user.getUsername(),ajs);
            }
        }
        userFenanList.stream().forEach(x -> System.out.println("金额："+x));
    }

    /**
     * 计算每个人需要分配的案件数量
     * @param userList
     * @param totalNumberValue
     * @return
     */
    private static Map<String, Integer> getStringIntegerMap(List<User> userList, int totalNumberValue) {
        Map<String, Integer> userNumMap = new HashMap<>();
        int tottalUserNum = 0;
        for(User user : userList){
            if(null != user.getBl()){
                float bl = Float.parseFloat(user.getBl());
                int funds = (int) Math.floor((float) totalNumberValue * bl / 100);
                if( funds < 1){
                    funds = 1;
                }
                tottalUserNum = tottalUserNum + funds;
                userNumMap.put(user.getUsername(),funds);
            }
        }
        //判断案件是否还有剩余
        int residueNum = totalNumberValue -tottalUserNum;
        if(residueNum > 0){
            int i = 0;
            userList.sort(Comparator.comparing(User::getBl).reversed());
            while(residueNum > 0 ){
                int index = i % userList.size();
                User user = userList.get(index);
                userNumMap.put(user.getUsername(),userNumMap.get(user.getUsername())+1);
                residueNum--;
            }
        }
        return userNumMap;
    }
}

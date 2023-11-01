package com.mylock.demo;

import cn.hutool.core.util.ObjectUtil;
import com.mylock.entity.Aj;
import com.mylock.entity.User;
import com.mylock.entity.UserFenan;

import java.math.BigDecimal;
import java.util.*;

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

        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("张三");
        user1.setBl("34");
        userList.add(user1);
        User user2 = new User();
        user2.setUsername("李四");
        user2.setBl("33");
        userList.add(user2);
        User user3 = new User();
        user3.setUsername("王五");
        user3.setBl("33");
        userList.add(user3);


//        getCaseNumList(ajList, userList);
        getCaseJeList(ajList, userList);
    }

    private static void getCaseNumList(List<Aj> ajList, List<User> userList) {
        Collections.shuffle(ajList);
        int totalValue = ajList.size(); // 需要分配的总值
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

    private static void getCaseJeList(List<Aj> ajList, List<User> userList) {
//        Collections.shuffle(ajList);
        BigDecimal totalValue = ajList.stream().map( Aj::getWaje ).reduce(BigDecimal.ZERO, BigDecimal::add); // 需要分配的总值
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
}

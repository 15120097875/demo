package com.mylock.demo;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mylock.constant.GlobalConstant;
import com.mylock.dto.StatisticsGroupCourtDto;
import com.mylock.dto.shanghai.*;
import com.mylock.entity.Aj;
import com.mylock.entity.Store;
import com.mylock.util.MaskingUtil;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chaihao
 */
public class yaosu {

    public static void main(String[] args){
        StringBuffer susong = new StringBuffer();
        int xuhao = 1;
        //注：（1）币种均为人民币的判断条件：是否双币信用卡=否；
        //   （2）被告，显示本案的全部被告，包括担保人。
        if("是否双币信用卡".equals("是否双币信用卡")){
            susong.append(xuhao);
            susong.append("、判令被告");
            susong.append("【被告-名称、姓名】");
            susong.append("支付截止至");
            susong.append("【数据截止日】");
            susong.append("的信用卡欠款本金人民币");
            susong.append("【本金（人民币）】");
            susong.append("元(以下币种均为人民币) 及自逾期之日起至");
            susong.append("【数据截止日】");
            susong.append("的利息");
            susong.append("【利息（人民币）】");
            susong.append("元、违约金");
            susong.append("【违约金（人民币）】");
            susong.append("元、分期手续费");
            susong.append("【分期手续费（人民币）】");
            susong.append("元、年费");
            susong.append("【年费（人民币）】");
            susong.append("元、取现手续费");
            susong.append("【取现手续费（人民币）】");
            susong.append("元、单项增值服务费");
            susong.append("【单项增值服务费（人民币）】");
            susong.append("元、杂费");
            susong.append("【杂费（人民币）】");
            susong.append("元。");
            susong.append("\r\n");
            xuhao++;
        }
        //注：（1）币种均为人民币的判断条件：是否双币信用卡=是；
        //   （2）被告，显示本案的全部被告，包括担保人。
        //   （3）以上两条二选一生成。
        if("是否双币信用卡".equals("是")){
            susong.append(xuhao);
            susong.append("、判令被告");
            susong.append("【被告-名称、姓名】");
            susong.append("支付截止至");
            susong.append("【数据截止日】");
            susong.append("的信用卡欠款本金人民币");
            susong.append("【本金（人民币）】");
            susong.append("元、美元");
            susong.append("【本金（美金）】");
            susong.append("元及自逾期之日起至");
            susong.append("【数据截止日】");
            susong.append("的利息人民币");
            susong.append("【利息（人民币）】");
            susong.append("元、美元");
            susong.append("元、违约金人民币");
            susong.append("【违约金（人民币）】");
            susong.append("元、美元");
            susong.append("【违约金（美金）】");
            susong.append("元、分期手续费人民币");
            susong.append("【分期手续费（人民币）】");
            susong.append("元、美元");
            susong.append("【分期手续费（美金）】");
            susong.append("元、取现手续费人民币");
            susong.append("【取现手续费（人民币）】");
            susong.append("元、美元");
            susong.append("【取现手续费（美金）】");
            susong.append("元、单项增值服务费人民币");
            susong.append("【单项增值服务费（人民币）】");
            susong.append("元、美元");
            susong.append("【单项增值服务费（美金）】");
            susong.append("元、杂费人民币");
            susong.append("【杂费（人民币）】");
            susong.append("元。上述美元合计");
            susong.append("【美元合计】");
            susong.append("元，折合人民币");
            susong.append("【折合人民币】");
            susong.append("元。");
            susong.append("\r\n");
            xuhao++;
        }
        //如利息、违约金、分期手续费、年费、取现手续费、单项增值服务费、杂费其中一之金额大零
        //注：（1）被告，显示本案的全部被告，包括担保人；
        //   （2）不为0的项目即显示，为0的不显示。
        if("如利息".equals("如利息")){
            susong.append(xuhao);
            susong.append("、判令被告");
            susong.append("【被告-名称、姓名】");
            susong.append("支付自");
            susong.append("【数据截止日次日】");
            susong.append("起至实际清偿日止的利息、违约金、分期手续费、年费、取现手续费、单项增值服务费、杂费；");
            susong.append("【均按双方订立的合同约定的标准执行】");
            susong.append("\r\n");
            xuhao++;
        }
        //如存在大于零的律师费
        //注：（1）被告，不包括担保人。
        if("如存在大于零的律师费".equals("如存在大于零的律师费")){
            susong.append(xuhao);
            susong.append("、判令被告");
            susong.append("【被告-名称、姓名】");
            susong.append("支付律师费");
            susong.append("【律师费（人民币）】");
            susong.append("元。");
            susong.append("\r\n");
            xuhao++;
        }
        //如存在保证人信息
        // （1） 存在保证人的判断条件：是否有保证人=是
        // （2）【被告-名称、姓名】不显示担保人，仅显示被告本人
        if("是否有保证人".equals("是否有保证人")){
            susong.append(xuhao);
            susong.append("、判令被告");
            susong.append("【保证人姓名】");
            susong.append("对被告");
            susong.append("【被告-名称、姓名】");
            susong.append("的欠款在");
            susong.append("【保证范围】");
            susong.append("元范围内承担");
            susong.append("【保证方式】");
            susong.append("\r\n");
            xuhao++;
        }
        //【如存在滞纳金金额大于零】
        //注：（1）被告，显示本案的全部被告，包括担保人。
        if("如存在滞纳金金额大于零".equals("如存在滞纳金金额大于零")){
            susong.append(xuhao);
            susong.append("、判令被告");
            susong.append("【被告-名称、姓名】");
            susong.append("支付滞纳金");
            susong.append("【滞纳金（人民币）】");
            susong.append("元。");
            susong.append("\r\n");
            xuhao++;
        }
        //【如存在其他文本录入的诉请】
        if("如存在其他文本录入的诉请".equals("如存在其他文本录入的诉请")){
            susong.append(xuhao);
            susong.append("、【其他诉请】");
            susong.append("\r\n");
            xuhao++;
        }
        //【固定展示】
            susong.append(xuhao);
            susong.append("、诉讼费用由被告");
            susong.append("【被告-名称、姓名】");
            susong.append("负担。");
            susong.append("\r\n");
        System.out.println(susong.toString());
//=========================================上面是诉讼请求   下面是事实与理由===========================================

        StringBuffer shishi = new StringBuffer();
        Boolean kaiguan = true;
        //“于【信用卡申请日期1】、【信用卡申请日期2】、【信用卡申请日期3】向原告【原告名称】申请信用卡”,
        // ①当同组信用卡信息要素中“信用卡申请日期”、“核卡日期”均有值时，优先取“信用卡申请日期”；
        // ②同组信用卡信息要素中“核卡日期”有值，且“信用卡申请日期”无值时，本句表述显示为“原告【原告-名称、姓名】于【核卡日期1】、核卡日期2】、【核卡日期3】向被告【被告-名称、姓名】核发信用卡，”；
        if(!"核卡日期".isEmpty() && "信用卡申请日期".isEmpty() || kaiguan){
            shishi.append("原告");
            shishi.append("【原告-名称、姓名】");
            shishi.append("于");
            shishi.append("【核卡日期1】");
            shishi.append("向被告");
            shishi.append("【被告-名称、姓名】");
            shishi.append("核发信用卡，");
        }else {
            shishi.append("被告");
            shishi.append("【被告姓名】");
            shishi.append("于");
            shishi.append("【信用卡申请日期1】");
            shishi.append("向原告");
            shishi.append("【原告名称】");
            shishi.append("申请信用卡，");
        }
        //①同组信用卡信息要素中“实体卡号”、“账号”均有值时，优先取“实体卡号”,内容显示为：卡号为【实体卡号1】、【实体卡号2】、【实体卡号3】，
        //②同组信用卡信息要素中“账号”有值，且“实体卡号”无值时,内容显示为：记账账号为【账号1】、【账号2】、【账号3】
        if(!"账号".isEmpty() && "实体卡号".isEmpty() || kaiguan){
            shishi.append("记账账号为");
            shishi.append("【账号1】");
        }else{
            shishi.append("卡号为");
            shishi.append("【实体卡号1】");
        }
        //“虚拟卡卡号”、或“虚拟卡办理日期”有值时，在“其使用信用卡进行透支消费等业务后未能按约还款。”此句前增加显示“并于【虚拟卡办理日期1】、【虚拟卡办理日期2】、【虚拟卡办理日期3】办理了E招贷业务（对应的虚拟卡卡号为【虚拟卡卡号1】、【虚拟卡卡号2】、【虚拟卡卡号3】）
        if(!"虚拟卡卡号”、或“虚拟卡办理日期".isEmpty() || kaiguan){
            shishi.append(",并于");
            shishi.append("【虚拟卡办理日期1】");
            shishi.append("办理了E招贷业务（对应的虚拟卡卡号为");
            shishi.append("【虚拟卡卡号1】");
        }
        shishi.append("，其使用信用卡进行透支消费等业务后未能按约还款。");
        shishi.append("\r\n");
        shishi.append("原告");
        shishi.append("【原告-名称、姓名】");
        shishi.append("信用卡领用合约及章程/信用卡客户协议及费率表对");
        //“利息、违约金、分期手续费、年费、取现手续费、单项增值服务费等”，利息、违约金、分期手续费、年费、取现手续费、单项增值服务费值有值时（包含美元有值），则对应字段在描述中展示；如均为零，则描述中不展示
        if(!"利息、违约金、分期手续费、年费、取现手续费、单项增值服务费值有值时（包含美元有值）".isEmpty() || kaiguan){
            shishi.append(" 利息、违约金、分期手续费、年费、取现手续费、单项增值服务费等");
        }
        shishi.append("费用进行了约定,");
        shishi.append("其中：1、信用卡透支按月计收复利，透支利率为日利率0.05%，即年化利率18.25%。2、持卡人可选择全额或最低还款额还款方式还款。从记账日至到期还款日间为免息还款期。选择最低还款额方式还款将不再享受免息还款期待遇。3、持卡人还款金额未达到最低还款额时，除按照上述计息方法支付利息外，对最低还款额未还部分，还须按月支付5%的逾期还款违约金。");
        if(!"无律师费且无担保人，或“虚拟卡卡号”有值时".isEmpty() || kaiguan){
            shishi.append("持卡人如连续2个月（含）以上未按时还清每期账单最低还款额，则按最低还款额未还清部分收取6%违约金。");
        }
        shishi.append("4、分期手续费由银行根据持卡人的资信状况、用卡情况在标准手续费率的基础上进行调整，实际费率以银行最终评定结果和出账账单为准。");
        if(!"无律师费且无担保人，或“虚拟卡卡号”有值时".isEmpty() || kaiguan){
            shishi.append("持卡人如连续2个月（含）以上未按时还清每期账单最低还款额，则按最低还款额未还清部分收取6%违约金。");
        }
        //有保证人时，展示本段描述，且仅“保证范围”大于零时展示完整的“在【保证范围】元范围内”语句，否则，该句不展示。
        int shishixuhao = 5;
        if(!"保证人".isEmpty() || kaiguan){
            shishi.append("5、被告");
            shishi.append("【保证人姓名】");
            shishi.append("为被告");
            shishi.append("【被告-名称、姓名】");
            shishi.append("使用信用卡产生的债务");
            if(!"保证范围大于零时".isEmpty() || kaiguan){
                shishi.append("在");
                shishi.append("【保证范围】");
                shishi.append("元范围内");
            }
            shishi.append("承担");
            shishi.append("【保证方式】");
            shishi.append("。保证期间");
            shishi.append("【保证期间】。");
            shishixuhao++;
        }
        if(!"仅当“诉讼请求”“诉请3”律师费大于零时".isEmpty() || kaiguan){
            shishi.append(shishixuhao);
            shishi.append("、因信用卡纠纷产生的律师费由持卡人承担。");
        }
        if(!"信用卡申请日”或“核卡日期”早于2017.1.1时".isEmpty()|| kaiguan){
            shishi.append("\r\n");
            shishi.append("自2017年1月1日起，信用卡服务项目“滞纳金”变更为“违约金”，两者计算方式一致。");
        }
        if(!"滞纳金”大于零，且“信用卡申请日”或“核卡日期”早于2017.1.1".isEmpty()|| kaiguan){
            shishi.append("（诉请中的违约金数额已包含2017年1月1日前的滞纳金）。");
        }
        if("“虚拟卡卡号”或“虚拟卡办理日期”有值时".isEmpty()){
            if(!"否双币信用卡=否".isEmpty()){
                shishi.append("\r\n");
                shishi.append("截止至");
                shishi.append("【数据截止日期】");
                shishi.append("，被告");
                shishi.append("【被告-名称、姓名】");
                shishi.append("共计拖欠信用卡本金");
                shishi.append("【本金（人民币）】");
                shishi.append("元。");
            }
            if(!"否双币信用卡=是".isEmpty()){
                shishi.append("\r\n");
                shishi.append("截止至");
                shishi.append("【数据截止日期】");
                shishi.append("，被告");
                shishi.append("【被告-名称、姓名】");
                shishi.append("共计拖欠信用卡本金");
                shishi.append("【本金（人民币）】");
                shishi.append("元、美金");
                shishi.append("【本金（美金）】");
                shishi.append("元。");
            }
        }else {
            shishi.append("\r\n");
            shishi.append("截止至");
            shishi.append("【虚拟卡办理日期】");
            shishi.append("，被告");
            shishi.append("【被告-名称、姓名】");
            shishi.append("拖欠卡号为");
            shishi.append("【实体号或账号】");
            shishi.append("的信用卡本金");
            shishi.append("【本金（人民币）】");
            shishi.append("元、美元");
            shishi.append("【本金（美金）】");
            shishi.append("元、利息人民币");
            shishi.append("【利息（人民币）】");
            shishi.append("元、美元");
            shishi.append("【利息（美金）】");
            shishi.append("元、违约金人民币");
            shishi.append("【违约金（人民币）】");
            shishi.append("元、美元");
            shishi.append("【违约金（美金）】");
            shishi.append("元、分期手续费人民币");
            shishi.append("【分期手续费（人民币）】");
            shishi.append("元，卡号为");
            shishi.append("【虚拟卡卡号】");
            shishi.append("（E招贷虚拟卡）的信用卡本金");
            shishi.append("【本金（人民币）】");
            shishi.append("元、美元");
            shishi.append("【本金（美金）】");
            shishi.append("元、利息人民币");
            shishi.append("【利息（人民币）】");
            shishi.append("元、美元");
            shishi.append("【利息（美金）】");
            shishi.append("元、违约金人民币");
            shishi.append("【违约金（人民币）】");
            shishi.append("元、美元");
            shishi.append("【违约金（美金）】");
            shishi.append("元。");
        }

        System.out.println(shishi);
    }
}

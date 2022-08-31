package com.mylock.util;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mylock.constant.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 通过word模板生成新的word工具类
 *
 * @author zhiheng
 *
 */
@Slf4j
public class WorderToNewWordUtils {

    private static String templatePath;

    public static String createWord(Long userCaseId, Map<String,String> dataMap, Map<String,String> pictureMap,byte[] byteData) throws Exception{

        //1.设定生成的文书路径
        String fileRoot = getFileRoot(userCaseId);
        createFileRoot(fileRoot);
//        String wenshuUrl = getFileUrl(fileRoot, dataMap) + ".docx";
        String wenshuUrl ="D:\\多合同调解协议.docx";
        log.info("wenshuUrl==="+wenshuUrl);

        File outFile = new File(wenshuUrl);
        getPictureMap(fileRoot, pictureMap);
        try(Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8), 10240)) {
            // 下载模板
//            String mubanPath ="D:\\多合同调解协议最终版本.docx";
            String mubanPath ="D:\\多合同调解协议2.0.docx";
            // poi生成word
            List<String[]> testList = new ArrayList<>();
            WorderToNewWordUtils.changWord(mubanPath, wenshuUrl, dataMap, testList,pictureMap);

            WordSealUtil.sealInWord(wenshuUrl,wenshuUrl,pictureMap.get(GlobalConstant.AGREEMENT_SIGN_USER),dataMap.get("centerName"),0,0,25,-45,true);
            //生成pdf
            String pdfUrl = "D:\\多合同调解协议最终版本.pdf";
//            WordUtil.word2pdf(wenshuUrl,pdfUrl);
            XDocReport.wordConverterToPdf(wenshuUrl,pdfUrl,null,null);

            return pdfUrl;
        }
    }
    public static String createCommitmentWord(Long userCaseId, Map<String,String> dataMap, Map<String,String> pictureMap,byte[] byteData) throws Exception{

        //1.设定生成的文书路径
        String fileRoot = getFileRoot(userCaseId);
        createFileRoot(fileRoot);
        String wenshuUrl = getCommitmentFileUrl(fileRoot, dataMap) + ".docx";
        log.info("getCommitmentFileUrl==="+wenshuUrl);

        File outFile = new File(wenshuUrl);
        getPictureMap(fileRoot, pictureMap);
        try(Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8), 10240)) {
            // 下载模板
            String mubanPath = fileRoot + "commitment.docx";
            getFile(byteData, mubanPath);
            log.info("commitmentmubanPath==="+mubanPath);
            // poi生成word
            List<String[]> testList = new ArrayList<>();
            WorderToNewWordUtils.changWord(mubanPath, wenshuUrl, dataMap, testList,pictureMap);

//            WordSealUtil.sealInWord(wenshuUrl,wenshuUrl,pictureMap.get(GlobalConstant.AGREEMENT_SIGN_USER),dataMap.get("mediateOrgName"),0,0,275,-30,false);
            //生成pdf
            String pdfUrl = getCommitmentFileUrl(fileRoot, dataMap) + ".pdf";
            WordUtil.word2pdf(wenshuUrl,pdfUrl);

            return pdfUrl;
        }
    }

    /**
     * 删除根目录
     * @param userCaseId 用户分配ID
     */
    public static void deleteFileRoot(Long userCaseId){
        File file = new File(getFileRoot(userCaseId));
        FileUtil.del(file);
    }

    /**
     * 获取的图片base64转换成实际图片
     * @param fileRoot 根目录
     * @param pictureMap 图片Map
     */
    private static void getPictureMap(String fileRoot, Map<String, String> pictureMap) {

        for (Entry<String, String> entry : pictureMap.entrySet()){
            List<String> signUrlList = new ArrayList<>();
            if (StrUtil.isNotBlank(entry.getValue())){
                if(entry.getValue().contains("|")){
                    List<String> list = Arrays.asList(entry.getValue().split("\\|"));
                    for (String sign :list) {
                        String signUrl = fileRoot + File.separator + entry.getKey() + IdUtil.simpleUUID() + StrUtil.DOT + Base64ImageUtils.getSuffix(sign);
                        if(!ImgTools.generateImage(Base64ImageUtils.getBase64ForChange(entry.getValue()), signUrl)){
                            throw new ValidateException("签名生成失败!");
                        }
                        signUrlList.add(signUrl);
                    }
                }else{
                    String signUrl = fileRoot + File.separator + entry.getKey() + IdUtil.simpleUUID() +StrUtil.DOT + Base64ImageUtils.getSuffix(entry.getValue());
                    if(!ImgTools.generateImage(Base64ImageUtils.getBase64ForChange(entry.getValue()), signUrl)){
                        throw new ValidateException("签名生成失败!");
                    }
                    signUrlList.add(signUrl);
                }
                log.info("signUrlList="+signUrlList);
                pictureMap.put(entry.getKey(),String.join("|",signUrlList));
            }
        }
        log.info("pictureMap="+pictureMap);
    }

    private static String getFileUrl(String fileRoot ,Map<String,String> dataMap) {
        return  fileRoot + dataMap.get("applyName") +"和"+ dataMap.get("receiveName")
                + dataMap.get("reason") + "一案协调协议书";
    }
    private static String getCommitmentFileUrl(String fileRoot ,Map<String,String> dataMap) {
        return  fileRoot + dataMap.get("applyName") +"和"+ dataMap.get("receiveName")
                + dataMap.get("reason") + "一案承诺函";
    }

    /**
     * 获取根目录
     * @param userCaseId 用户分配ID
     * @return String
     */
    private static String getFileRoot(Long userCaseId){
        return getTemplatePath() + userCaseId + File.separator;
    }

    /**
     * 创建根目录
     * @param filePath 文件路径
     */
    private static void createFileRoot(String filePath){
        File file = new File(filePath);
        file.mkdirs();
    }

    /**
     * 当前文件夹地址
     * @return String
     */
    private static String getTemplatePath() {
        if(StringUtils.isBlank(templatePath)) {
            File directory = new File(""); // 参数为空
            String courseFile = null;
            try {
                courseFile = directory.getCanonicalPath();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            templatePath = courseFile + File.separator;
        }
        return templatePath;
    }

    /**
     * 根据byte数组，生成文件
     */
    public static String getFile(byte[] bfile, String filePath) {
        File file = new File(filePath);
        try (
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos)
        ){
            bos.write(bfile);
        } catch (Exception e) {
            throw new ValidateException("下载模板文件失败!", e);
        }
        return file.getAbsolutePath();
    }


    /**
     * 根据模板生成新word文档
     * 判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
     * @param inputUrl 模板存放地址
     * @param outputUrl 新文档存放地址
     * @param textMap 需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     */
    public static void changWord(String inputUrl, String outputUrl,
                                 Map<String, String> textMap, List<String[]> tableList,Map<String, String> pictureMap) {
        try {
            WordUtil.wordLicense(inputUrl,inputUrl,textMap);
            //获取docx解析对象
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
//            log.info("document==="+document.getDocument().toString());
//            log.info("document==="+document.getDocument().xmlText());
            //解析替换文本段落对象
            //设置段落的时间以及调解组织 单独设计位置
            log.info("textMap===="+textMap);
//            WorderToNewWordUtils.changeText(document, textMap);
            WorderToNewWordUtils.changBreak(document, textMap);


            WorderToNewWordUtils.changPicture(document,pictureMap);
            //解析替换表格对象
            WorderToNewWordUtils.changeTable(document, textMap, tableList);

            //生成新的word
            File file = new File(outputUrl);
            FileOutputStream stream = new FileOutputStream(file);
            document.write(stream);
            stream.close();

        } catch (IOException e) {
            log.error("create word failed!", e);
        }
    }

    private static void changBreak(XWPFDocument document, Map<String, String> pictureMap) {
        List<XWPFParagraph> paragraphs2 = document.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs2) {
            //判断此段落时候需要进行替换
            List<XWPFRun> runs = paragraph.getRuns();
            for (XWPFRun run : runs) {
//                System.out.print(run.toString());
                String value = run.toString();
                //分段显示的情况
                String[] values = value.split("\n");
                if(values.length > 1) {
                    run.setText(values[0],0);
                    for (int i = 1; i<  values.length; i++) {
                        //存在分段则新建一个run
                        XWPFRun newrun= paragraph.insertNewRun(i);
                        //copy样式
                        newrun.getCTR().setRPr(run.getCTR().getRPr());
                        //换行
                        newrun.addBreak();
                        //缩进
                        newrun.addTab();
                        newrun.setText(values[i]);
                    }
                    break;
                }else {
                    run.setText(value,0);
                }
            }
        }
    }

    private static void changPicture(XWPFDocument document, Map<String, String> pictureMap) {
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            List<XWPFRun> runs = paragraph.getRuns();
            for (XWPFRun run : runs) {
                //替换模板原来位置
                String string = run.toString();
                if (pictureMap.containsKey(string)){
                    run.setText("",0);

                    String picture = pictureMap.get(string);
                    if (StrUtil.isNotBlank(picture)){
                        changeAttachment(run, picture);
                    }
                }
            }
        }
    }

    /**
     * 替换段落文本
     * @param document docx解析对象
     * @param textMap 需要替换的信息集合
     */
    public static void changeText(XWPFDocument document, Map<String, String> textMap) {
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            List<XWPFRun> runs = paragraph.getRuns();
//            log.info("对比textMap===="+textMap);
            for (XWPFRun run : runs) {
                log.info("run===="+run.toString());
                //替换模板原来位置
                /*if(run.toString().contains("applyName")){
                    run.toString().replace("applyName",textMap.get("applyName"));
                }
                if(run.toString().contains("receiveName")){
                    run.toString().replace("receiveName",textMap.get("receiveName"));
                }
                if(run.toString().contains("contractNo")){
                    run.toString().replace("contractNo",textMap.get("contractNo"));
                }
                if(run.toString().contains("interestSettlementDate")){
                    run.toString().replace("interestSettlementDate",textMap.get("interestSettlementDate"));
                }
                if(run.toString().contains("principalAmount")){
                    run.toString().replace("principalAmount",textMap.get("principalAmount"));
                }*/
                chan(paragraph,textMap);
                if (textMap.containsKey(run.toString())){
                    log.info("textMap包含的run===="+run.toString());
                    run.setText(textMap.get(run.toString()), 0);
                }
            }

        }
    }

	/**
	 * 为段落添加图片
	 * @param run 行
	 * @param path 需要替换的信息集合
	 */
	private static void changeAttachment(XWPFRun run, String path) {
        log.info("path="+path);
        if(path.contains("|")){
            List<String> list = Arrays.asList(path.split("\\|"));
            for (String url:list) {
                try (FileInputStream inputStream = new FileInputStream(url)){
                    run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, path, Units.toEMU(70), Units.toEMU(70));
                } catch (Exception e){
                    log.error("add picture failed!", e);
                }
            }
        }else{
            try (FileInputStream inputStream = new FileInputStream(path)){
                run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, path, Units.toEMU(70), Units.toEMU(70));
            } catch (Exception e){
                log.error("add picture failed!", e);
            }
        }


	}

	/**
     * 替换表格对象方法
     * @param document docx解析对象
     * @param textMap 需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     */
    public static void changeTable(XWPFDocument document, Map<String, String> textMap,
                                   List<String[]> tableList){
        //获取表格对象集合
        List<XWPFTable> tables = document.getTables();
        for (XWPFTable table : tables) {
            //只处理行数大于等于2的表格，且不循环表头
            if(table.getRows().size() > 1){
                //判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
                if(checkText(table.getText())){
                    List<XWPFTableRow> rows = table.getRows();
                    //遍历表格,并替换模板
                    eachTable(rows, textMap);
                }else{
                    insertTable(table, tableList);
                }
            }
        }
    }





    /**
     * 遍历表格
     * @param rows 表格行对象
     * @param textMap 需要替换的信息集合
     */
    public static void eachTable(List<XWPFTableRow> rows , Map<String, String> textMap){
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                //判断单元格是否需要替换
                if(checkText(cell.getText())){
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            run.setText(changeValue(run.toString(), textMap),0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 为表格插入数据，行数不够添加新行
     * @param table 需要插入数据的表格
     * @param tableList 插入数据集合
     */
    public static void insertTable(XWPFTable table, List<String[]> tableList){
        //遍历表格插入数据
        List<XWPFTableRow> rows = table.getRows();
        for(int i = 1; i < rows.size(); i++){
            XWPFTableRow newRow = table.getRow(i);
            List<XWPFTableCell> cells = newRow.getTableCells();
            for(int j = 0; j < cells.size(); j++){
                XWPFTableCell cell = cells.get(j);
                cell.setText(tableList.get(i-1)[j]);
            }
        }
    }



    /**
     * 判断文本中时候包含$
     * @param text 文本
     * @return 包含返回true,不包含返回false
     */
    public static boolean checkText(String text){
        return text.contains("${");
    }

    /**
     * 匹配传入信息集合与模板
     * @param value 模板需要替换的区域
     * @param textMap 传入信息集合
     * @return 模板需要替换区域信息集合对应值
     */
    public static String changeValue(String value, Map<String, String> textMap){
        for (Entry<String, String> textSet : textMap.entrySet()) {
            //匹配模板与替换值 格式${key}
            String key = "${"+textSet.getKey()+"}";
            if(value.contains(key)){
                value = value.replace(key, textSet.getValue());
            }
        }
        return value;
    }
public  static void chan(XWPFParagraph paragraph , Map<String, String> textMap){
    List<XWPFRun> runs = paragraph.getRuns();
    // 合并逻辑
    for (int i = 0; i < runs.size(); i++) {
        String text0 = runs.get(i).getText(runs.get(i).getTextPosition());
        if (text0 != null&&text0.contains("@")) {
            int startIndex = text0.lastIndexOf("@");
            int endIndex = 1;
            if (startIndex != -1) {
                endIndex = text0.substring(startIndex).indexOf("}");
            }
            if (endIndex < 0) {
                // 记录分隔符中间跨越的runs数量，用于字符串拼接和替换
                int num = 0;
                int j = i + 1;
                for (; j < runs.size(); j++) {
                    String text1 = runs.get(j).getText(runs.get(j).getTextPosition());
                    if (text1 != null && text1.contains("}")) {
                        num = j - i;
                        break;
                    }
                }
                if (num != 0) {
                    // num!=0说明找到了@@配对，需要替换
                    StringBuilder newText = new StringBuilder();
                    for (int s = i; s <= i + num; s++) {
                        String text2 = runs.get(s).getText(runs.get(s).getTextPosition());
                        String replaceText = text2;
                        if (s == i && text2.contains("@") && text2.contains("}")) {
                            newText.append(text2);
                        } else if (s == i && text2.contains("@")) {
                            replaceText = text2.substring(0, text2.indexOf("@"));
                            newText.append(text2.substring(text2.indexOf("@")));
                        } else if (text2.contains("}")) {
                            replaceText = text2.substring(replaceText.indexOf("}") + 1);
                            newText.append(text2.substring(0, text2.indexOf("}") + 1));
                        } else {
                            replaceText = "";
                            newText.append(text2);

                        }
                        log.info("newText==="+newText);
                        log.info("replaceText==="+replaceText);
                        runs.get(s).setText(textMap.get(replaceText), 0);
                    }
                    runs.get(i).setText(newText.toString(), 0);
                    i = i - 1;
                }
            }
        }
    }
}
}

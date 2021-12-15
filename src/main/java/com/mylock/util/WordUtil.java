package com.mylock.util;

import com.aspose.words.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * word转换
 */
@Slf4j
public class WordUtil {

    public static void main(String[] args) throws IOException {

        String inputPath = "D:\\多合同调解协议.docx";
        String outputPath ="D:\\多合同调解协议111.docx";
        Map<String, String> map= new HashMap<>();
        map.put("applyName","11111");
        map.put("applyAddress","223423424234");
        map.put("receiveName","223423424234");
        map.put("receiveSex","11111");
        map.put("receiveBirthday","223423424234");
        map.put("receiveAddress","223423424234");
        map.put("applyInfo","原告：sheihsie ,住所地：\n"+"原告：sheihsie ,住所地：\n");
        map.put("receiveInfo","被告：sheihsie ,住所地：\n"+"被告：sheihsie ,住所地：\n");
        map.put("reason","11111");
        map.put("receiveContent","本人为解决上述债务问题，特向贵司申请减免罚息人民币    元，并提出如下付款计划：\n" +
                " 一、本人承诺采用分期方式还款，共分    期还清上述债务： \n" +
                "1.第一期：于  年   月   日前向贵司支付人民币  元，至贵司指定收款账户中。\n" +
                "2.第二期：于  年   月   日前向贵司支付人民币  元，至贵司指定收款账户中。\n" +
                "3.最后一期：于  年   月   日前向贵司核实最终欠款金额后，将免除罚息后的金额支付至贵司指定收款账户，恳请贵司于收到本人全部分期还款后再给予核销并减免本人罚息人民币    元，分期期间仍以欠款本金为基数，按照合同约定的利率与逾期利率计算利息、罚息，仍以尚欠利息为基数，按照合同约定的逾期利率计算复利。\n" +
                "二、贵司指定收款账户如下：户名：浙江网商银行股份有限公司借呗还款待处理户，借呗账号：30020000013132679001，开户行（支行）：浙江网商银行股份有限公司，开户省市：浙江省杭州市。");
        map.put("applyAddress","223423424234");
        map.put("receiveName","223423424234");
        map.put("interestSettlementDate","11111");
        map.put("contractNo","223423424234");
        map.put("principalAmount","223423424234");
        map.put("interestAmount","11111");
        map.put("applyAddress","223423424234");
        map.put("liquidatedAmount","223423424234");
        map.put("targetAmount","11111");
        map.put("content","223423424234");
        map.put("mediateOrgName","223423424234");
        map.put("endTime","223423424234");

        wordLicense(inputPath,outputPath,map);
        XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage("D:\\承诺函111.docx"));
        List<XWPFParagraph> paragraphs2 = document.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs2) {
            //判断此段落时候需要进行替换
            List<XWPFRun> runs = paragraph.getRuns();
            for (XWPFRun run : runs) {
                System.out.print(run.toString());
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
        File file = new File("D:\\承诺函222.docx");
        FileOutputStream stream = new FileOutputStream(file);
        document.write(stream);
        stream.close();
//        try {
//            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
//            ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
//            License license = new License();
//            license.setLicense(is);
//            Document doc = new Document(inputPath);
//            doc.getRange().replace(Pattern.compile("applyName"),"张三");
//            doc.getRange().replace(Pattern.compile("contractNo"),"合同编号1111111111");
//            doc.save("");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        word2pdf("/Users/chenshuai/Desktop/调解协议.docx","/Users/chenshuai/Desktop/调解协议1.pdf");//word转pdf


        //word转图片格式
//        try {
//            File file = new File("/Users/chenshuai/Desktop/调解协议.docx");
//
//            InputStream inStream = new FileInputStream(file);
//            List<BufferedImage> wordToImg = wordToImg(inStream,2);//
//            BufferedImage mergeImage = mergeImage(false, wordToImg);
//
//            ImageIO.write(mergeImage, "jpg", new File("C:/Users/Administrator/Desktop/xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.png")); //将其保存在C:/imageSort/targetPIC/下
//
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }
    /**
     * @Description: 验证aspose.word组件是否授权：无授权的文件有水印标记
     */
    public static boolean wordLicense(String path, String outputUrl,Map<String, String> textMap) {
        boolean result = false;
        log.info("doc.getText===开始");
        try {
            String inputPath = path;
            try {
                String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
                ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
                License license = new License();
                license.setLicense(is);
                Document doc = new Document(inputPath);
                DocumentBuilder documentBuilder =new DocumentBuilder(doc);
                for(String obj: textMap.keySet()){
                    doc.getRange().replace(Pattern.compile(obj),textMap.get(obj));
                }

//                log.info("document===="+doc.getRange().get);

                /*doc.getRange().replace(Pattern.compile("applyName"),textMap.get("applyName"));
                doc.getRange().replace(Pattern.compile("applyAddress"),textMap.get("applyAddress"));
                doc.getRange().replace(Pattern.compile("receiveName"),textMap.get("receiveName"));
                doc.getRange().replace(Pattern.compile("receiveSex"),textMap.get("receiveSex"));
                doc.getRange().replace(Pattern.compile("receiveBirthday"),textMap.get("receiveBirthday"));
                doc.getRange().replace(Pattern.compile("receiveAddress"),textMap.get("receiveAddress"));
                doc.getRange().replace(Pattern.compile("reason"),textMap.get("reason"));
                doc.getRange().replace(Pattern.compile("receiveName"),textMap.get("receiveName"));
                doc.getRange().replace(Pattern.compile("interestSettlementDate"),textMap.get("interestSettlementDate"));
                doc.getRange().replace(Pattern.compile("contractNo"),textMap.get("contractNo"));
                doc.getRange().replace(Pattern.compile("principalAmount"),textMap.get("principalAmount"));
                doc.getRange().replace(Pattern.compile("interestAmount"),textMap.get("interestAmount"));
                doc.getRange().replace(Pattern.compile("liquidatedAmount"),textMap.get("liquidatedAmount"));
                doc.getRange().replace(Pattern.compile("targetAmount"),textMap.get("targetAmount"));
                doc.getRange().replace(Pattern.compile("content"),textMap.get("content"));
                doc.getRange().replace(Pattern.compile("mediateOrgName"),textMap.get("mediateOrgName"));
                doc.getRange().replace(Pattern.compile("endTime"),textMap.get("endTime"));
                doc.getRange().replace(Pattern.compile("mediateNo"),textMap.get("mediateNo"));*/
                log.info("doc.getText==="+doc.getText());
                log.info("doc.getText==="+doc.getDocument().getText());
                ParagraphCollection paragraphs = doc.getFirstSection().getBody().getParagraphs();
                for (Paragraph paragraph : paragraphs) {
                    log.info("WORDF替换后的内容:      "+paragraph.getText());
                }
                doc.save(outputUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description: 验证aspose.word组件是否授权：无授权的文件有水印标记
     */
    public static boolean isWordLicense() {
        boolean result = false;
        try {
            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(s.getBytes());
            //InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("license.xml");
            License license = new com.aspose.words.License();
            license.setLicense(inputStream);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }




    /**
     * @Description: word和txt文件转换图片
     */
    private static List<BufferedImage> wordToImg(InputStream inputStream, int pageNum) throws Exception {
        if (!isWordLicense()) {
            return null;
        }
        try {
            long old = System.currentTimeMillis();
            Document doc = new Document(inputStream);
            ImageSaveOptions options = new ImageSaveOptions(SaveFormat.PNG);
            options.setPrettyFormat(true);
            options.setUseAntiAliasing(true);
            options.setUseHighQualityRendering(true);
            int pageCount = doc.getPageCount();
            if (pageCount > pageNum) {//生成前pageCount张
                pageCount = pageNum;
            }
            List<BufferedImage> imageList = new ArrayList<BufferedImage>();
            for (int i = 0; i < pageCount; i++) {
                OutputStream output = new ByteArrayOutputStream();
                options.setPageIndex(i);

                doc.save(output, options);
                ImageInputStream imageInputStream = javax.imageio.ImageIO.createImageInputStream(parse(output));
                imageList.add(javax.imageio.ImageIO.read(imageInputStream));

            }
            return imageList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //outputStream转inputStream
    public static ByteArrayInputStream parse(OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }



    /**
     * 合并任数量的图片成一张图片
     *
     * @param isHorizontal true代表水平合并，fasle代表垂直合并
     * @param imgs         待合并的图片数组
     * @return
     * @throws IOException
     */
    public static BufferedImage mergeImage(boolean isHorizontal, List<BufferedImage> imgs) throws IOException {
        // 生成新图片
        BufferedImage destImage = null;
        // 计算新图片的长和高
        int allw = 0, allh = 0, allwMax = 0, allhMax = 0;
        // 获取总长、总宽、最长、最宽
        for (int i = 0; i < imgs.size(); i++) {
            BufferedImage img = imgs.get(i);
            allw += img.getWidth();

            if (imgs.size() != i + 1) {
                allh += img.getHeight() + 5;
            } else {
                allh += img.getHeight();
            }


            if (img.getWidth() > allwMax) {
                allwMax = img.getWidth();
            }
            if (img.getHeight() > allhMax) {
                allhMax = img.getHeight();
            }
        }
        // 创建新图片
        if (isHorizontal) {
            destImage = new BufferedImage(allw, allhMax, BufferedImage.TYPE_INT_RGB);
        } else {
            destImage = new BufferedImage(allwMax, allh, BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g2 = (Graphics2D) destImage.getGraphics();
        g2.setBackground(Color.LIGHT_GRAY);
        g2.clearRect(0, 0, allw, allh);
        g2.setPaint(Color.RED);

        // 合并所有子图片到新图片
        int wx = 0, wy = 0;
        for (int i = 0; i < imgs.size(); i++) {
            BufferedImage img = imgs.get(i);
            int w1 = img.getWidth();
            int h1 = img.getHeight();
            // 从图片中读取RGB
            int[] ImageArrayOne = new int[w1 * h1];
            ImageArrayOne = img.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
            if (isHorizontal) { // 水平方向合并
                destImage.setRGB(wx, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            } else { // 垂直方向合并
                destImage.setRGB(0, wy, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            }
            wx += w1;
            wy += h1 + 5;
        }


        return destImage;
    }


    /**
     * word转换pdf
     * @param docPath wordurl
     * @param savePath  oss pdfurl
     */
    public static void word2pdf(String docPath,String savePath){

        try {
            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
            License license = new License();
            license.setLicense(is);
            Document document = new Document(docPath);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath));
            document.save(fileOutputStream,SaveFormat.PDF);
            fileOutputStream.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}

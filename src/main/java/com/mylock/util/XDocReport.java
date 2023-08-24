package com.mylock.util;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.itext.extension.font.IFontProvider;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.awt.*;
import java.io.*;

public class XDocReport {

    public static void main(String[] args) throws Exception {
        String outPath = "D:\\多合同调解协议11.docx";
//        String outPath = "C:\\Users\\良卿卿\\Desktop\\金桥后的桌面上无用东西\\金桥调解协议书\\多合同调解协议最终版本1.0.docx";
//        wordConverterToPdf(outPath, outPath.replace("docx", "pdf"), "C:/Windows/Fonts/simhei.ttf", BaseFont.IDENTITY_H);
        wordConverterToPdf(outPath, outPath.replace("docx", "pdf"), null, null);
    }

    /**
     * 将word文档， 转换成pdf
     * 宋体：STSong-Light
     *
     * @param fontParam1 可以字体的路径，也可以是itextasian-1.5.2.jar提供的字体，比如宋体"STSong-Light"
     * @param fontParam2 和fontParam2对应，fontParam1为路径时，fontParam2=BaseFont.IDENTITY_H，为itextasian-1.5.2.jar提供的字体时，fontParam2="UniGB-UCS2-H"
     * @param tmp        源为word文档， 必须为docx文档
     * @param target     目标输出
     * @throws Exception
     */
    public static void wordConverterToPdf(String tmp, String target, String fontParam1, String fontParam2) {
        InputStream sourceStream = null;
        OutputStream targetStream = null;
        XWPFDocument doc = null;
        try {
            sourceStream = new FileInputStream(tmp);
            targetStream = new FileOutputStream(target);
            doc = new XWPFDocument(sourceStream);
            PdfOptions options = PdfOptions.create();
            //中文字体处理  如果乱码解开注释传入字符集
            /*options.fontProvider(new IFontProvider() {
                @Override
                public Font getFont(String familyName, String encoding, float size, int style, Color color) {
                    try {
                        BaseFont bfChinese = BaseFont.createFont(fontParam1, fontParam2, BaseFont.NOT_EMBEDDED);
                        Font fontChinese = new Font(bfChinese, size, style, color);
                        if (familyName != null) {
                            fontChinese.setFamily(familyName);
                        }
                        return fontChinese;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });*/
            PdfConverter.getInstance().convert(doc, targetStream, options);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(doc);
            IOUtils.closeQuietly(targetStream);
            IOUtils.closeQuietly(sourceStream);
        }
    }

}

//package com.mylock.util;
//
//
//import com.spire.doc.Document;
//import com.spire.doc.FileFormat;
//import com.spire.doc.documents.XHTMLValidationType;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ooxml.POIXMLDocument;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//
//import javax.imageio.stream.ImageInputStream;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;
//
///**
// * word转换
// */
//@Slf4j
//public class HtmlUtil {
//
//    public static void main(String[] args) throws Exception {
//
//        String inputPath = "D:\\wenshu.html";
//        String outputPath ="D:\\wenshu.pdf";
//
//        htmltopdf(inputPath,outputPath);//word转pdf
//
//    }
//
//    public static void htmltopdf(String htmlPath,String savePath) throws Exception {
//        //加载HTML文档
//        Document document = new Document();
//        document.loadFromFile(htmlPath, FileFormat.Html, XHTMLValidationType.None);
//
//        //文档另存为PDF
//        document.saveToFile("Result.pdf",FileFormat.PDF);
//
//    }
//}

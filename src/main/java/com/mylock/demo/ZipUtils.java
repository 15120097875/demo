package com.mylock.demo;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author MercyJiajia
 * @version 1.0.0
 * @date 2023/7/20  11:32
 */

public class ZipUtils {


    private static final int BUFFER_SIZE = 2 * 1024;

    /**
     * 压缩成ZIP 方法1
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */

    public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure) throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 压缩成ZIP 方法2
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */

    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */

    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }
                }
            }
        }
    }




    /**
     * 解压
     * @param zipFilePath 待解压文件的路径
     * @param unzipFilePath 解压后的文件存储路径
     * @throws Exception
     */
    public static void unzip(String zipFilePath, String unzipFilePath) throws Exception {
        File zipFile = new File(zipFilePath);

        //创建解压缩文件保存的路径
        File unzipFileDir = new File(unzipFilePath);
        if (!unzipFileDir.exists() || !unzipFileDir.isDirectory()) {
            unzipFileDir.mkdirs();
        }

        //开始解压
        ZipEntry entry = null;
        String entryFilePath = null, entryDirPath = null;
        File entryFile = null, entryDir = null;
        int index = 0, count = 0;
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        ZipFile zip = new ZipFile(zipFile);
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>)zip.entries();
        //循环对压缩包里的每一个文件进行解压
        while(entries.hasMoreElements()) {
            entry = entries.nextElement();
            //构建压缩包中一个文件解压后保存的文件全路径
            entryFilePath = unzipFilePath + File.separator + entry.getName();
            //构建解压后保存的文件夹路径
            index = entryFilePath.lastIndexOf(File.separator);
            if (index != -1) {
                entryDirPath = entryFilePath.substring(0, index);
            }
            else {
                entryDirPath = "";
            }
            entryDir = new File(entryDirPath);
            //如果文件夹路径不存在，则创建文件夹
            if (!entryDir.exists() || !entryDir.isDirectory()) {
                entryDir.mkdirs();
            }

            //创建解压文件
            entryFile = new File(entryFilePath);
            if (entryFile.exists()) {
                //检测文件是否允许删除，如果不允许删除，将会抛出SecurityException
                SecurityManager securityManager = new SecurityManager();
                securityManager.checkDelete(entryFilePath);
                //删除已存在的目标文件
                entryFile.delete();
            }

            //写入文件
            bos = new BufferedOutputStream(new FileOutputStream(entryFile));
            bis = new BufferedInputStream(zip.getInputStream(entry));
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, count);
            }
            bos.flush();
            bos.close();
        }
    }


    public static void main(String[] args) throws Exception {
//        List<File> files = new ArrayList<>();
//        files.add(new File("D:\\ecf\\ws_data\\ja\\(2023)渝0105民初12328号\\(2023)渝0105民初12328号+金桥信息+民事裁定书（小额诉讼程序转普通程序用）.docx"));
//        files.add(new File("D:\\ecf\\ws_data\\pq\\(2023)渝0105民初13138号\\民事裁定书（小额诉讼程序转普通程序用）.docx"));
//        Long id = IdUtil.getId();
//        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\ecf\\ws_data\\ja\\"+id+"\\判决.zip"));
//        ZipUtils.toZip(files, fileOutputStream);

//        String goalPath ="D:\\ecf\\test.zip";
//        String tempPath ="D:\\ecf";
//        TomcatServletWebServerFactory.
//        ZipUtils.unzip(goalPath,tempPath);
//        File unzip = ZipUtil.unzip("D:\\ecf\\test.zip", "D:\\ecf");

//        //加载Word文档
//        Document document = new Document();
//        document.loadFromFile("D:\\ecf\\ceshi2.docx");
//
//        //获取文档中的文本保存为String
//        String text=document.getText();
//        String qianzhi = "";
//        if(text.contains("裁判如下")){
//            qianzhi = "裁判如下";
//        }
//        if(text.contains("判决如下")){
//            qianzhi = "判决如下";
//        }
//
//        String houzhi = "";
//        if(text.contains("如不服本判决")){
//            houzhi = "如不服本判决";
//        }
//        if(text.contains("若不服本判决")){
//            houzhi = "若不服本判决";
//        }
//        if(text.contains("如果不服本判决")){
//            houzhi = "如果不服本判决";
//        }
//        if(text.contains("本判决为终审判决")){
//            houzhi = "本判决为终审判决";
//        }
//        text = text.substring(text.indexOf(qianzhi),text.indexOf(houzhi));
//        System.out.println(text.substring(text.indexOf("一、")));
    }



}

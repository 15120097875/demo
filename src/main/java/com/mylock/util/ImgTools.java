package com.mylock.util;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

@Slf4j
public class ImgTools {

	private ImgTools(){}

	/**
	 * 将图片压缩到指定大小以内
	 * 
	 * @param srcImgData 源图片数据
	 * @param maxSize 目的图片大小
	 * @return 压缩后的图片数据
	 */
	public static byte[] compressUnderSize(byte[] srcImgData, long maxSize) {
		double scale = 0.9;
		byte[] imgData = Arrays.copyOf(srcImgData, srcImgData.length);
 
		if (imgData.length > maxSize) {
			do {
				try {
					imgData = compress(imgData, scale);
 
				} catch (IOException e) {
					throw new IllegalStateException("压缩图片过程中出错，请及时联系管理员！", e);
				}
 
			} while (imgData.length > maxSize);
		}
 
		return imgData;
	}
 
	/**
	 * 按照 宽高 比例压缩
	 * 
	 * @param srcImgData 待压缩图片输入流
	 * @param scale 压缩刻度
	 * @return 压缩后图片数据
	 * @throws IOException 压缩图片过程中出错
	 */
	public static byte[] compress(byte[] srcImgData, double scale) throws IOException {
		BufferedImage bi = ImageIO.read(new ByteArrayInputStream(srcImgData));
		int width = (int) (bi.getWidth() * scale); // 源图宽度
		int height = (int) (bi.getHeight() * scale); // 源图高度
 
		Image image = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
 
		Graphics g = tag.getGraphics();
		g.setColor(Color.RED);
		g.drawImage(image, 0, 0, null); // 绘制处理后的图
		g.dispose();
 
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		ImageIO.write(tag, "JPEG", bOut);
 
		return bOut.toByteArray();
	}


	/**
	 * base64转图片
	 * @param imgData 文件数据
	 * @param imgFilePath 文件路径
	 * @return boolean
	 */
	public static boolean generateImage(String imgData, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
		// 图像数据为空
		if (imgData == null) {
			return false;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try (OutputStream out = new FileOutputStream(imgFilePath)){
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgData);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			out.write(b);
		} catch (Exception e) {
			log.error("转化图片失败!", e);
			return false;
		}
		return true;
	}
 
}

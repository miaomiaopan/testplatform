/**
 * 
 */
package testplatform.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  

/**
 * @author panmiaomiao
 *
 * @date 2018年6月20日
 */
public class jyjm {

	/**
	 * 将字符串压缩后Base64
	 * 
	 * @param primStr
	 *            待加压加密函数
	 * @return
	 */
	public static String gzipString(String primStr) {
		if (primStr == null || primStr.length() == 0) {
			return primStr;
		}
		ByteArrayOutputStream out = null;
		GZIPOutputStream gout = null;
		try {
			out = new ByteArrayOutputStream();
			gout = new GZIPOutputStream(out);
			gout.write(primStr.getBytes("utf-8"));
			gout.flush();
		} catch (IOException e) {
			System.out.println("对字符串进行加压加密操作失败："+e.getMessage());
			return null;
		} finally {
			if (gout != null) {
				try {
					gout.close();
				} catch (IOException e) {
					System.out.println("对字符串进行加压加密操作，关闭gzip操作流失败："+e.getMessage());
				}
			}
		}
		return new BASE64Encoder().encode(out.toByteArray());
	}

	/**
	 * 将压缩并Base64后的字符串进行解密解压
	 * 
	 * @param compressedStr
	 *            待解密解压字符串
	 * @return
	 */
	public static final String ungzipString(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}
		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		GZIPInputStream gin = null;
		String decompressed = null;
		try {
			byte[] compressed = new BASE64Decoder().decodeBuffer(compressedStr);
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			gin = new GZIPInputStream(in);
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = gin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString("utf-8");
		} catch (IOException e) {
			System.out.println("对字符串进行解密解压操作失败："+e.getMessage());
			decompressed = null;
		} finally {
			if (gin != null) {
				try {
					gin.close();
				} catch (IOException e) {
					System.out.println("对字符串进行解密解压操作，关闭压缩流失败："+e.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("对字符串进行解密解压操作，关闭输入流失败："+e.getMessage());
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println("对字符串进行解密解压操作，关闭输出流失败："+e.getMessage());
				}
			}
		}
		return decompressed;
	}

	public static void main(String[] args) {
		System.out.println(ungzipString(gzipString("hello word")));
		System.out.println(ungzipString("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"));
	}
}

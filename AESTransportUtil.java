package cn.aes;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES对称加密/解密
 * @author sevennight
 *
 */
public class AESTransportUtil {
	 
	
	private static final String AES_TRANSPORT_KEYS ="893980u93489h8353o845b305j345i3485";
	
	/**
	  * 获取加密的key
	  * @return
	  */
	public static String getEncryptKey(){
		
		 StringBuilder keys=new StringBuilder();
	   	 Random random=new Random();
	   	 int length=AES_TRANSPORT_KEYS.length();
	   	 for(int i=0;i<16;i++){
	   		 int index=random.nextInt(length);
	   		 keys.append(AES_TRANSPORT_KEYS.charAt(index));
	   	 }
	   	 return keys.toString();
    }
	
	/**
	 * 加密方法
	 * @param content 加密内容
	 * @param pass 加密密匙
	 * @return
	 * @throws Exception String
	 */
	public static String encrypt(String content,String pass) throws Exception{
		
    	 Cipher aesECB = Cipher.getInstance("AES/CBC/PKCS5Padding");
         SecretKeySpec key = getSecretKeySpec(pass);
         IvParameterSpec spec=new IvParameterSpec("0102030405060708".getBytes());
         aesECB.init(Cipher.ENCRYPT_MODE, key,spec);
         byte[] result = aesECB.doFinal(content.getBytes());
         return parseByte2HexStr(result);
     }
	

	/**
	 * 解密方法
	 * @param content 解密内容
	 * @param pass 解密密匙
	 * @return
	 * @throws Exception String
	 */
	public static String derypt(String content,String pass) throws Exception{
		
    	 Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器 NoPadding   PKCS5Padding
         SecretKeySpec key = getSecretKeySpec(pass);
         IvParameterSpec spec=new IvParameterSpec("0102030405060708".getBytes());
         cipher.init(Cipher.DECRYPT_MODE, key,spec);// 初始化
         byte[] result = parseHexStr2Byte(content);
         return new String(cipher.doFinal(result)); // 解密
	}
     
	private static SecretKeySpec getSecretKeySpec(String key) throws Exception{
    	 
    	 byte[] arrBTmp = key.getBytes();
         byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）
         for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
             arrB[i] = arrBTmp[i];
         }
         SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
         return skeySpec;
	}
     
	/**
	 * 将二进制转换成16进制 
	 * @param buf
	 * @return String
	 */
	private static String parseByte2HexStr(byte buf[]) {  
		
		StringBuffer sb = new StringBuffer();  
		for (int i = 0; i < buf.length; i++) { 
			String hex = Integer.toHexString(buf[i] & 0xFF);  
			if (hex.length() == 1) {  
				hex = '0' + hex;  
			}  
			sb.append(hex.toUpperCase());  
		}  
		return sb.toString();  
	} 
     
     /**将16进制转换为二进制 
      * @param hexStr 
      * @return 
      */  
	private static byte[] parseHexStr2Byte(String hexStr) {
		
		if (hexStr.length() < 1)  
			return null;  
		byte[] result = new byte[hexStr.length()/2];  
		for (int i = 0;i< hexStr.length()/2; i++) {  
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);  
		}  
		return result;  
	} 
}

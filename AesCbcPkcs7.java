
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

public class AesCbcPkcs7 {

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     * Java 6支持PKCS5Padding填充方式
     * Bouncy Castle支持PKCS7Padding填充方式
     */
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    /**
     * 偏移量
     */
    private static final byte[] IV = "2624b9a9c447e587".getBytes(StandardCharsets.UTF_8);

    static {
        //如果是PKCS7Padding填充方式，必须加下面这行
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 获取key
     */
    private static SecretKeySpec getKey(String key) {
        byte[] arrBTmp = key.getBytes();
        // 创建一个空的16位字节数组（默认值为0），数据库为128位
        byte[] arrB = new byte[16];
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        return new SecretKeySpec(arrB, KEY_ALGORITHM);
    }

    /**
     * AES加密
     * @param plainText 明文
     * @param key 密钥
     * @return 加密内容
     * @throws Exception ex
     */
    public static String encrypt(String plainText, String key) throws Exception {
        // 密钥
        SecretKeySpec secretKeySpec = getKey(key);
        // 偏移量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        // 明文转字节数组
        byte[] sourceBytes = plainText.getBytes(StandardCharsets.UTF_8);
        // 加密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(sourceBytes);
        return Base64.encodeBase64String(decrypted);
    }

    /**
     * AES解密
     * @param encryptStr 密文
     * @param key 密钥
     * @return 解密内容
     * @throws Exception ex
     */
    public static String decrypt(String encryptStr, String key) throws Exception {
        // 密钥
        SecretKeySpec secretKeySpec = getKey(key);
        // 偏移量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        // 密文转字节数组
        byte[] sourceBytes = Base64.decodeBase64(encryptStr);
        // 解密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decoded = cipher.doFinal(sourceBytes);
        return new String(decoded, StandardCharsets.UTF_8);
    }
}

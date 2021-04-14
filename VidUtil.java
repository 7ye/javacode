package com.comm.util;

import java.util.UUID;

/**
 * 生成8位数ID（有重复概率）
 */
public class VidUtil {

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    /**
     * 获取ID
     * @param ph 占位符
     */
    public static String getVid(String... ph) {
        StringBuilder sb = new StringBuilder(ph[0]);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            sb.append(chars[x % 0x3E]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String payId = getVid("pay_G");
            System.out.println(payId);
        }
    }
}
package cc.sevennight;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 判断图片链接是否能访问
 * @author SEVENNIGHT
 * @date 2016-1-14下午3:31:17
 * 		@Revised 无
 *		@Revised content 无
 */
public class CheskUrl {
	
    private static URL urlStr;
    private static HttpURLConnection connection;
    private static int state = -1;
    private static String succ;
   
    public static String isConnect(String url) {
        int counts = 0;
        succ = null;
        if (url == null || url.length() <= 0) {
            return succ;
        }
        while (counts < 5) {
            try {
                urlStr = new URL(url);
                connection = (HttpURLConnection) urlStr.openConnection();
                state = connection.getResponseCode();
                if (state == 200) {
                    succ = connection.getURL().toString();
                }
                break;
            } catch (Exception ex) {
                counts++; 
                continue;
            }
        }
        return succ;
    }
    
    public static void main(String[] args) {
    	//结果为null则表示不能访问，URL地址无效；如果不为null则为有效地址，能访问
    	String rs = isConnect("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/icon-police.png?v=md5");
    	System.out.println("结果："+rs);
	}
}

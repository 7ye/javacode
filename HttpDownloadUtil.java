package cc.sevennight;

import java.io.*;
import java.net.*;
import java.util.*;

public class HttpDownloadUtil {
	
    private Vector<String> vRemoteHttpURL = new Vector<String>();
    private Vector<String> vLocalSaveFile = new Vector<String>();

    /**
     * 设置代理服务器
     * @Explain
     * 	设置代理的服务器域名与端口
     * @param sProxyHost 域名
     * @param sProxyPort 端口
     */
    public void setProxy(String sProxyHost, String sProxyPort) {
    	
    	if ((sProxyHost != null) && !sProxyHost.trim().equals("")) {
            if ((sProxyPort == null) || sProxyPort.trim().equals("")) {
                sProxyPort = "80";
            }
            System.getProperties().put("proxySet", "true");
            System.getProperties().put("proxyHost", sProxyHost);
            System.getProperties().put("proxyPort", sProxyPort);
        }
    }

    /**
     * 添加一个下载任务
     * @Explain
     * 	无功能说明
     * @param sRemoteHttpURL
     * @param sLocalSaveFile
     * @return boolean
     */
    public boolean addOneTask(String sRemoteHttpURL, String sLocalSaveFile) {
    	
        if ((sRemoteHttpURL == null) || sRemoteHttpURL.trim().equals("") || !sRemoteHttpURL.trim().substring(0, 7).equalsIgnoreCase("http://")) {
            System.out.println(" @> HttpDownload.addOneTask() : 源地址有误，不是一个有效的 http 地址！");
            return false;
        } else {
            if ((sLocalSaveFile == null) || sLocalSaveFile.trim().equals("")) {
                sLocalSaveFile = "./" + sRemoteHttpURL.substring(sRemoteHttpURL.lastIndexOf("/") + 1);
            }

            vRemoteHttpURL.add(sRemoteHttpURL);
            vLocalSaveFile.add(sLocalSaveFile);
        }

        return true;
    }

    /**
     * 清除下载列表
     */
    public void clearAllTasks() {
        vRemoteHttpURL.clear();
        vLocalSaveFile.clear();
    }

    /**
     * 根据列表下载资源
     * @return   boolean
     */
    public boolean downLoadByList() {
        for (int i = 0; i < vRemoteHttpURL.size(); i++) {
            
        	String sRemoteHttpURL = vRemoteHttpURL.get(i);
            String sLocalSaveFile = vLocalSaveFile.get(i);

            if (!saveToFile(sRemoteHttpURL, sLocalSaveFile)) {
                //System.out.println(" @> HttpDownload.downLoadByList() : 下载远程资源时出现异常！");
                return false;
            }
        }

        return true;
    }

    /**
     * 将 HTTP 资源保存为文件
     * @param    String
     * @param    String
     * @return   boolean
     */
    private boolean saveToFile(String sRemoteHttpURL, String sLocalSaveFile) {
       
    	if ((sRemoteHttpURL == null) || sRemoteHttpURL.trim().equals("")) {
            System.out.println(" @> HttpDownload.saveToFile() : 要下载的远程资源地址不能为空！");
            return false;
        }

        try {
            URL tURL = new URL(sRemoteHttpURL);
            HttpURLConnection tHttpURLConnection = (HttpURLConnection) tURL.openConnection();
            tHttpURLConnection.connect();

            BufferedInputStream tBufferedInputStream = new BufferedInputStream(tHttpURLConnection.getInputStream());
            FileOutputStream tFileOutputStream = new FileOutputStream(sLocalSaveFile);

            int nBufferSize = 1024 * 5;
            byte[] bufContent = new byte[nBufferSize];
            int nContentSize = 0;

            while ((nContentSize = tBufferedInputStream.read(bufContent)) != -1) {
                tFileOutputStream.write(bufContent, 0, nContentSize);
            }

            tFileOutputStream.close();
            tBufferedInputStream.close();
            tHttpURLConnection.disconnect();

            tURL = null;
            tHttpURLConnection = null;
            tBufferedInputStream = null;
            tFileOutputStream = null;
        } catch (Exception ex) {
            //System.out.println(" @> HttpDownload.saveToFile() : 下载远程资源时出现异常！");
            //System.out.println("    远程地址：" + sRemoteHttpURL);
            //System.out.println("    本地路径：" + sLocalSaveFile);
            return false;
        }

        return true;
    }
    
    public static void main(String[] argv) {
        HttpDownloadUtil tHttpDownload = new HttpDownloadUtil();
        //第一个是下载地址
        //第二个是保存地址
        tHttpDownload.addOneTask("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/icon-police.png?v=md5","F:/logo.png");

        boolean bylist = tHttpDownload.downLoadByList();
        tHttpDownload = null;

        System.out.println(bylist);
    }
}

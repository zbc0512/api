package io.zbc.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class Spider {
    public static String sendGet(String url) {
        // 定义一个字符串用来存储网页内容
        String result = "";
        // 定义一个缓冲字符输入流
        BufferedReader in = null;
        BufferedWriter writer = null;
        try {
            // 将string转成url对象
            URL realUrl = new URL(url);
            // 创建代理服务器
            InetSocketAddress addr = new InetSocketAddress("proxy.iccnet.com.cn", 3128);
            // Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr); // Socket 代理
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
            // "password"));// 设置代理的用户和密码
            // 初始化一个链接到那个url的连接
            URLConnection connection = realUrl.openConnection(proxy);
            // 开始实际的连接
            connection.connect();
            // 初始化 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            // 用来临时存储抓取到的每一行的数据
            String line;
            while ((line = in.readLine()) != null) {
                // 遍历抓取到的每一行并将其存储到result里面
                result += line;
            }
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test.html")));
                for(int i=0; i < result.length(); i++){
                    char c = result.charAt(i);
                    writer.write(c);
                }
                writer.flush();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}

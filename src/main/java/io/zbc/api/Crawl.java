package io.zbc.api;

import java.util.List;

import io.zbc.api.model.AQI;

public class Crawl {
    public static void main(String[] args) {
        List<AQI> list = aqi();
    }

    static List<AQI> aqi() {
        // 定义即将访问的链接
        String city = "beijing";
        String url = "http://pm25.in/" + city;
        // 访问链接并获取页面内容
        String content = Spider.sendGet(url);
        // 获取该页面的所有的知乎对象
        List<AQI> aqis = PM25.getAQIs(content);
        // 打印结果
        for (AQI aqi : aqis) {
            System.out.println(aqi);
        }
        return aqis;
    }

}

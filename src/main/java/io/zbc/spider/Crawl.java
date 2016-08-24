package io.zbc.spider;

import java.util.ArrayList;

import io.zbc.spider.spiders.PM25;

public class Crawl {
    public static void main(String[] args) {
        ArrayList list = aqi();
    }

    static ArrayList<PM25> aqi() {
        // 定义即将访问的链接
        String city = "beijing";
        String url = "http://pm25.in/" + city;
        // 访问链接并获取页面内容
        String content = Spider.sendGet(url);
        // 获取该页面的所有的知乎对象
        ArrayList<PM25> pm25s = PM25.getTbody(content);
        // 打印结果
        for (PM25 pm25 : pm25s) {
            System.out.println(pm25);
        }
        return pm25s;
    }

}

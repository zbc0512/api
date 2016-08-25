package io.zbc.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.zbc.api.model.AQI;

public class AQIService {

    // 获取<tbody>
    public static List<AQI> getAQIs(String content) {
        String tbody = "";// <tbody>
        List<String> trs = new ArrayList<String>();// <tr>
        List<AQI> aqis = new ArrayList<AQI>();// <td>
        Pattern pattern;
        Matcher matcher;
        // 匹配<tbody>
        pattern = Pattern.compile("<tbody[^>]*>([\\s\\S]*?)</tbody>");
        matcher = pattern.matcher(content);
        // 存在匹配成功的对象
        if (matcher.find()) {
            tbody = matcher.group(1);
        }
        // 匹配<tr>
        pattern = Pattern.compile("<tr>([\\s\\S]*?)</tr>");
        matcher = pattern.matcher(tbody);
        while (matcher.find()) {
            trs.add(matcher.group(1));
        }
        // 匹配<td>
        for(String tr : trs){
            pattern = Pattern.compile("<td[^>]*>([\\s\\S]*?)</td>");
            matcher = pattern.matcher(tr);
            String td = "";
            while (matcher.find()) {
                td += matcher.group(1) + ",";
            }
            String[] tdList = td.substring(0, td.length()-1).replaceAll("_", "-1").split(",");
            AQI aqi = new AQI();
            aqi.setPosition_name(tdList[0]);
            aqi.setAqi(Integer.parseInt(tdList[1]));
            aqi.setQuality(tdList[2]);
            aqi.setPrimary_pollutant(tdList[3]);
            aqi.setPm2_5(Integer.parseInt(tdList[4]));
            aqi.setPm10(Integer.parseInt(tdList[5]));
            aqi.setCo(Double.parseDouble(tdList[6]));
            aqi.setNo2(Integer.parseInt(tdList[7]));
            aqi.setO3(Integer.parseInt(tdList[8]));
            aqi.setO3_8h(Integer.parseInt(tdList[9]));
            aqi.setSo2(Integer.parseInt(tdList[10]));
            aqis.add(aqi);
        }
        return aqis;
    }

}

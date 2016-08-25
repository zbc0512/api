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

public class PM25 {

    // 初始化数据
    public static List<AQI> getAQI(String tbody) {
        List<String> trs = new ArrayList<String>();// <tr>
        List<String> tds = new ArrayList<String>();// <td>
        List<AQI> aqis = new ArrayList<AQI>();// <td>
        Pattern pattern;
        Matcher matcher;
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
            String[] tdList = td.substring(0, td.length()-1).split(",");
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

    // 获取<tbody>
    public static List<AQI> getAQIs(String content) {
        // 预定义一个ArrayList来存储结果
        List<AQI> results = new ArrayList<AQI>();
        // 用来匹配<tbody>
        Pattern pattern = Pattern.compile("<tbody[^>]*>([\\s\\S]*?)</tbody>");
        Matcher matcher = pattern.matcher(content);
        // 是否存在匹配成功的对象
        Boolean isFind = matcher.find();
        while (isFind) {
            // 添加成功匹配的结果
            results = getAQI(matcher.group(1));
            // 继续查找下一个匹配对象
            isFind = matcher.find();
        }
        return results;
    }

    //@Override
    public String toString2() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/api";
        String jdbcDriver = "com.mysql.jdbc.Driver";
        String jdbcUser = "root";
        String jdbcPassword = "";
        String sql = "";
        Connection conn = null;
        try {
            new com.mysql.jdbc.Driver();
            conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            Statement st = conn.createStatement();
            int result = st.executeUpdate(sql);
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

}

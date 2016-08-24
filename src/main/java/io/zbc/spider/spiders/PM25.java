package io.zbc.spider.spiders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PM25 {
    public List<String> trs = new ArrayList<String>();// <tr>
    public List<String> tds = new ArrayList<String>();// <td>

    // 初始化数据
    public PM25(String tbody) {
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
                tds.add(td);
            }
        }
        
    }

    // 获取<tbody>
    public static ArrayList<PM25> getTbody(String content) {
        // 预定义一个ArrayList来存储结果
        ArrayList<PM25> results = new ArrayList<PM25>();
        // 用来匹配<tbody>
        Pattern pattern = Pattern.compile("<tbody[^>]*>([\\s\\S]*?)</tbody>");
        Matcher matcher = pattern.matcher(content);
        // 是否存在匹配成功的对象
        Boolean isFind = matcher.find();
        while (isFind) {
            // 定义一个PM25对象来存储抓取到的信息
            PM25 pm25Temp = new PM25(matcher.group(1));
            // 添加成功匹配的结果
            results.add(pm25Temp);
            // 继续查找下一个匹配对象
            isFind = matcher.find();
        }
        return results;
    }

    @Override
    public String toString() {
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

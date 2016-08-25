package io.zbc.api.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class AQI {

    private int aqi_id;
    private String city_code; // 城市代码
    private String station_code; // 监测点编码
    private String position_name; // 监测点名称
    private int aqi; // 空气质量指数(AQI)，即air quality index，是定量描述空气质量状况的无纲量指数
    private String quality; // 空气质量指数类别，有“优、良、轻度污染、中度污染、重度污染、严重污染”6类
    private String primary_pollutant; // 首要污染物
    private int pm2_5; // 颗粒物（粒径小于等于2.5μm）1小时平均
    private int pm10; // 颗粒物（粒径小于等于10μm）1小时平均
    private double co; // 一氧化碳1小时平均
    private int no2; // 二氧化氮1小时平均
    private int o3; // 臭氧1小时平均
    private int o3_8h; // 臭氧8小时滑动平均
    private int so2; // 二氧化硫1小时平均
    private Date time_point; // 数据发布的时间

    @Override
    public String toString() {
        return position_name + "," + aqi + "," + quality + "," + primary_pollutant + ","
                + pm2_5 + "," + pm10 + "," + co + "," + no2 + "," + o3 + "," + o3_8h + "," + so2;
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

    public int getAqi_id() {
        return aqi_id;
    }
    public void setAqi_id(int aqi_id) {
        this.aqi_id = aqi_id;
    }
    public String getCity_code() {
        return city_code;
    }
    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }
    public String getStation_code() {
        return station_code;
    }
    public void setStation_code(String station_code) {
        this.station_code = station_code;
    }
    public String getPosition_name() {
        return position_name;
    }
    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }
    public int getAqi() {
        return aqi;
    }
    public void setAqi(int aqi) {
        this.aqi = aqi;
    }
    public String getQuality() {
        return quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }
    public String getPrimary_pollutant() {
        return primary_pollutant;
    }
    public void setPrimary_pollutant(String primary_pollutant) {
        this.primary_pollutant = primary_pollutant;
    }
    public int getPm2_5() {
        return pm2_5;
    }
    public void setPm2_5(int pm2_5) {
        this.pm2_5 = pm2_5;
    }
    public int getPm10() {
        return pm10;
    }
    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }
    public double getCo() {
        return co;
    }
    public void setCo(double co) {
        this.co = co;
    }
    public int getNo2() {
        return no2;
    }
    public void setNo2(int no2) {
        this.no2 = no2;
    }
    public int getO3() {
        return o3;
    }
    public void setO3(int o3) {
        this.o3 = o3;
    }
    public int getO3_8h() {
        return o3_8h;
    }
    public void setO3_8h(int o3_8h) {
        this.o3_8h = o3_8h;
    }
    public int getSo2() {
        return so2;
    }
    public void setSo2(int so2) {
        this.so2 = so2;
    }
    public Date getTime_point() {
        return time_point;
    }
    public void setTime_point(Date time_point) {
        this.time_point = time_point;
    }
    
}

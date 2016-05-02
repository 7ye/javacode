package com.common.util;


/**
 * 经纬度距离计算
 *
 * @author sevennight
 *
 */
public class MapDistance {

    //地球半径
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return (d * Math.PI) / 180.0;
    }

    /**
     * 获取两个经纬度之间的距离
     * @param longitude1 第一点经度
     * @param latitude1  第一点维度
     * @param longitude2 第二点经度
     * @param latitude2  第二点维度
     * @param unit 单位（1千米、2米）
     * @return
     */
    public static double getShortestDistance(double longitude1,double latitude1, 
        double longitude2, double latitude2, int unit) {
        
        double radLat1 = rad(latitude1);
        double radLat2 = rad(latitude2);
        double a = radLat1 - radLat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                    (Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(
                            b / 2), 2))));

        if (unit == 1) {
            s = s * EARTH_RADIUS;
        } else if (unit == 2) {
            s = s * EARTH_RADIUS;
            s = s * 1000; //换算成米
        } else {
            //类型有误
            s = 0;
        }

        return s;
    }

    public static void main(String[] args) {
        //成都市：银泰香樟林  104.024815,30.636323
        //成都市：环球中心 104.063392,30.568751
        //百度测距：8371.78 米
        //方法测距：8.38116669640745 or 8381.16669640745
        System.out.println(getShortestDistance(104.024815d, 30.636323d,104.063392d, 30.568751d, 2));
    }
}

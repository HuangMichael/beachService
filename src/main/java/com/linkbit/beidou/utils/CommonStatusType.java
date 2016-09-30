package com.linkbit.beidou.utils;

/**
 * Created by HUANGBIN on 2016/5/17.
 * 公共状态类型
 */
public final class CommonStatusType {


    public static String STATUS_ON = "1";  // 启用
    public static String STATUS_OFF = "0";//禁用


    public static String STATUS_YES = "1";  //是
    public static String STATUS_NO = "0";//否


    public static String ORDER_CREATED = "0";  //新建工单
    public static String ORDER_DISTRIBUTED = "1";//工单分配状态
    public static String ORDER_FIXING = "2";//工单维修状态
    public static String ORDER_SUSPENDED = "3";//工单暂停状态
    public static String ORDER_FIXED = "4";//工单已完工

    //报修方式
    public static String REPORT_BY_EQ = "s";//按照设备报修
    public static String REPORT_BY_LOC = "w";//按照位置报修

    //报修车状态
    public static String CART_CREATED = "0";//报修车记录创建
    public static String CART_COMMITED = "1";//报修车记录提交


    //报修单明细状态
    public static String REPORT_CREATED = "0";//报修单记录创建
    public static String REPORT_COMMITED = "1";//报修单记录提交


    //维修单明细状态
    public static String FIX_CREATED = "0";//维修单记录创建
    public static String FIX_ACCOMPLISHED = "1";//维修单完工
    public static String FIX_DISTRIBUTED = "2";//维修单分配
    public static String FIX_PAUSED = "3";//维修单暂停

    public static String EQ_ABNORMAL = "0";//设备不正常
    public static String EQ_NORMAL = "1";  //设备正常的
    public static String EQ_REPORTED = "2";//设备报修的
    public static String EQ_SCRAPPED = "3";//设备报废


    public static String LOC_NORMAL = "1";  //位置正常的
    public static String LOC_ABNORMAL = "2";//位置不正常


}

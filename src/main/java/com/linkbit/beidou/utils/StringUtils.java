package com.linkbit.beidou.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public class StringUtils {


    /**
     * @param arrayStr  数组字符串  a,b,c
     * @param separator ,
     * @return 字符串转数组
     */
    public static String[] str2Array(String arrayStr, String separator) {
        String[] array = arrayStr.split(separator);
        return array;
    }


    /**
     * @param arrayStr  数组字符串  1,2,3
     * @param separator ,
     * @return 字符串转集合 Long
     */
    public static List<Long> str2List(String arrayStr, String separator) {
        List<Long> longList = new ArrayList<Long>();
        String[] array = arrayStr.split(separator);
        for (String str : array) {
            longList.add(Long.parseLong(str));
        }
        return longList;
    }


    /**
     * @param arrayStr  数组字符串  1,2,3
     * @param separator ,
     * @return 字符串转集合 Long
     */
    public static String replaceStrSeparator(String arrayStr, String separator) {

        String array[] = arrayStr.split(separator);
        String newStr ="";

        for (String s : array) {
            if (s != null && !s.equals("")) {
                newStr += s + ",";
            }
        }

        System.out.println(newStr);
        newStr = "(" + newStr.substring(0, newStr.length() - 1) + ")";

        System.out.println(newStr);
        return newStr;
    }
}

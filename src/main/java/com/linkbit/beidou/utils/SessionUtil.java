package com.linkbit.beidou.utils;


import com.linkbit.beidou.domain.locations.Locations;
import com.linkbit.beidou.domain.user.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by HUANGBIN on 2016/2/18 0018.
 * 获取当前的session
 */
public class SessionUtil {
    /**
     * 获取当前上下文路径
     */
    public static String getContextPath(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("/");
        return serverRealPath;
    }


    public static String getAbsolutePath(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("/");
        return serverRealPath;
    }

    /**
     * 获取当前用户信息
     */
    public static User getCurrentUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("currentUser");
        return user;
    }

    /**
     * 获取当前用户信息
     */
    public static User getCurrentUserBySession(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        return user;
    }


    /**
     * 获取当前用户信息
     */
    public static String getCurrentUserLocationBySession(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        String location = null;
        if (user != null) {
            location = user.getLocation();
        }
        return location;
    }

}

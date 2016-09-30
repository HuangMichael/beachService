package com.linkbit.beidou.liseners;


import com.linkbit.beidou.utils.DateUtils;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;


/**
 * 监听session的创建与销毁
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println(DateUtils.convertDate2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "------------sessionCreated----------------- " );
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println(DateUtils.convertDate2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "-------------sessionDestroyed----------------");

    }

}

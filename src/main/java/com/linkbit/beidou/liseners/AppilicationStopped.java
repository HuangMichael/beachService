package com.linkbit.beidou.liseners;

import com.linkbit.beidou.utils.DateUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

import java.util.Date;

/**
 * Created by huangbin on 2016/7/18.
 */
public class AppilicationStopped implements ApplicationListener<ContextStoppedEvent> {

    public void onApplicationEvent(ContextStoppedEvent event) {
        System.out.println(DateUtils.convertDate2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "----------session stop------------------- ");
    }
}

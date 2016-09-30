package com.linkbit.beidou.tasks;

import com.linkbit.beidou.service.workOrder.WorkOrderFixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 维修任务状态定时更新
 *
 * @author huangbin
 * @create 2016-09-05 14:08
 **/
@Component
public class FixtTaskStatusSchedule {

    private static final Logger log = LoggerFactory.getLogger(FixtTaskStatusSchedule.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Autowired
    WorkOrderFixService workOrderFixService;
    @Scheduled(cron = "0 35 14 * * *")
    public void updateFixTaskStatus() {
        log.info("定时更新任务状态---- {}", dateFormat.format(new Date()));
        workOrderFixService. updateFixTaskStatus("已派工");
    }
}

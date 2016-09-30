package com.linkbit.beidou.service.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by huangbin  on 2016/5/20.
 * 日志对象
 */
@Service
public class BaseService {


    protected Log log = LogFactory.getLog(this.getClass());

}

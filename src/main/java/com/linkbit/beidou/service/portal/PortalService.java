package com.linkbit.beidou.service.portal;

/**
 * Created by Administrator on 2016/7/24.
 */

import com.linkbit.beidou.dao.workOrder.*;
import com.linkbit.beidou.domain.workOrder.*;
import com.linkbit.beidou.service.app.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin on 2016/7/24.
 */
@Service
public class PortalService extends BaseService {


    @Autowired
    VworkOrderLineNumReportRepository vworkOrderLineNumReportRepository;
    @Autowired
    VworkOrderLineNumFixedRepository vworkOrderLineNumFixedRepository;
    @Autowired
    VworkOrderLineNumFixingRepository vworkOrderLineNumFixingRepository;
    @Autowired
    VworkOrderLineNumSuspendRepository vworkOrderLineNumSuspendRepository;
    @Autowired
    VworkOrderLineNumAbortRepository vworkOrderLineNumAbortRepository;


    /**
     * @return 获取线路总报修数量
     */
    public List<VworkOrderLineNumReport> getLineReportNum() {

        return vworkOrderLineNumReportRepository.findAll();
    }


    /**
     * @return 获取线路总完工数量
     */
    public List<VworkOrderLineNumFixed> getLineFixedNum() {

        return vworkOrderLineNumFixedRepository.findAll();
    }


    /**
     * @return 获取线路总维修中数量
     */
    public List<VworkOrderLineNumFixing> getLineFixingNum() {

        return vworkOrderLineNumFixingRepository.findAll();
    }

    /**
     * @return 获取线路总维修中数量
     */
    public List<VworkOrderLineNumAbort> getLineAbortNum() {


        return vworkOrderLineNumAbortRepository.findAll();
    }


    /**
     * @return 获取线路总暂停数量
     */
    public List<VworkOrderLineNumSuspend> getLineSuspendNum() {

        return vworkOrderLineNumSuspendRepository.findAll();
    }

}

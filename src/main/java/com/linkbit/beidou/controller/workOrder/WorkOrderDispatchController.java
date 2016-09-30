package com.linkbit.beidou.controller.workOrder;


import com.linkbit.beidou.dao.outsourcingUnit.OutsourcingUnitRepository;
import com.linkbit.beidou.domain.outsourcingUnit.OutsourcingUnit;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.domain.workOrder.WorkOrderReportCart;
import com.linkbit.beidou.service.workOrder.WorkOrderDispatchService;
import com.linkbit.beidou.service.workOrder.WorkOrderReportCartService;
import com.linkbit.beidou.service.workOrder.WorkOrderReportService;
import com.linkbit.beidou.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/workOrderDispatch")
public class WorkOrderDispatchController {


    @Autowired
    WorkOrderDispatchService workOrderDispatchService;
    @Autowired
    WorkOrderReportService workOrderReportService;

    @Autowired
    OutsourcingUnitRepository outsourcingUnitRepository;

    @Autowired
    WorkOrderReportCartService workOrderReportCartService;

    /**
     * @param modelMap
     * @return 显示报修工单明细列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap, HttpSession session) {
        User user = SessionUtil.getCurrentUserBySession(session);
        //查询当前登录用户location下的未完工的报修单明细
        List<WorkOrderReportCart> workOrderReportDetailList = null;

        List<OutsourcingUnit> unitList = null;
        if (user != null && user.getLocation() != null) {
            workOrderReportDetailList = workOrderReportCartService.findByLocationStartingWithAndNodeState(user.getLocation(), "已报修");
            unitList = outsourcingUnitRepository.findByStatus("1");
            modelMap.put("workOrderReportDetailList", workOrderReportDetailList);
            modelMap.put("unitList", unitList);
        }
        return "/workOrderDispatcher/list";
    }


    /**
     * 加载将要生成维修单的维修信息
     */
    @RequestMapping(value = "/loadOrderDesc", method = RequestMethod.POST)
    public String loadOrderDesc(@RequestParam("ids") String ids, ModelMap modelMap) {
        List<WorkOrderReportCart> WorkOrderReportDetailList = workOrderReportCartService.findWorkOrderReportDetailByIds(ids);
        modelMap.put("WorkOrderReportDetailList", WorkOrderReportDetailList);
        return "/workOrderDispatcher/orderDescList";
    }


    /**
     * @param detailId 维修明细id
     * @param unitId   维修单位ID
     * @return
     */
    @RequestMapping(value = "/updateDetailUnit", method = RequestMethod.POST)
    @ResponseBody
    public WorkOrderReportCart updateDetailUnit(@RequestParam("detailId") Long detailId, @RequestParam("unitId") Long unitId) {
        WorkOrderReportCart workOrderReportCart = workOrderDispatchService.updateDetailUnit(detailId, unitId);
        return workOrderReportCart;
    }


    /**
     * 加载将要提交的报修车确认信息
     */
    @RequestMapping(value = "/loadCommitPage", method = RequestMethod.POST)
    public String loadCommitPage(@RequestParam("ids") String ids, ModelMap modelMap) {
        List<WorkOrderReportCart> workOrderReportDetailList = workOrderDispatchService.findWorkOrderReportCartByIds(ids);
        modelMap.put("workOrderReportDetailList", workOrderReportDetailList);
        return "/workOrderDispatcher/orderCommitList";
    }
}

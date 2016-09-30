package com.linkbit.beidou.controller.portal;


import com.linkbit.beidou.domain.workOrder.*;
import com.linkbit.beidou.service.portal.PortalService;
import com.linkbit.beidou.service.workOrder.WorkOrderReportCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/portal")
@SessionAttributes("menuList")
public class PortalController {


    @Autowired
    WorkOrderReportCartService workOrderReportCartService;


    @Autowired
    PortalService portalService;


    @RequestMapping(value = "/index")
    public String index(ModelMap modelMap) {

        return "/portal/index";
    }

    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap) {

        return "/portal/list";
    }


    @RequestMapping(value = "/findTopEqClass", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> findTopNReportCartByEqClass() {
        return workOrderReportCartService.findTopNReportCartByEqClass();
    }


    @RequestMapping(value = "/getLastNMonth/{n}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getLastNMonth(@PathVariable("n") int n) {
        return workOrderReportCartService.getLastNMonthName(n);
    }


    @RequestMapping(value = "/getLineReportNum", method = RequestMethod.GET)
    @ResponseBody
    public List<VworkOrderLineNumReport> getLineReportNum() {
        return portalService.getLineReportNum();
    }


    @RequestMapping(value = "/getLineFixedNum", method = RequestMethod.GET)
    @ResponseBody
    public List<VworkOrderLineNumFixed> getLineFixedNum() {
        return portalService.getLineFixedNum();
    }


    @RequestMapping(value = "/getLineFixingNum", method = RequestMethod.GET)
    @ResponseBody
    public List<VworkOrderLineNumFixing> getLineFixingNum() {
        return portalService.getLineFixingNum();
    }

    @RequestMapping(value = "/getLineAbortNum", method = RequestMethod.GET)
    @ResponseBody
    public List<VworkOrderLineNumAbort> getLineAbortNum() {
        return portalService.getLineAbortNum();
    }


    @RequestMapping(value = "/getLineSuspendNum", method = RequestMethod.GET)
    @ResponseBody
    public List<VworkOrderLineNumSuspend> getLineSuspendNum() {
        return portalService.getLineSuspendNum();
    }


}


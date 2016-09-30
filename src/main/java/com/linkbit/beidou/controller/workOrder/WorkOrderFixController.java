package com.linkbit.beidou.controller.workOrder;


import com.linkbit.beidou.dao.workOrder.WorkOrderHistoryRepository;
import com.linkbit.beidou.dao.workOrder.WorkOrderReportCartRepository;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.domain.workOrder.VworkOrderFixBill;
import com.linkbit.beidou.domain.workOrder.WorkOrderHistory;
import com.linkbit.beidou.domain.workOrder.WorkOrderReportCart;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.workOrder.WorkOrderFixService;
import com.linkbit.beidou.service.workOrder.WorkOrderReportCartService;
import com.linkbit.beidou.service.workOrder.WorkOrderReportService;
import com.linkbit.beidou.utils.DateUtils;
import com.linkbit.beidou.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/workOrderFix")
public class WorkOrderFixController {


    @Autowired
    WorkOrderReportService workOrderReportService;


    @Autowired

    WorkOrderFixService workOrderFixService;


    @Autowired
    WorkOrderHistoryRepository workOrderHistoryRepository;

    @Autowired
    WorkOrderReportCartRepository workOrderReportCartRepository;
    @Autowired
    WorkOrderReportCartService workOrderReportCartService;

    /**
     * @param modelMap
     * @return 显示维修工单列表
     */
    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    public String list2(ModelMap modelMap, HttpSession session) {
        User user = SessionUtil.getCurrentUserBySession(session);
        String location = user.getLocation();
        //过滤显示当前用户location数据 找出不完工的单子

       /* List<WorkOrderReportCart> workOrderFixDetailListList0 = workOrderFixService.findDistributedOrders(location);
        List<WorkOrderReportCart> workOrderFixDetailListList1 = workOrderFixService.findFinishOrders(location);
        List<WorkOrderReportCart> workOrderFixDetailListList2 = workOrderFixService.findPausedOrders(location);
        List<WorkOrderReportCart> workOrderFixDetailListList3 = workOrderFixService.findRemovedOrders(location);
        modelMap.put("workOrderFixDetailListList0", workOrderFixDetailListList0);
        modelMap.put("workOrderFixDetailListList1", workOrderFixDetailListList1);
        modelMap.put("workOrderFixDetailListList2", workOrderFixDetailListList2);
        modelMap.put("workOrderFixDetailListList3", workOrderFixDetailListList3);
        //查询出已派工的维修单*/
        return "/workOrderFix/list";
    }


    /**
     * @param modelMap
     * @return 显示维修工单列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap, HttpSession session) {
        User user = SessionUtil.getCurrentUserBySession(session);
        String location = user.getLocation();
        //过滤显示当前用户location数据 找出不完工的单子
        List<VworkOrderFixBill> workOrderFixDetailListList0 = workOrderFixService.findByNodeStateAndLocation("已派工", location);
        List<VworkOrderFixBill> workOrderFixDetailListList1 = workOrderFixService.findByNodeStateAndLocation("已完工", location);
        List<VworkOrderFixBill> workOrderFixDetailListList2 = workOrderFixService.findByNodeStateAndLocation("已暂停", location);
        List<VworkOrderFixBill> workOrderFixDetailListList3 = workOrderFixService.findByNodeStateAndLocation("已取消", location);
        modelMap.put("workOrderFixDetailListList0", workOrderFixDetailListList0);
        modelMap.put("workOrderFixDetailListList1", workOrderFixDetailListList1);
        modelMap.put("workOrderFixDetailListList2", workOrderFixDetailListList2);
        modelMap.put("workOrderFixDetailListList3", workOrderFixDetailListList3);
        //查询出已派工的维修单
        return "/workOrderFix/list";
    }


    /**
     * @param fixId
     * @return 完成单个维修工单
     */
    @RequestMapping(value = "/finishDetail", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject finishDetail(@RequestParam Long fixId, @RequestParam String fixDesc) {
        WorkOrderReportCart workOrderReportCart = workOrderReportCartRepository.findById(fixId);
        WorkOrderHistory workOrderHistory = workOrderFixService.handleWorkOrder(workOrderReportCart, fixDesc, "已完工");
        return getReturnType(workOrderHistory != null, "维修单完工成功!", "维修单完工失败!");
    }


    /**
     * @param fixId 维修单id
     * @param fixDesc 维修描述
     * @return 暂停单个维修工单
     */
    @RequestMapping(value = "/pauseDetail", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject pauseDetail(@RequestParam Long fixId, @RequestParam String fixDesc) {
        WorkOrderReportCart workOrderReportCart = workOrderReportCartRepository.findById(fixId);
        WorkOrderHistory workOrderHistory = workOrderFixService.handleWorkOrder(workOrderReportCart, fixDesc, "已暂停");
        return getReturnType(workOrderHistory != null, "维修单暂停成功!", "维修单暂停失败!");
    }

    /**
     * @param fixId
     * @param fixDesc
     * @return 取消单个维修工单
     */
    @RequestMapping(value = "/abortDetail", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject abortDetail(@RequestParam Long fixId, @RequestParam String fixDesc) {
        WorkOrderReportCart workOrderReportCart = workOrderReportCartRepository.findById(fixId);
        WorkOrderHistory workOrderHistory = workOrderFixService.handleWorkOrder(workOrderReportCart, fixDesc, "已取消");
        return getReturnType(workOrderHistory != null, "维修单取消成功!", "维修单取消失败!");
    }


    /**
     * @param orderId
     * @return 获取次日零点时间
     */
    @RequestMapping(value = "/getCellingDate/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public Date getCellingDate(@PathVariable("orderId") Long orderId) {
        //根据维修单id查询分配订单时间
        Date outDate = null;
        WorkOrderReportCart workOrderReportCart = workOrderReportCartRepository.findById(orderId);
        List<WorkOrderHistory> workOrderHistoryList = workOrderHistoryRepository.findByWorkOrderReportCart(workOrderReportCart);
        if (!workOrderHistoryList.isEmpty()) {
            Date dispatchTime = workOrderHistoryList.get(0).getNodeTime();
            outDate = DateUtils.getCellingDate(dispatchTime);
        }
        return outDate;
    }

    /**
     * @param orderId     工单id
     * @param deadLineStr 截止日期字符串
     * @return 更新截止日期
     */
    @RequestMapping(value = "/updateDeadLine", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject updateDeadLine(@RequestParam("orderId") Long orderId, @RequestParam("deadLine") String deadLineStr) {
        //根据维修单id查询分配订单时间
        WorkOrderReportCart workOrderReportCart = workOrderFixService.updateDeadLine(orderId, deadLineStr);
        return getReturnType(workOrderReportCart.getDeadLine() != null,"维修单维修时限修改成功","维修单维修时限修改失败");
    }


    /**
     * @param result      返回结果
     * @param successDesc 执行成功后描述
     * @param failureDesc 执行失败时描述
     * @return
     */
    public ReturnObject getReturnType(Boolean result, String successDesc, String failureDesc) {
        ReturnObject returnObject = new ReturnObject();
        String resultDesc = result ? successDesc : failureDesc;
        returnObject.setResult(result);
        returnObject.setResultDesc(resultDesc);
        return returnObject;
    }

}

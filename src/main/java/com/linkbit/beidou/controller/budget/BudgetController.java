package com.linkbit.beidou.controller.budget;


import com.linkbit.beidou.domain.app.MyPage;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.budget.BudgetBill;
import com.linkbit.beidou.domain.budget.VbudgetBill;
import com.linkbit.beidou.domain.workOrder.VworkOrderReportBill;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.budge.BudgeService;
import com.linkbit.beidou.service.commonData.CommonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 采购申请
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/budget")
public class BudgetController {
    @Autowired
    BudgeService budgeService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    CommonDataService commonDataService;

    /**
     * 初始化分页查询采购单信息
     *
     * @param current
     * @param rowCount
     * @param searchPhrase
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public MyPage data(@RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount, @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        Page<VbudgetBill> page = budgeService.findByAccessoryNameContains(searchPhrase, new PageRequest(current - 1, rowCount.intValue()));
        MyPage myPage = new MyPage();
        myPage.setRows(page.getContent());
        myPage.setRowCount(rowCount);
        myPage.setCurrent(current);
        myPage.setTotal(page.getTotalElements());
        return myPage;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpSession httpSession, ModelMap modelMap) {
        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);
        return "/budget/list";
    }

    /**
     * @param id 根据id查询
     * @return
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BudgetBill findById(@PathVariable("id") Long id) {
        return budgeService.findById(id);
    }


    /**
     * @param budgetBill 保存或者更新采购单
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(BudgetBill budgetBill) {
        BudgetBill budgetObj;
        String operation = "保存";
        if (budgetBill.getId() != null) {
            operation = "更新";
        }
        budgetObj = budgeService.save(budgetBill);
        return commonDataService.getReturnType(budgetObj != null, "采购申请单" + operation + "成功!", "采购申请单" + operation + "失败!");

    }


    /**
     * @param id 根据id删除采购单
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return budgeService.delete(id);
    }


    /**
     * @return 查询所有的id
     */
    @RequestMapping(value = "/findAllIds", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> findAllIds() {
        return budgeService.findAllIds();
    }

}

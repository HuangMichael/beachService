package com.linkbit.beidou.controller.ecbudget;


import com.linkbit.beidou.domain.EcBudget.EcBudgetBill;
import com.linkbit.beidou.domain.EcBudget.VEcBudgetBill;
import com.linkbit.beidou.domain.app.MyPage;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.budge.EcBudgeService;
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
 * 低值易耗品采购申请
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/ecbudget")
public class EcbudgetController {

    @Autowired
    EcBudgeService ecBudgeService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    CommonDataService commonDataService;

    /**
     * 分页查询
     *
     * @param current      当前页
     * @param rowCount     每页条数
     * @param searchPhrase 查询关键字
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public MyPage data(@RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount, @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        Page<VEcBudgetBill> page = ecBudgeService.findByEcnameContains(searchPhrase, new PageRequest(current - 1, rowCount.intValue()));
        MyPage myPage = new MyPage();
        myPage.setRows(page.getContent());
        myPage.setRowCount(rowCount);
        myPage.setCurrent(current);
        myPage.setTotal(page.getTotalElements());
        return myPage;
    }


    /**
     * @param httpSession
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpSession httpSession, ModelMap modelMap) {
        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);
        return "/ecbudget/list";
    }

    /**
     * @param id 根据id查询
     * @return
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EcBudgetBill findById(@PathVariable("id") Long id) {
        return ecBudgeService.findById(id);
    }


    /**
     * @param budgetBill 保存或者更新低值易耗品采购单
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(EcBudgetBill budgetBill) {
        EcBudgetBill budgetObj;
        String operation = "保存";
        if (budgetBill.getId() != null) {
            operation = "更新";
        }
        budgetObj = ecBudgeService.save(budgetBill);
        return commonDataService.getReturnType(budgetObj != null, "低值易耗品采购申请单" + operation + "成功!", "低值易耗品采购申请单" + operation + "失败!");
    }


    /**
     * @param id 根据id查询
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return ecBudgeService.delete(id);
    }


    /**
     * @return 查询所有的id集合
     */
    @RequestMapping(value = "/findAllIds", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> findAllIds() {
        return ecBudgeService.findAllIds();
    }

}

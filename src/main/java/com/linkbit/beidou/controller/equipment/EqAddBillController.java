package com.linkbit.beidou.controller.equipment;


import com.linkbit.beidou.domain.app.MyPage;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.equipments.EqAddBill;
import com.linkbit.beidou.domain.equipments.VEqAddBill;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.commonData.CommonDataService;
import com.linkbit.beidou.service.equipments.EqAddBillService;
import com.linkbit.beidou.service.equipments.EqUpdateBillService;
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
 * 设备新置
 * 2016年9月28日13:46:03
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/eqAddBill")
public class EqAddBillController {
    @Autowired
    EqUpdateBillService eqUpdateBillService;

    @Autowired
    EqAddBillService eqAddBillService;

    @Autowired
    ResourceService resourceService;
    @Autowired
    CommonDataService commonDataService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpSession httpSession, ModelMap modelMap) {
        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);
        return "/eqAddBill/list";
    }

    /**
     * 初始化分页查询设备新置申请单信息
     *
     * @param current
     * @param rowCount
     * @param searchPhrase
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public MyPage data(@RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount, @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        Page<VEqAddBill> page = eqAddBillService.findByEqNameContaining(searchPhrase, new PageRequest(current - 1, rowCount.intValue()));
        MyPage myPage = new MyPage();
        myPage.setRows(page.getContent());
        myPage.setRowCount(rowCount);
        myPage.setCurrent(current);
        myPage.setTotal(page.getTotalElements());
        return myPage;
    }


    /**
     * @param eqAddBill 保存或者更新设备更新申请单
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(EqAddBill eqAddBill) {
        EqAddBill budgetObj;
        String operation = "保存";
        if (eqAddBill.getId() != null) {
            operation = "更新";
        }
        budgetObj = eqAddBillService.save(eqAddBill);
        return commonDataService.getReturnType(budgetObj != null, "设备新置申请单" + operation + "成功!", "设备新置申请单" + operation + "失败!");

    }


    /**
     * @return 查询所有的id
     */
    @RequestMapping(value = "/findAllIds", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> findAllIds() {
        return eqAddBillService.findAllIds();
    }


    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EqAddBill findById(@PathVariable("id") Long id) {
        return eqAddBillService.findById(id);
    }


}

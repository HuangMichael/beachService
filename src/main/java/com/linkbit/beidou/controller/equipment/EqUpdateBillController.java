package com.linkbit.beidou.controller.equipment;


import com.linkbit.beidou.domain.app.MyPage;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.equipments.*;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.commonData.CommonDataService;
import com.linkbit.beidou.service.equipments.EqUpdateBillService;
import com.linkbit.beidou.service.equipments.EquipmentAccountService;
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
 * 设备更新
 * 2016年9月23日10:48:16
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/eqUpdateBill")
public class EqUpdateBillController {
    @Autowired
    EqUpdateBillService eqUpdateBillService;
    @Autowired
    ResourceService resourceService;

    @Autowired
    CommonDataService commonDataService;

    @Autowired
    EquipmentAccountService equipmentAccountService;

    /**
     * 初始化分页查询设备更新申请单信息
     *
     * @param current
     * @param rowCount
     * @param searchPhrase
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public MyPage data(@RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount, @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        Page<VEqUpdateBill> page = eqUpdateBillService.findByEqNameContaining(searchPhrase, new PageRequest(current - 1, rowCount.intValue()));
        MyPage myPage = new MyPage();
        myPage.setRows(page.getContent());
        myPage.setRowCount(rowCount);
        myPage.setCurrent(current);
        myPage.setTotal(page.getTotalElements());
        return myPage;
    }


    /**
     * @param eid
     * @return 返回新创建的对象
     */
    @RequestMapping(value = "/create/{eid}", method = RequestMethod.GET)
    @ResponseBody
    public EqUpdateBill create(@PathVariable("eid") Long eid) {
        Equipments equipments = equipmentAccountService.findById(eid);
        EqUpdateBill eqUpdateBill = new EqUpdateBill();
        eqUpdateBill.setEquipments(equipments);
        return eqUpdateBill;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpSession httpSession, ModelMap modelMap) {
        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);
        return "/eqUpdateBill/list";
    }

    /**
     * @param id 根据id查询
     * @return
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EqUpdateBill findById(@PathVariable("id") Long id) {
        return eqUpdateBillService.findById(id);
    }


    /**
     * @param budgetBill 保存或者更新设备更新申请单
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(EqUpdateBill budgetBill) {
        EqUpdateBill budgetObj;
        String operation = "保存";
        if (budgetBill.getId() != null) {
            operation = "更新";
        }
        budgetObj = eqUpdateBillService.save(budgetBill);
        return commonDataService.getReturnType(budgetObj != null, "设备更新申请单" + operation + "成功!", "设备更新申请单" + operation + "失败!");

    }


    /**
     * @param id 根据id删除采购单
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return eqUpdateBillService.delete(id);
    }


    /**
     * @return 查询所有的id
     */
    @RequestMapping(value = "/findAllIds", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> findAllIds() {
        return eqUpdateBillService.findAllIds();
    }


    /**
     * @param lid
     * @return 根据位置id过滤对应的设备分类
     */
    @RequestMapping(value = "/findCByLocId/{lid}", method = RequestMethod.GET)
    @ResponseBody
    public List<VeqClass> findEqClassesByLocationId(@PathVariable("lid") Long lid) {
        return commonDataService.findEqClassesByLocationId(lid);
    }


    /**
     * @param lid
     * @return 根据位置和设备分类过滤设备
     */
    @RequestMapping(value = "/findEqBy/{lid}/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public List<Vequipments> findEqClassesByLocationId(@PathVariable("lid") Long lid, @PathVariable("cid") Long cid) {
        return commonDataService.findEqByLocIdAndEqcId(lid, cid);
    }
}

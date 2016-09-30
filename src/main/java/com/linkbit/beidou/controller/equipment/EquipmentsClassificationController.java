package com.linkbit.beidou.controller.equipment;


import com.linkbit.beidou.dao.equipments.EquipmentsClassificationRepository;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.equipments.EquipmentsClassification;
import com.linkbit.beidou.domain.outsourcingUnit.OutsourcingUnit;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.equipmentsClassification.EquipmentsClassificationService;
import com.linkbit.beidou.service.unit.OutsoucingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 * 设备分类控制器类
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/equipmentsClassification")
public class EquipmentsClassificationController {

    @Autowired
    EquipmentsClassificationRepository equipmentsClassificationRepository;
    @Autowired
    EquipmentsClassificationService equipmentsClassificationService;
    @Autowired
    OutsoucingUnitService outsoucingUnitService;
    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap, HttpSession httpSession) {
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, "equipmentsClassification");
        modelMap.put("appMenus", appMenus);
        List<EquipmentsClassification> classificationList = equipmentsClassificationRepository.findAll();
        EquipmentsClassification object = null;
        if (!classificationList.isEmpty()) {
            object = equipmentsClassificationRepository.findById(classificationList.get(0).getId());
            modelMap.put("object", object);
        }
        modelMap.put("classificationList", classificationList);
        return "/equipmentsClassification/list";
    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/findNodeByParentId")
    @ResponseBody
    public List<EquipmentsClassification> findNodeByParentId() {
        List<EquipmentsClassification> equipmentsClassificationList = equipmentsClassificationRepository.findNodeByParentId();
        return equipmentsClassificationList;
    }

    /**
     * 查询根节点
     */
    @RequestMapping(value = "/findNodeByParentId/{id}")
    @ResponseBody
    public List<EquipmentsClassification> findNodeByParentId(@PathVariable("id") long id) {

        EquipmentsClassification equipmentClassification = equipmentsClassificationRepository.findById(id);
        List<EquipmentsClassification> equipmentsClassificationList = equipmentsClassificationRepository.findNodeByParent(equipmentClassification);
        return equipmentsClassificationList;
    }

    /**
     * 查询根节点
     */
    @RequestMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap modelMap) {
        String url = "/equipmentsClassification";
        EquipmentsClassification object = null;
        List<OutsourcingUnit> unitList = new ArrayList<OutsourcingUnit>();
        if (id != 0) {
            url += "/detail";
            object = equipmentsClassificationRepository.findById(id);
            unitList = object.getUnitSet();
        }
        modelMap.put("equipmentsClassification", object);
        modelMap.put("unitList", unitList);
        //查询出当前设备分类的所有维修单位
        return url;
    }

    /**
     * 查询根节点
     */
    @RequestMapping(value = "/create/{id}")
    public String create(@PathVariable("id") Long id, ModelMap modelMap) {
        EquipmentsClassification newObj = equipmentsClassificationService.create(id);
        modelMap.put("equipmentsClassification", newObj);
        List<EquipmentsClassification> equipmentsClassificationList = equipmentsClassificationService.findAll();
        modelMap.put("equipmentsClassificationList", equipmentsClassificationList);
        return "/equipmentsClassification/create";
    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public EquipmentsClassification save(@RequestParam("lid") Long lid,
                                         @RequestParam("description") String description,
                                         @RequestParam("parentId") Long parentId,
                                         @RequestParam("classId") String classId,
                                         @RequestParam(value = "limitHours", required = false) Long limitHours) {
        EquipmentsClassification newObj;
        EquipmentsClassification parent = equipmentsClassificationService.findById(parentId);
        if (lid == null || lid == 0) {
            newObj = new EquipmentsClassification();
            newObj.setClassId(classId);
            newObj.setDescription(description);
            newObj.setLimitHours(limitHours);
            newObj.setLevel(parent.getLevel() + 1);
            newObj.setStatus("1");
            newObj.setClassType(parent.getClassType());
            newObj.setParent(parent);
        } else {
            newObj = equipmentsClassificationService.findById(lid);
            newObj.setDescription(description);
            newObj.setParent(parent);
            newObj.setLimitHours(limitHours);
            newObj.setClassType(parent.getClassType());
            newObj.setLevel(parent.getLevel() + 1);
            newObj.setStatus("1");
        }
        newObj = equipmentsClassificationService.save(newObj);
        return newObj;
    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<EquipmentsClassification> findAll() {
        List<EquipmentsClassification> equipmentClassificationList = equipmentsClassificationRepository.findAll();
        return equipmentClassificationList;
    }


    /**
     * 保存部门信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        EquipmentsClassification equipmentsClassification = equipmentsClassificationService.findById(id);
        if (equipmentsClassification != null) {
            equipmentsClassificationService.delete(equipmentsClassificationService.findById(id));
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据设备分类查询不是该分类对应的外委单位信息
     *
     * @param cid 设备分类编号
     * @return 返回 id  外委单位名称
     */
    @RequestMapping(value = "/findUnitByClassIdNotEq/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> findUnitByClassIdNotEq(@PathVariable("cid") Long cid) {
        return outsoucingUnitService.findUnitListByEqClassIdNotEq(cid);
    }


    /**
     * 根据设备分类查询对应的外委单位信息
     *
     * @param cid 设备分类编号
     * @return 返回 id  外委单位名称
     */
    @RequestMapping(value = "/findUnitListByEqClassId/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> findUnitListByEqClassId(@PathVariable("cid") Long cid) {


        return outsoucingUnitService.findUnitListByEqClassIdEq(cid);
    }


    /**
     * 载入选择外委单位页面
     *
     * @param cid      设备分类编号
     * @param modelMap map对象
     * @return 返回 选择外委单位页面
     */
    @RequestMapping(value = "/loadSelectUnitPage/{cid}", method = RequestMethod.GET)
    public String loadSelectUnitPage(@PathVariable("cid") Long cid, ModelMap modelMap) {
        List<Object> unitList = outsoucingUnitService.findUnitListByEqClassIdNotEq(cid);
        modelMap.put("unitList", unitList);
        return "equipmentsClassification/unitList";
    }


    @RequestMapping(value = "/addUnits", method = RequestMethod.POST)
    @ResponseBody
    public List<OutsourcingUnit> addUnits(@RequestParam("cid") Long cid, @RequestParam("ids") String ids) {
        EquipmentsClassification equipmentsClassification = null;
        List<OutsourcingUnit> outsourcingUnitList = null;
        if (cid != null && ids != null) {
            outsourcingUnitList = outsoucingUnitService.addUnits(cid, ids);
        }
        return outsourcingUnitList;
    }


    @RequestMapping(value = "/addU2c", method = RequestMethod.POST)
    @ResponseBody
    public List<OutsourcingUnit> addU2c(@RequestParam("cid") Long cid, @RequestParam("ids") String ids, @RequestParam("workOrderId") Long workOrderId) {
        List<OutsourcingUnit> outsourcingUnitList = null;
        if (cid != null && ids != null) {
            outsourcingUnitList = outsoucingUnitService.addU2c(cid, ids, workOrderId);
        }
        return outsourcingUnitList;
    }

    @RequestMapping(value = "/removeUnits", method = RequestMethod.POST)
    @ResponseBody
    public EquipmentsClassification removeUnits(@RequestParam("cid") Long cid, @RequestParam("ids") String ids) {
        EquipmentsClassification equipmentsClassification = null;
        if (cid != null && ids != null) {
            equipmentsClassification = outsoucingUnitService.removeUnits(cid, ids);
        }
        return equipmentsClassification;
    }


    /**
     * @param cid 设备分类ID
     * @return 根据设备分类的ID查询对应的外委单位的ID集合
     */
    @RequestMapping(value = "/getUnitsByEqClassId/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> getUnitsByEqClassId(@PathVariable("cid") Long cid) {
        List<Long> longList = equipmentsClassificationService.getUnitsByEqClassId(cid);
        return longList;
    }
}

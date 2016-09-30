package com.linkbit.beidou.controller.equipment;


import com.linkbit.beidou.controller.common.BaseController;
import com.linkbit.beidou.dao.equipments.EquipmentsClassificationRepository;
import com.linkbit.beidou.dao.equipments.EquipmentsRepository;
import com.linkbit.beidou.dao.equipments.VequipmentsRepository;
import com.linkbit.beidou.dao.locations.VlocationsRepository;
import com.linkbit.beidou.domain.app.MyPage;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.equipments.EqUpdateBill;
import com.linkbit.beidou.domain.equipments.Equipments;
import com.linkbit.beidou.domain.equipments.Vequipments;
import com.linkbit.beidou.domain.outsourcingUnit.OutsourcingUnit;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.object.PageObject;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.equipments.EqUpdateBillService;
import com.linkbit.beidou.service.equipments.EquipmentAccountService;
import com.linkbit.beidou.service.locations.LocationsService;
import com.linkbit.beidou.utils.DateUtils;
import com.linkbit.beidou.utils.SessionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 * 设备台账控制器类
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/equipment")
public class EquipmentController extends BaseController {


    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    EquipmentsRepository equipmentsRepository;
    @Autowired
    EquipmentAccountService equipmentAccountService;

    @Autowired
    EquipmentsClassificationRepository equipmentsClassificationRepository;

    @Autowired
    LocationsService locationsService;

    @Autowired
    VlocationsRepository vlocationsRepository;
    @Autowired
    VequipmentsRepository vequipmentsRepository;

    @Autowired
    ResourceService resourceService;

    @Autowired
    EqUpdateBillService eqUpdateBillService;


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
    public MyPage data(HttpSession session, @RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount, @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        String location = SessionUtil.getCurrentUserLocationBySession(session);
        Page<Vequipments> page = equipmentAccountService.findByEqNameContains(searchPhrase, new PageRequest(current - 1, rowCount.intValue()));
        MyPage myPage = new MyPage();
        myPage.setRows(page.getContent());
        myPage.setRowCount(rowCount);
        myPage.setCurrent(current);
        myPage.setTotal(page.getTotalElements());
        return myPage;
    }


    @RequestMapping(value = "/list")
    public String list(HttpSession httpSession, ModelMap modelMap) {

        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        System.out.println("controllerName-----------------------" + controllerName);
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);


        return "/equipment/list";
    }

    /**
     * @param session 当前会话
     * @return 查询当前用户所在的位置下属的设备
     */
    @RequestMapping(value = "/findMyEqs")
    @ResponseBody
    public List<Vequipments> findMyEqs(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        String userLocation = user.getLocation();
        return vequipmentsRepository.findByLocationStartingWithOrderByIdDesc(userLocation);
    }

    @RequestMapping(value = "/reload/{objId}")
    public String reload(@PathVariable("objId") long objId, ModelMap modelMap) {
        Equipments equipments = equipmentsRepository.findById(objId);
        modelMap.put("object", equipments);
        return "/equipment/table_1_2";
    }

    /**
     * 查询根节点
     */
    @RequestMapping(value = "/create")
    public String create(ModelMap modelMap) {
        List<OutsourcingUnit> outsourcingUnitList = equipmentAccountService.findAllUnit();
        //  modelMap.put("outsourcingUnitList", outsourcingUnitList);
        //查询出所有的设备分类
        return "/equipment/create";
    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Equipments save(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam("eqCode") String eqCode,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "manager", required = false) String manager,
            @RequestParam(value = "maintainer", required = false) String maintainer,
            @RequestParam(value = "productFactory", required = false) String productFactory,
            @RequestParam(value = "imgUrl", required = false) String imgUrl,
            @RequestParam(value = "originalValue", required = false) Double originalValue,
            @RequestParam(value = "netValue", required = false) Double netValue,
            @RequestParam(value = "purchasePrice", required = false) Double purchasePrice,
            @RequestParam(value = "purchaseDate", required = false) String purchaseDate,
            @RequestParam(value = "locations_id", required = false) Long locations_id,
            @RequestParam(value = "equipmentsClassification_id", required = false) Long equipmentsClassification_id,
            @RequestParam(value = "status", defaultValue = "1") String status,
            @RequestParam(value = "eqModel", required = false) String eqModel,
            @RequestParam(value = "assetNo", required = false) String assetNo,
            @RequestParam(value = "manageLevel", required = false) Long manageLevel,
            @RequestParam(value = "running", required = false) String running,
            @RequestParam(value = "warrantyPeriod", required = false) String warrantyPeriod,
            @RequestParam(value = "setupDate", required = false) String setupDate,
            @RequestParam(value = "productDate", required = false) String productDate,
            @RequestParam(value = "runDate", required = false) String runDate,
            @RequestParam(value = "expectedYear", required = false) Long expectedYear
    ) {
        Equipments equipments = (id != null) ? equipmentAccountService.findById(id) : new Equipments();

        //首先判断是否存在
        try {
            equipments.setEqCode(eqCode);
            equipments.setDescription(description);
            equipments.setManager(manager);
            equipments.setMaintainer(maintainer);
            equipments.setProductFactory(productFactory);
            equipments.setImgUrl(imgUrl);
            equipments.setOriginalValue(originalValue);
            equipments.setPurchasePrice(purchasePrice);
            equipments.setNetValue(netValue);
            equipments.setLocations(locationsService.findById(locations_id));
            equipments.setEquipmentsClassification(equipmentsClassificationRepository.findById(equipmentsClassification_id));
            equipments.setStatus(status);
            equipments.setLocation(equipments.getLocations().getLocation());
            equipments.setEqModel(eqModel);
            equipments.setAssetNo(assetNo);
            equipments.setManageLevel(manageLevel);
            equipments.setRunning(running);
            equipments.setVlocations(vlocationsRepository.findById(locations_id));
            Date purchaseDated;
            Date warrantyPeriodDate;
            Date setupDateDate;
            Date productDateDate;
            Date runDateDate;
            if (purchaseDate != null) {
                purchaseDated = DateUtils.convertStr2Date(purchaseDate, "yyyy-MM-dd");
                equipments.setPurchaseDate(purchaseDated);
            }
            if (warrantyPeriod != null) {
                warrantyPeriodDate = DateUtils.convertStr2Date(warrantyPeriod, "yyyy-MM-dd");
                equipments.setWarrantyPeriod(warrantyPeriodDate);
            }
            if (setupDate != null) {
                setupDateDate = DateUtils.convertStr2Date(setupDate, "yyyy-MM-dd");
                equipments.setSetupDate(setupDateDate);
            }
            if (productDate != null) {
                productDateDate = DateUtils.convertStr2Date(productDate, "yyyy-MM-dd");
                equipments.setProductDate(productDateDate);
            }
            if (runDate != null) {
                runDateDate = DateUtils.convertStr2Date(runDate, "yyyy-MM-dd");
                equipments.setRunDate(runDateDate);
            }
            equipments.setExpectedYear(expectedYear);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipmentAccountService.save(equipments);

    }


    /**
     * 检验设备编号是否唯一
     */
    @RequestMapping(value = "/checkExist/{eqCode}")
    @ResponseBody
    public boolean checkExist(@PathVariable("eqCode") String eqCode) {
        boolean exists = true;
        if (eqCode != null && !eqCode.equals("")) {

            exists = equipmentAccountService.checkExist(eqCode);
        }
        return exists;

    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/findById/{id}")
    @ResponseBody
    public Equipments findById(@PathVariable("id") Long id) {
        return equipmentAccountService.findById(id);

    }


    /**
     * @param id 根据id删除设备信息
     */
    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public ReturnObject delete(@PathVariable("id") Long id) {
        Equipments equipments = null;
        if (id != null) {
            equipments = equipmentAccountService.findById(id);
        }

        ReturnObject returnObject = new ReturnObject();
        returnObject.setResult(equipmentAccountService.delete(equipments));
        returnObject.setResultDesc(equipments.getId() + "");

        return returnObject;

    }


    /**
     * @param id 根据id删除设备信息
     */
    @RequestMapping(value = "/abandon/{id}")
    @ResponseBody
    public String abandon(@PathVariable("id") Long id) {
        Equipments equipments = null;
        String status = "";
        if (id != null) {
            equipments = equipmentAccountService.findById(id);
            status = equipmentAccountService.abandon(equipments);
        }
        return status;

    }

    /**
     * @param location 位置编号
     * @returnzz
     */
    @RequestMapping(value = "/findEqByLocLike/{location}")
    @ResponseBody
    public PageObject findEquipmentsByLocationLike(@PathVariable("location") String location) {

        List<Equipments> equipmentsList = equipmentsRepository.findByLocationStartingWith(location);
        PageObject po = new PageObject();
        po.setCurrent(1l);
        po.setRowCount(10l);
        po.setRows(equipmentsList);
        po.setTotal(equipmentsList.size() + 0l);
        return po;

    }


    /**
     * @param eid
     */
    @RequestMapping(value = "/findFixingStep/{eid}")
    @ResponseBody
    public List<Object> findFixingStep(@PathVariable("eid") Long eid) {

        return equipmentAccountService.findFixingStepByEid(eid);

    }

    /**
     * @param eid 设备编号
     * @return 根据设备id获取设备信息
     */
    @RequestMapping(value = "/findEquipment/{eid}")
    @ResponseBody
    public Equipments findEquipment(@PathVariable("eid") Long eid) {
        return equipmentAccountService.findById(eid);
    }

   /* *//**
     * @param eid 设备编号
     * @return 根据设备id获取设备维修节点信息信息
     *//*
    @RequestMapping(value = "/getFixSteps/{eid}")
    @ResponseBody
    public List<Object> getFixSteps(@PathVariable("eid") Long eid) {
        String orderLineNo = workOrderReportService.getLastOrderLineNoByEquipmentId(eid);
        List<Object> fixSteps = null;
        if (orderLineNo != null && !orderLineNo.equals("")) {
            fixSteps = equipmentAccountService.findFixStepsByOrderLineNo(orderLineNo);
        }
        return fixSteps;
    }*/

    /**
     * @param eid 设备编号
     * @return 根据设备id获取设备维修节点信息信息
     */
    @RequestMapping(value = "/getFixStepsByEid/{eid}")
    @ResponseBody
    public List<Object> getFixStepsByEid(@PathVariable("eid") Long eid) {
        return equipmentAccountService.findAllFixStepsByEid(eid);
    }


    /**
     * 查询设备对应的维修历史
     */
    @RequestMapping(value = "/loadHistory/{eid}")
    public String loadHistoryByEid(@PathVariable("eid") Long eid, ModelMap modelMap) {
        List<Object> historyList = equipmentAccountService.findFixHistoryByEid(eid);
        modelMap.put("historyList", historyList);
        return "/equipment/table_1_3";
    }


    /**
     * 查询设备对应的维修历史
     */
    @RequestMapping(value = "/loadLastHistory/{eid}")
    public String loadLastHistory(@PathVariable("eid") Long eid, ModelMap modelMap) {
        List<Object> historyList = equipmentAccountService.findLastFixHistoryByEid(eid);
        modelMap.put("historyList", historyList);
        return "/equipment/table_1_3";
    }

    /**
     * 查询设备对应的维修历史
     */
    @RequestMapping(value = "/loadFixHistory/{orderLineNo}")
    public String loadFixHistory(@PathVariable("orderLineNo") String orderLineNo, ModelMap modelMap) {
        List<Object> fixHistoryList = equipmentAccountService.findAllFixStepsByOrderLineNo(orderLineNo);
        modelMap.put("fixHistoryList", fixHistoryList);
        return "/equipment/table_1_4";
    }


    /**
     * 查询设备对应的维修历史
     *
     * @param eid
     * @return 根据设备id查询维修历史信息
     *//*
    @RequestMapping(value = "/findFixHisory/{eid}")
    @ResponseBody
    public List<VworkOrderStep> findFixHisory(@PathVariable("eid") Long eid) {
        return equipmentAccountService.findFixHistory(eid);
    }*/


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Equipments add(

            @RequestParam("eqCode") String eqCode,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "manager", required = false) String manager,
            @RequestParam(value = "maintainer", required = false) String maintainer,
            @RequestParam(value = "productFactory", required = false) String productFactory,
            @RequestParam(value = "locations_id", required = false) Long locations_id,
            @RequestParam(value = "equipmentsClassification_id", required = false) Long equipmentsClassification_id,
            @RequestParam(value = "status", defaultValue = "1") String status
    ) {
        Equipments equipments = new Equipments();
        try {
            equipments.setEqCode(eqCode);
            equipments.setDescription(description);
            equipments.setManager(manager);
            equipments.setMaintainer(maintainer);
            equipments.setProductFactory(productFactory);
            equipments.setLocations(locationsService.findById(locations_id));
            equipments.setEquipmentsClassification(equipmentsClassificationRepository.findById(equipmentsClassification_id));
            equipments.setStatus(status);
            equipments.setLocation(equipments.getLocations().getLocation());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipmentAccountService.save(equipments);

    }

    /**
     * @param eqCode 设备编号
     * @return 查询设备编号是否存在
     */
    @RequestMapping(value = "/checkEqCodeExists/{eqCode}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkEqCodeExists(@PathVariable("eqCode") String eqCode) {
        return equipmentAccountService.eqCodeExists(eqCode);
    }


    /**
     * @param id 设备id
     * @return 根据设备id查询设备更新历史
     */
    @RequestMapping(value = "/getUpdateHistoryById/{id}", method = RequestMethod.GET)
    @ResponseBody
    List<EqUpdateBill> getUpdateHistoryById(@PathVariable("id") Long id) {
        return eqUpdateBillService.getUpdateHistoryById(id);
    }
}

package com.linkbit.beidou.controller.outsourcingUnit;


import com.linkbit.beidou.dao.outsourcingUnit.OutsourcingUnitRepository;
import com.linkbit.beidou.domain.equipments.EquipmentsClassification;
import com.linkbit.beidou.domain.outsourcingUnit.OutsourcingUnit;
import com.linkbit.beidou.domain.outsourcingUnit.OutsourcingUnitContract;
import com.linkbit.beidou.domain.outsourcingUnit.OutsourcingUnitEvaluation;
import com.linkbit.beidou.domain.outsourcingUnit.OutsourcingUnitSafeDocs;
import com.linkbit.beidou.service.unit.OutsoucingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by huangbin on 2015/12/23 0023.
 * 外委单位控制器类
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/outsourcingUnit")
public class OutsourcingUnitController {

    @Autowired
    OutsourcingUnitRepository outsourcingUnitRepository;


    @Autowired
    OutsoucingUnitService outsourcingUnitService;

    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap) {

        return "/units/list";
    }

    /**
     * @return 查询 所有的外委单位信息
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<OutsourcingUnit> findAll() {
        List<OutsourcingUnit> outsourcingUnitList = outsourcingUnitService.findAll();
        return outsourcingUnitList;
    }


    /**
     * @param unitId   外委单位信息
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/detail/{unitId}", method = RequestMethod.GET)
    public String detail(@PathVariable("unitId") Long unitId, ModelMap modelMap) {
        OutsourcingUnit unit = outsourcingUnitRepository.findById(unitId);

        if (unit != null) {
            List<OutsourcingUnitContract> contractList = unit.getContractList();
            List<OutsourcingUnitSafeDocs> docsList = unit.getSafeDocsList();
            List<OutsourcingUnitEvaluation> evaluationList = unit.getEvaluationList();
            Set<EquipmentsClassification> equipmentsClassificationList = unit.getEqClassList();
            modelMap.put("unit", unit);
            modelMap.put("contractList", contractList);
            modelMap.put("docsList", docsList);
            modelMap.put("evaluationList", evaluationList);
            modelMap.put("equipmentsClassificationList", equipmentsClassificationList);
        }
        return "/units/detail";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public OutsourcingUnit save(OutsourcingUnit outsourcingUnit) {
        outsourcingUnit.setStatus("1");
        outsourcingUnit = outsourcingUnitRepository.save(outsourcingUnit);
        return outsourcingUnit;
    }


    @RequestMapping(value = "/saveLink", method = RequestMethod.POST)
    @ResponseBody
    public List<OutsourcingUnit> saveLink(@RequestParam("unitNo") String unitNo,
                                          @RequestParam("description") String description,
                                          @RequestParam("linkman") String linkman,
                                          @RequestParam("telephone") String telephone,
                                          @RequestParam("eqClassId") Long eqClassId,
                                          @RequestParam("workDays") String workDays
    ) {
        OutsourcingUnit outsourcingUnit = new OutsourcingUnit();
        outsourcingUnit.setUnitNo(unitNo);
        outsourcingUnit.setDescription(description);
        outsourcingUnit.setLinkman(linkman);
        outsourcingUnit.setTelephone(telephone);
        outsourcingUnit.setWorkDays(workDays);
        outsourcingUnit.setStatus("1");
        outsourcingUnit = outsourcingUnitRepository.save(outsourcingUnit);
        return outsourcingUnitService.saveLink(outsourcingUnit, eqClassId);
    }


    /**
     * @param id 根据id删除设备信息
     */
    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        OutsourcingUnit unit = null;
        if (id != null) {
            unit = outsourcingUnitService.findById(id);
        }
        return outsourcingUnitService.delete(unit);

    }


    @RequestMapping(value = "/downloadContract/{contractId}", method = RequestMethod.GET)
    public String downloadContract(@PathVariable("contractId") Long contractId, ModelMap modelMap) {
        return "/docs/contracts/abc.doc";
    }


    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OutsourcingUnit findById(@PathVariable("id") Long id) {
        return outsourcingUnitRepository.findById(id);
    }

    /**
     * @param status 状态
     * @return 根据状态查询外委单位信息
     */
    @RequestMapping(value = "/findByStatus/{status}", method = RequestMethod.GET)
    @ResponseBody
    public List<OutsourcingUnit> findByStatus(@PathVariable("status") String status) {
        return outsourcingUnitRepository.findByStatus(status);
    }


    @RequestMapping(value = "/loadPageByUrl/{pageUrl}/{uid}", method = RequestMethod.GET)
    public String loadPageByUrl(@PathVariable("pageUrl") String pageUrl, @PathVariable("uid") Long uid, ModelMap modelMap) {
        OutsourcingUnit unit = outsourcingUnitRepository.findById(uid);
        modelMap.put("unit", unit);
        return "/units/" + pageUrl;
    }


    /**
     * @param unitNo 单位编号
     * @return 查询单位编号是否存在
     */
    @RequestMapping(value = "/checkUnitCodeExists/{unitNo}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkUnitCodeExists(@PathVariable("unitNo") String unitNo) {
        return outsourcingUnitService.unitNoExists(unitNo);
    }
}

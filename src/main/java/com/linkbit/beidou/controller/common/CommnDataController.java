package com.linkbit.beidou.controller.common;


import com.linkbit.beidou.domain.equipments.EquipmentsClassification;
import com.linkbit.beidou.domain.equipments.VeqClass;
import com.linkbit.beidou.domain.equipments.Vequipments;
import com.linkbit.beidou.domain.locations.Locations;
import com.linkbit.beidou.domain.locations.Vlocations;
import com.linkbit.beidou.domain.person.Person;
import com.linkbit.beidou.object.ListObject;
import com.linkbit.beidou.service.commonData.CommonDataService;
import com.linkbit.beidou.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 * 创建通用数据的控制器 自动选择数据加载方式
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/commonData")
public class CommnDataController extends BaseController {
    @Autowired
    CommonDataService commonDataService;

    /**
     * @param httpSession 当前会话
     * @return
     */
    @RequestMapping(value = "/findMyLocation", method = RequestMethod.GET)
    @ResponseBody
    public List<Locations> findMyLocation(HttpSession httpSession) {
        String location = SessionUtil.getCurrentUserLocationBySession(httpSession);
        List<Locations> locationList = null;
        if (location != null && !location.equals("")) {
            locationList = commonDataService.findMyLocation(location, httpSession);
        }
        return locationList;
    }


    /**
     * @param httpSession 当前会话
     * @return
     */
    @RequestMapping(value = "/findMyLoc", method = RequestMethod.GET)
    @ResponseBody
    public List<Vlocations> findMyLoc(HttpSession httpSession) {
        String location = SessionUtil.getCurrentUserLocationBySession(httpSession);
        List<Vlocations> locationList = null;
        if (location != null && !location.equals("")) {
            locationList = commonDataService.findMyVLocation(location, httpSession);
        }
        return locationList;
    }


    /**
     * @param httpSession 当前会话
     * @return
     */
    @RequestMapping(value = "/findMyEqs", method = RequestMethod.GET)
    @ResponseBody
    public List<Vequipments> findMyEqs(HttpSession httpSession) {
        String location = SessionUtil.getCurrentUserLocationBySession(httpSession);
        List<Vequipments> vequipmentsList = null;
        if (location != null && !location.equals("")) {
            vequipmentsList = commonDataService.findMyVeqs(location, httpSession);
        }
        return vequipmentsList;
    }

    /**
     * @param httpSession 当前会话
     * @return 查询分类
     */
    @RequestMapping(value = "/findEqClass", method = RequestMethod.GET)
    @ResponseBody
    public List<EquipmentsClassification> findEquipmentsClassifications(HttpSession httpSession) {
        List<EquipmentsClassification> equipmentsClassificationList = null;
        if (httpSession != null) {
            equipmentsClassificationList = commonDataService.findEquipmentsClassification(httpSession);
        }
        return equipmentsClassificationList;
    }


    /**
     * @param httpSession 当前会话
     * @return 查询分类视图信息
     */
    @RequestMapping(value = "/findVEqClass", method = RequestMethod.GET)
    @ResponseBody
    public List<VeqClass> findVeqClass(HttpSession httpSession) {
        List<VeqClass> veqClassList = null;
        if (httpSession != null) {
            veqClassList = commonDataService.findVeqClass(httpSession);
        }
        return veqClassList;
    }


    /**
     * @param httpSession 当前会话
     * @return 获得设备状态
     */
    @RequestMapping(value = "/getEqStatus", method = RequestMethod.GET)
    @ResponseBody
    public List<ListObject> getEqStatus(HttpSession httpSession) {
        return commonDataService.getEqStatus(httpSession);
    }

    /**
     * @param httpSession 当前会话
     * @return 获得设备状态
     */
    @RequestMapping(value = "/getEqRunStatus", method = RequestMethod.GET)
    @ResponseBody
    public List<ListObject> getEqRunStatus(HttpSession httpSession) {

        return commonDataService.getRunningStatus(httpSession);
    }


    /**
     * @param httpSession 当前会话
     * @return 获得设备状态
     */
    @RequestMapping(value = "/findActivePerson", method = RequestMethod.GET)
    @ResponseBody
    public List<Person> findActivePerson(HttpSession httpSession) {
        return commonDataService.findActivePerson(httpSession);
    }

    /**
     * @return 获得服务器时间
     */
    @RequestMapping(value = "/getServerDate", method = RequestMethod.GET)
    @ResponseBody
    public String getServerDate() {
        return  commonDataService.getServerDate();
    }
}


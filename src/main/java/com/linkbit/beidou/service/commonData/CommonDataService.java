package com.linkbit.beidou.service.commonData;

import com.linkbit.beidou.dao.app.resource.ResourceRepository;
import com.linkbit.beidou.dao.equipments.EquipmentsClassificationRepository;
import com.linkbit.beidou.dao.equipments.EquipmentsRepository;
import com.linkbit.beidou.dao.equipments.VeqClassRepository;
import com.linkbit.beidou.dao.equipments.VequipmentsRepository;
import com.linkbit.beidou.dao.line.LineRepository;
import com.linkbit.beidou.dao.line.StationRepository;
import com.linkbit.beidou.dao.locations.LocationsRepository;
import com.linkbit.beidou.dao.locations.VlocationsRepository;
import com.linkbit.beidou.dao.person.PersonRepository;
import com.linkbit.beidou.domain.app.resoure.Resource;
import com.linkbit.beidou.domain.equipments.Equipments;
import com.linkbit.beidou.domain.equipments.EquipmentsClassification;
import com.linkbit.beidou.domain.equipments.VeqClass;
import com.linkbit.beidou.domain.equipments.Vequipments;
import com.linkbit.beidou.domain.line.Line;
import com.linkbit.beidou.domain.line.Station;
import com.linkbit.beidou.domain.locations.Locations;
import com.linkbit.beidou.domain.locations.Vlocations;
import com.linkbit.beidou.domain.person.Person;
import com.linkbit.beidou.object.ListObject;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.utils.CommonStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 */
@Service
public class CommonDataService extends BaseService {

    @Autowired
    LocationsRepository locationsRepository;

    @Autowired
    VlocationsRepository vlocationsRepository;

    @Autowired
    VeqClassRepository veqClassRepository;

    @Autowired
    EquipmentsClassificationRepository equipmentsClassificationRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    LineRepository lineRepository;

    @Autowired
    StationRepository stationRepository;


    @Autowired
    VequipmentsRepository vequipmentsRepository;

    @Autowired
    EquipmentsRepository equipmentsRepository;


    /**
     * @param location 位置编号
     * @return 查询我的下属位置信息
     * 先从session中找  如果失败再做查询
     */
    public List<Locations> findMyLocation(String location, HttpSession httpSession) {
        List<Locations> locationsList = null;
        Object object = httpSession.getAttribute("locationsList");
        if (object != null) {
            locationsList = (ArrayList<Locations>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询位置信息");
        } else {
            if (location != null && !location.equals("")) {
                locationsList = locationsRepository.findByLocationStartingWith(location);
                log.info(this.getClass().getCanonicalName() + "------------从缓存中查询位置信息");
            }
        }
        return locationsList;
    }


    /**
     * @param location    位置编号
     * @param httpSession 查询位置我的视图信息
     * @return 查询我的下属位置信息
     * 先从session中找  如果失败再做查询
     */
    public List<Vlocations> findMyVLocation(String location, HttpSession httpSession) {
        List<Vlocations> locationsList = null;
        Object object = httpSession.getAttribute("locationsList");
        if (object != null) {
            locationsList = (ArrayList<Vlocations>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询位置信息");
        } else {
            if (location != null && !location.equals("")) {
                locationsList = vlocationsRepository.findByLocationStartingWith(location);
                log.info(this.getClass().getCanonicalName() + "------------从缓存中查询位置信息");
            }
        }
        return locationsList;
    }


    /**
     * @param location    位置编号
     * @param httpSession 查询位置我的视图信息
     * @return 查询我的下属位置设备信息
     * 先从session中找  如果失败再做查询
     */
    public List<Vequipments> findMyVeqs(String location, HttpSession httpSession) {
        List<Vequipments> vequipmentsList = null;
        Object object = httpSession.getAttribute("vequipmentsList");
        if (object != null) {
            vequipmentsList = (ArrayList<Vequipments>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询位置信息");
        } else {
            if (location != null && !location.equals("")) {
                vequipmentsList = vequipmentsRepository.findByLocationStartingWith(location);
                log.info(this.getClass().getCanonicalName() + "------------从缓存中查询位置信息");
            }
        }
        return vequipmentsList;
    }


    /**
     * @param httpSession
     * @return 查询设备种类信息
     */
    public List<EquipmentsClassification> findEquipmentsClassification(HttpSession httpSession) {
        List<EquipmentsClassification> equipmentsClassificationList = null;
        Object object = httpSession.getAttribute("equipmentsClassificationList");
        if (object != null) {
            equipmentsClassificationList = (ArrayList<EquipmentsClassification>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询设备种类");

        } else {
            equipmentsClassificationList = equipmentsClassificationRepository.findAll();
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询设备种类");
            httpSession.setAttribute("equipmentsClassificationList", equipmentsClassificationList);
            log.info(this.getClass().getCanonicalName() + "------------设备种类放入缓存");
        }
        return equipmentsClassificationList;


    }


    /**
     * @param httpSession
     * @return 查询设备种类信息
     */
    public List<VeqClass> findVeqClass(HttpSession httpSession) {
        List<VeqClass> eqClassList = null;
        Object object = httpSession.getAttribute("eqClassList");
        if (object != null) {
            eqClassList = (ArrayList<VeqClass>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询设备种类视图");
        } else {
            eqClassList = veqClassRepository.findAll();
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询设备种类视图");
            httpSession.setAttribute("eqClassList", eqClassList);
            log.info(this.getClass().getCanonicalName() + "------------设备种类视图放入缓存");
        }
        return eqClassList;


    }


    /**
     * @param httpSession
     * @return 查询设备种类信息
     */
    public List<Resource> findMenus(HttpSession httpSession) {
        List<Resource> menusList = null;
        Object object = httpSession.getAttribute("menusList");
        if (object != null) {
            menusList = (ArrayList<Resource>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询菜单");
        } else {
            menusList = resourceRepository.findByResourceLevel(1L);
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询菜单");
            httpSession.setAttribute("menusList", menusList);
            log.info(this.getClass().getCanonicalName() + "------------将菜单放入缓存");
        }
        return menusList;


    }


    /**
     * @param httpSession
     * @return (0:停用 1:投用 2报废)
     */
    public List<ListObject> getEqStatus(HttpSession httpSession) {
        List<ListObject> eqStatusList = new ArrayList<ListObject>();
        Object object = httpSession.getAttribute("eqStatusList");
        if (object != null) {
            eqStatusList = (ArrayList<ListObject>) httpSession.getAttribute("eqStatusList");
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询设备状态");
        } else {
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询设备状态");
            eqStatusList.add(new ListObject("0", "维修"));
            eqStatusList.add(new ListObject("1", "投用"));
            eqStatusList.add(new ListObject("2", "报废"));
            log.info(this.getClass().getCanonicalName() + "------------设备状态放入缓存");
            httpSession.setAttribute("eqStatusList", eqStatusList);
        }
        return eqStatusList;

    }


    /**
     * @param httpSession
     * @return 获取运行状态 (0:停止 1:运行)
     */
    public List<ListObject> getRunningStatus(HttpSession httpSession) {
        List<ListObject> eqRunStatusList = new ArrayList<ListObject>();
        Object object = httpSession.getAttribute("eqRunStatusList");
        if (object != null) {
            eqRunStatusList = (ArrayList<ListObject>) httpSession.getAttribute("eqRunStatusList");
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询设备运行状态");
        } else {
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询设备运行状态");
            eqRunStatusList.add(new ListObject("0", "停止"));
            eqRunStatusList.add(new ListObject("1", "运行"));
            log.info(this.getClass().getCanonicalName() + "------------设备运行状态放入缓存");
            httpSession.setAttribute("eqRunStatusList", eqRunStatusList);
        }
        return eqRunStatusList;

    }


    /**
     * @param httpSession
     * @return 查询设备种类信息
     */
    public List<Person> findActivePerson(HttpSession httpSession) {
        List<Person> activePerson = null;
        Object object = httpSession.getAttribute("activePerson");
        if (object != null) {
            activePerson = (ArrayList<Person>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询人员");
        } else {
            activePerson = personRepository.findByStatus(CommonStatusType.STATUS_YES);
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询人员");
            httpSession.setAttribute("activePerson", activePerson);
            log.info(this.getClass().getCanonicalName() + "------------将人员放入缓存");
        }
        return activePerson;
    }


    /**
     * @param httpSession
     * @return 查询设备种类信息
     */
    public List<Station> findStations(HttpSession httpSession) {
        List<Station> stationList = null;
        Object object = httpSession.getAttribute("stationList");
        if (object != null) {
            stationList = (ArrayList<Station>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询车站");
        } else {
            stationList = stationRepository.findByStatus(CommonStatusType.STATUS_YES);
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询车站");
            httpSession.setAttribute("stationList", stationList);
            log.info(this.getClass().getCanonicalName() + "------------将车站放入缓存");
        }
        return stationList;
    }


    /**
     * @param httpSession
     * @return 查询设备种类信息
     */
    public List<Line> findLines(HttpSession httpSession) {
        List<Line> lineList = null;
        Object object = httpSession.getAttribute("lineList");
        if (object != null) {
            lineList = (ArrayList<Line>) object;
            log.info(this.getClass().getCanonicalName() + "------------从缓存中查询线路");
        } else {
            lineList = lineRepository.findByStatus(CommonStatusType.STATUS_YES);
            log.info(this.getClass().getCanonicalName() + "------------从数据库中查询线路");
            httpSession.setAttribute("lineList", lineList);
            log.info(this.getClass().getCanonicalName() + "------------将线路放入缓存");
        }
        return lineList;
    }

    /**
     * @return 获取服务器当前时间
     */
    public String getServerDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
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


    /**
     * @param id 位置编号
     * @return 查询位置下对应的设备分类
     */
    public List<VeqClass> findEqClassesByLocationId(Long id) {
        return equipmentsRepository.findEqClassesByLocationId(id);
    }


    /**
     * @param lid
     * @param cid
     * @return 查询位置下对应的设备分类
     */
    public List<Vequipments> findEqByLocIdAndEqcId(Long lid,Long cid) {
        return equipmentsRepository.findEqByLocIdAndEqcId(lid,cid);
    }

}

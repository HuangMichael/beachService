package com.linkbit.beidou.controller.line;


import com.linkbit.beidou.domain.EcBudget.VEcBudgetBill;
import com.linkbit.beidou.domain.app.MyPage;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.line.Line;
import com.linkbit.beidou.domain.line.Station;
import com.linkbit.beidou.domain.person.Person;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.line.LineService;
import com.linkbit.beidou.service.line.StationService;
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
 * Created by huangbin on 2015/12/23 0023.
 * 设备台账控制器类
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/station")
public class StationController {


    @Autowired
    StationService stationService;
    @Autowired
    LineService lineService;
    @Autowired
    ResourceService resourceService;


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
        Page<Station> page = stationService.findByStationNameContains(searchPhrase, new PageRequest(current - 1, rowCount.intValue()));
        MyPage myPage = new MyPage();
        myPage.setRows(page.getContent());
        myPage.setRowCount(rowCount);
        myPage.setCurrent(current);
        myPage.setTotal(page.getTotalElements());
        return myPage;
    }



    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap, HttpSession httpSession) {

        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);

        List<Station> stationList = stationService.findByStatus("1");
        modelMap.put("stationList", stationList);
        return "/station/list";
    }

    /**
     * 根据线路查询站点
     */
    @RequestMapping(value = "/findStationByLine/{lineId}")
    @ResponseBody
    public List<Station> findStationByLine(@PathVariable("lineId") Long lineId) {
        Line line = lineService.findById(lineId);
        if (null != line) {
            return stationService.findStationsByLine(line);
        } else {
            return null;
        }
    }


    /**
     * 新建记录
     */
    @RequestMapping(value = "/create")
    public String create() {
        return "/station/create";
    }

    /**
     * 根据所有车站信息
     */
    @RequestMapping(value = "/findByStatus")
    @ResponseBody
    public List<Station> findByStatus() {
        List<Station> stationList = stationService.findByStatus("1");
        return stationList;
    }

    /**
     * @param id 根据id查询车站信息
     * @return
     */
    @RequestMapping(value = "/findById/{id}")
    @ResponseBody
    public Station findById(@PathVariable("id") long id) {
        Station station = stationService.findById(id);
        return station;
    }


    /**
     * @param stationNo   车站编号
     * @param description 车站名称
     * @param status      车站状态
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Station save(@RequestParam("stationNo") String stationNo,
                        @RequestParam("description") String description,
                        @RequestParam("lineId") Long lineId,
                        @RequestParam("type") String type,
                        @RequestParam("status") String status) {
        Station station = new Station();
        station.setStationNo(stationNo);
        Line line = lineService.findById(lineId);
        station.setDescription(description);
        station.setLine(line);
        station.setStatus(status);
        station.setType(type);
        station = stationService.save(station);
        return station;
    }


    /**
     * @param stationNo   车站编号
     * @param description 车站名称
     * @param status      车站状态
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Station update(@RequestParam("stationId") Long stationId,
                          @RequestParam("stationNo") String stationNo,
                          @RequestParam("description") String description,
                          @RequestParam("lineId") Long lineId,
                          @RequestParam("type") String type,
                          @RequestParam("status") String status) {
        Station station = stationService.findById(stationId);
        station.setStationNo(stationNo);
        Line line = lineService.findById(lineId);
        station.setDescription(description);
        station.setLine(line);
        station.setStatus(status);
        station.setType(type);
        station = stationService.save(station);
        return station;
    }


    /**
     * 查询激活状态的人员信息
     *
     * @return
     */
    @RequestMapping(value = "/findActiveStation")
    @ResponseBody
    public List<Station> findActivePerson() {
        List<Station> stationList = stationService.findActiveStation();
        return stationList;
    }


    /**
     * @param id id
     * @return 根据删除车站信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public boolean delete(@RequestParam("id") Long id) {
        return stationService.delete(id);
    }
}
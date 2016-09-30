package com.linkbit.beidou.controller.line;


import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.line.Line;
import com.linkbit.beidou.domain.role.Role;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.commonData.CommonDataService;
import com.linkbit.beidou.service.line.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 * 线路控制器类
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/line")
public class LineController {

    @Autowired
    LineService lineService;
    @Autowired
    CommonDataService commonDataService;

    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap, HttpSession httpSession) {

        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);
        List<Line> lineList = lineService.findByStatus("1");
        modelMap.put("lineList", lineList);
        return "/line/list";
    }

    @RequestMapping(value = "/lines", method = {RequestMethod.GET})
    @ResponseBody
    public List<Line> findAllUseFulLines() {
        return lineService.findByStatus("1");
    }


    /**
     * 根据所有线路信息
     */
    @RequestMapping(value = "/findByStatus")
    @ResponseBody
    public List<Line> findByStatus() {
        List<Line> lineList = lineService.findByStatus("1");
        return lineList;
    }

    /**
     * @param id 根据id查询车站信息
     * @return
     */
    @RequestMapping(value = "/findById/{id}")
    @ResponseBody
    public Line findById(@PathVariable("id") long id) {
        Line line = lineService.findById(id);
        return line;
    }


    @RequestMapping(value = "/create")
    public String create() {
        return "/line/create";
    }


    /**
     * 保存线路信息
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public Line save(Line line) {
        return lineService.save(line);
    }


    /**
     * 保存角色信息
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public Line update(@RequestParam("lineId") Long lineId,
                       @RequestParam("lineNo") String lineNo,
                       @RequestParam("sortNo") Long sortNo,
                       @RequestParam("description") String description) {

        System.out.println("lineId--" + lineId);
        System.out.println("lineNo--" + lineNo);
        System.out.println("description--" + description);
        Line line = lineService.findById(lineId);
        line.setDescription(description);
        line.setSortNo(sortNo);
        line.setLineNo(lineNo);
        return lineService.save(line);
    }


    /**
     * @return 查询所有的线路
     */
    @RequestMapping(value = "/findAllLines")
    @ResponseBody
    public List<Line> findAllLines() {
        List<Line> lineList = lineService.findAll();
        return lineList;
    }


    /**
     * @return 查询所有的线路
     */
    @RequestMapping(value = "/findLines")
    @ResponseBody
    public List<Line> findLines() {
        List<Line> lineList = lineService.findLines();
        return lineList;
    }


    /**
     * @return 查询所有的线路
     */
    @RequestMapping(value = "/findSegs")
    @ResponseBody
    public List<Line> findSegs() {
        List<Line> lineList = lineService.findSegs();
        return lineList;
    }
}

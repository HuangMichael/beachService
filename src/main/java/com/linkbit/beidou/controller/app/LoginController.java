package com.linkbit.beidou.controller.app;

import com.linkbit.beidou.dao.app.org.OrgRepository;
import com.linkbit.beidou.dao.equipments.VeqClassRepository;
import com.linkbit.beidou.domain.app.org.Org;
import com.linkbit.beidou.domain.app.resoure.Resource;
import com.linkbit.beidou.domain.equipments.VeqClass;
import com.linkbit.beidou.domain.line.Line;
import com.linkbit.beidou.domain.line.Station;
import com.linkbit.beidou.domain.locations.Locations;
import com.linkbit.beidou.domain.locations.Vlocations;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.commonData.CommonDataService;
import com.linkbit.beidou.service.equipmentsClassification.EquipmentsClassificationService;
import com.linkbit.beidou.service.line.LineService;
import com.linkbit.beidou.service.line.StationService;
import com.linkbit.beidou.service.locations.LocationsService;
import com.linkbit.beidou.service.user.UserService;
import com.linkbit.beidou.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016/4/22.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    OrgRepository orgRepository;
    @Autowired
    CommonDataService commonDataService;
    @RequestMapping("/")
    public String logout(HttpServletRequest request, ModelMap modelMap) {
        HttpSession session = request.getSession();
        Org org = orgRepository.findByStatus("1").get(0);
        if (session.getId() != null) {
            request.removeAttribute("currentUser");
            request.removeAttribute("locationsList");
            request.removeAttribute("equipmentsClassificationList");
            request.getSession().invalidate();
        }
        modelMap.put("sysName", org.getSysName());
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject authenticate(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        String encryptPassword = MD5Util.md5(password);
        List<User> userList = userService.findByUserNameAndPasswordAndStatus(userName, encryptPassword, "1");
        return commonDataService.getReturnType(!userList.isEmpty(), "用户登录成功", "用户登录失败!");
    }
}

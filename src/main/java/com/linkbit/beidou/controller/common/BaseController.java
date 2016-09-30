package com.linkbit.beidou.controller.common;


import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.service.app.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
public class BaseController {
    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = "/list")
    public String list(HttpSession httpSession, ModelMap modelMap) {
        //加载查询菜单
        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        System.out.println("controllerName-----------------------"+controllerName);
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);
        return "/" + controllerName.toLowerCase() + "/list";
    }


}


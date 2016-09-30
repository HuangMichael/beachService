package com.linkbit.beidou.controller.app;


import com.linkbit.beidou.dao.app.resource.VRoleAuthViewRepository;
import com.linkbit.beidou.domain.app.resoure.Resource;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.menu.Menu;
import com.linkbit.beidou.domain.role.Role;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.role.RoleService;
import com.linkbit.beidou.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    ResourceService resourceService;

    @Autowired
    RoleService roleService;
    @Autowired
    VRoleAuthViewRepository vRoleAuthViewRepository;

    @Autowired
    UserService userService;


    /**
     * 初始化展示授权列表
     */
    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap) {
        //加载角色列表 显示所有的角色
        List<Role> roleList = roleService.findByStatus("1");
        modelMap.put("roleList", roleList);

        Role role = roleService.findById(1l);
        List<VRoleAuthView> vRoleAuthViews = vRoleAuthViewRepository.findByRole(role);
        modelMap.put("vRoleAuthViews", vRoleAuthViews);
        return "/authority/list";
    }


    /**
     * 初始化展示授权列表
     *
     * @param roleId      角色ID
     * @param resourceIds 资源的ID字符串
     * @return
     */
    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject grant(@RequestParam("roleId") Long roleId, @RequestParam("resourceIds") String resourceIds) {
        ReturnObject returnObject = new ReturnObject();
        if (roleId == null) {
            returnObject.setResult(false);
            returnObject.setResultDesc("应用授权失败,请选择要授权的角色!");
        } else if (resourceIds == null || resourceIds.equals("")) {
            returnObject.setResult(false);
            returnObject.setResultDesc("应用授权失败,请选择要授权的资源!");
        } else {
            Role role = roleService.findById(roleId);
            List<Resource> resouceList = role.getResourceList();
            List<Resource> resourceListInIdStr = resourceService.findResourceListInIdStr(resourceIds);
            for (Resource resource : resourceListInIdStr) {
                if (!resouceList.contains(resource))
                    resouceList.add(resource);
            }
            role.setResourceList(resouceList);
            roleService.save(role);
            returnObject.setResult(true);
            returnObject.setResultDesc("应用授权成功!");
        }
        return returnObject;
    }

    /**
     * @param roleId
     * @param modelMap
     * @return 根据角色ID载入用户权限预览
     */
    @RequestMapping(value = "/loadAuthView/{roleId}", method = RequestMethod.GET)
    public String loadAuthView(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
        Role role = roleService.findById(roleId);
        List<VRoleAuthView> vRoleAuthViews = vRoleAuthViewRepository.findByRole(role);
        modelMap.put("vRoleAuthViews", vRoleAuthViews);
        return "resource/authList";
    }


    /**
     * @param roleId
     * @param modelMap
     * @return 根据角色ID载入用户权限预览
     */
    @RequestMapping(value = "/loadAuth/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public List<VRoleAuthView> loadAuth(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
        Role role = roleService.findById(roleId);
        List<VRoleAuthView> authViewList = vRoleAuthViewRepository.findByRole(role);
        return authViewList;
    }


    /**
     * @param userId
     * @return 根据用户ID载入用户权限预览
     */
    @RequestMapping(value = "/loadModule/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<VRoleAuthView> loadAuthViewByUserId(@PathVariable("userId") Long userId) {
        List<VRoleAuthView> vRoleAuthViews = resourceService.findResourcesByUserIdAndResourceLevel(userId, 1l);
        return vRoleAuthViews;
    }


    /**
     * @param userId
     * @return 根据用户ID载入用户权限预览
     */


    @RequestMapping(value = "/loadApp/{moduleId}/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<VRoleAuthView> loadAuthViewByUserIdAndModuleId(@PathVariable("moduleId") Long moduleId, @PathVariable("userId") Long userId) {
        List<VRoleAuthView> vRoleAuthViews = resourceService.findResourcesByUserIdAndResourceLevelAndParentId(userId, 2l, moduleId);
        return vRoleAuthViews;
    }
}

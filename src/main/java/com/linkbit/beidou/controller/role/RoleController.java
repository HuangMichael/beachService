package com.linkbit.beidou.controller.role;


import com.linkbit.beidou.dao.role.RoleRepository;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.role.Role;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleService roleService;
    @Autowired
    ResourceService resourceService;
    @RequestMapping(value = "/create")
    public String create() {
        return "/role/create";
    }

    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap, HttpSession httpSession) {
        List<Role> roleList = roleRepository.findAll();
        modelMap.put("roleList", roleList);
        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);
        return "/role/list";
    }


    /**
     * 保存角色信息
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public Role save(Role role) {
        return roleRepository.save(role);
    }


    /**
     * 保存角色信息
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public Role update(@RequestParam("roleId") Long roleId, @RequestParam("roleDesc") String roleDesc) {
        Role role = roleService.findById(roleId);
        role.setRoleDesc(roleDesc);
        return roleRepository.save(role);
    }


    /**
     * 载入明细信息
     */
    @RequestMapping(value = "/detail/{roleId}")
    public String detail(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
        Role role = roleRepository.findById(roleId);
        modelMap.put("role", role);
        return "/role/detail";
    }


    /**
     * 保存角色信息
     *
     * @return 查询在用的角色
     */
    @RequestMapping(value = "/findActiveRole", method = {RequestMethod.GET})
    @ResponseBody
    public List<Role> findActiveRole() {
        return roleService.findActiveRole();

    }


    /**
     * @return 查询在用的角色
     */
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Role findById(@PathVariable Long id) {
        return roleService.findById(id);
    }


    /**
     * 载入明细信息
     */
    @RequestMapping(value = "/popUsers/{roleId}", method = RequestMethod.GET)
    public String popUsers(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
        List<Object> usersNotInRole = roleService.findUsersNotInRole(roleId);
        modelMap.put("usersNotInRole", usersNotInRole);
        return "/role/popUsers";
    }


    /**
     * 载入明细信息
     */
    @RequestMapping(value = "/loadMyUsers/{roleId}", method = RequestMethod.GET)
    public String loadMyUsers(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
        Role role = roleService.findById(roleId);
        modelMap.put("role", role);
        return "/role/myUsers";
    }

    /**
     * @return 查询不在当前角色中的用户
     */
    @RequestMapping(value = "/findUsersNotInRole/{roleId}", method = {RequestMethod.GET})
    @ResponseBody
    public List<Object> findUsersNotInRole(@PathVariable("roleId") Long roleId) {
        return roleService.findUsersNotInRole(roleId);
    }


    /**
     * @return 查询不在当前角色中的用户
     */
    @RequestMapping(value = "/addUser", method = {RequestMethod.POST})
    @ResponseBody
    public ReturnObject addUsers(@RequestParam("roleId") Long roleId, @RequestParam("usersIdStr") String usersIdStr) {
        return roleService.addUsers(roleId, usersIdStr);
    }

    /**
     * @param roleId
     * @return 根据角色查询用户列表
     */
    @RequestMapping(value = "/findUsersOfRole/{roleId}", method = {RequestMethod.GET})
    @ResponseBody
    public List<User> findUsersOfRole(@PathVariable("roleId") Long roleId) {
        return roleService.findUsersOfRole(roleId);
    }



    /**
     * @return 查询不在当前角色中的用户
     */
    @RequestMapping(value = "/removeUser", method = {RequestMethod.POST})
    @ResponseBody
    public ReturnObject removeUser(@RequestParam("roleId") Long roleId, @RequestParam("userId") Long userId) {
        return roleService.removeUser(roleId, userId);
    }



}






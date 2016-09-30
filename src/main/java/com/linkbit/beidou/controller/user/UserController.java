package com.linkbit.beidou.controller.user;


import com.linkbit.beidou.dao.locations.VlocationsRepository;
import com.linkbit.beidou.dao.person.PersonRepository;
import com.linkbit.beidou.dao.user.UserRepository;
import com.linkbit.beidou.domain.app.MyPage;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.domain.equipments.Vequipments;
import com.linkbit.beidou.domain.locations.Vlocations;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import com.linkbit.beidou.service.user.UserService;
import com.linkbit.beidou.utils.CommonStatusType;
import com.linkbit.beidou.utils.MD5Util;
import com.linkbit.beidou.utils.SessionUtil;
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
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    VlocationsRepository vlocationsRepository;

    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = "/list")
    public String list(HttpSession httpSession,ModelMap modelMap) {
        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);
        return "/user/list";
    }


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
    public MyPage data( @RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount, @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        Page<User> page = userService.findByUserNameContrains(searchPhrase,new PageRequest(current - 1, rowCount.intValue()));
        MyPage myPage = new MyPage();
        myPage.setRows(page.getContent());
        myPage.setRowCount(rowCount);
        myPage.setCurrent(current);
        myPage.setTotal(page.getTotalElements());
        return myPage;
    }


    @RequestMapping(value = "/personal")
    public String personal(ModelMap modelMap) {
        return "/user/personal";
    }


    @RequestMapping(value = "/create")
    public String create(ModelMap modelMap) {
        return "/user/create";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject update(@RequestParam("userId") Long userId, @RequestParam("personId") Long personId, @RequestParam("locationId") Long locationId, @RequestParam("status") String status) {
        ReturnObject returnObject = new ReturnObject();
        User user = null;
        if (userId != null && personId != null && locationId != null) {
            user = userService.update(userId, personId, locationId, status);
        }
        if (user != null) {
            returnObject.setResult(true);
            returnObject.setResultDesc("用户更新成功!");
        } else {
            returnObject.setResult(false);
            returnObject.setResultDesc("用户更新失败 !");
        }
        return returnObject;
    }

    /**
     * @return 查询所有的用户信息
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<User> findAll() {
        return userService.findAllUsers();
    }


    /**
     * 保存用户信息
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public ReturnObject save(@RequestParam("personId") Long personId, @RequestParam("locationId") Long locationId) {
        User user = new User();
        user.setPerson(personRepository.findById(personId));
        Vlocations vlocations =vlocationsRepository.findById(locationId);
        user.setVlocations(vlocations);
        user.setLocation(vlocations.getLocation());
        user = userService.save(user);
        ReturnObject returnObject = new ReturnObject();
        returnObject.setResult(user != null);
        String resStr = returnObject.getResult() ? "成功" : "失败";
        returnObject.setResultDesc("用户信息保存" + resStr);
        returnObject.getObjectsList().add(user);
        return returnObject;
    }


    /**
     * 保存用户信息
     *
     * @param personId
     * @param locationId
     * @return 创建用户
     */
    @RequestMapping(value = "/createUser", method = {RequestMethod.POST})
    @ResponseBody
    public ReturnObject createUser(@RequestParam("userName") String userName, @RequestParam("personId") Long personId, @RequestParam("locationId") Long locationId) {
        User user = new User();
        user.setUserName(userName);
        user.setPerson(personRepository.findById(personId));
        Vlocations vlocations =vlocationsRepository.findById(locationId);
        user.setVlocations(vlocations);
        user.setLocation(vlocations.getLocation());
        user = userService.createUser(user);
        ReturnObject returnObject = new ReturnObject();
        returnObject.setResult(user != null);
        String resStr = returnObject.getResult() ? "成功" : "失败";
        returnObject.setResultDesc("用户信息创建" + resStr);
        returnObject.getObjectsList().add(user);
        return returnObject;
    }

    /**
     * @param session  当前会话
     * @param modelMap 显示个人信息
     * @return
     */
    @RequestMapping(value = "/profile")
    public String profile(HttpSession session, ModelMap modelMap) {
        User user = SessionUtil.getCurrentUserBySession(session);
        modelMap.put("user", user);
        return "/user/profile";
    }


    /**
     * @param id 用户id
     * @return 根据用户id查询
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }


    /**
     * @return 修改密码
     */
    @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject changePwd(@RequestParam("userName") String userName, @RequestParam("newPwd") String newPwd) {
        ReturnObject returnObject = new ReturnObject();
       boolean result = userService.changePwd(userName,newPwd);
        returnObject.setResult(result);
        if (returnObject.getResult()) {
            returnObject.setResultDesc("用户密码修改成功!");
        } else {
            returnObject.setResultDesc("用户密码修改失败!");
        }
        return returnObject;
    }


    /**
     * 检查用户密码合法性
     */
    @RequestMapping(value = "/checkPwd", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject checkPwd(@RequestParam("userName") String userName, @RequestParam("oldPwd") String oldPwd) {
        ReturnObject returnObject = new ReturnObject();
        oldPwd = MD5Util.md5(oldPwd);
        List<User> userList = userService.findByUserNameAndPasswordAndStatus(userName, oldPwd, CommonStatusType.STATUS_YES);
        if (!userList.isEmpty()) {
            returnObject.setResult(true);
            returnObject.setResultDesc("用户密码验证通过!");
        } else {
            returnObject.setResult(false);
            returnObject.setResultDesc("用户密码验证失败!");
        }
        return returnObject;

    }

}

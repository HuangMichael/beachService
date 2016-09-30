package com.linkbit.beidou.controller.resource;

import com.linkbit.beidou.domain.app.resoure.Resource;
import com.linkbit.beidou.domain.app.resoure.VRoleAuthView;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.ResourceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 2015/12/23 0023.
 * 位置控制器类
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    /**
     * @param modelMap
     * @param httpSession
     * @return 初始化载入界面
     */
    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap, HttpSession httpSession) {
        String controllerName = this.getClass().getSimpleName().split("Controller")[0];
        List<VRoleAuthView> appMenus = resourceService.findAppMenusByController(httpSession, controllerName.toUpperCase());
        modelMap.put("appMenus", appMenus);
        return "/resource/list";

    }

    /**
     * 查询所有的节点
     */

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> findAll() {
        List<Resource> resourceList = resourceService.findAll(); //查询二级模块
        return resourceList;
    }

    /**
     * 查询所有的节点
     */

    @RequestMapping(value = "/findApps", method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> findApps() {
        List<Resource> resourceList = resourceService.findAll(); //查询二级模块
        return resourceList;
    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/create/{id}")
    public String create(@PathVariable("id") Long id, ModelMap modelMap) {
        Resource parent = resourceService.findById(id);
        Resource newObj = new Resource();
        newObj.setParent(parent);
        //加载上级列表
        List<Resource> resourceList = resourceService.findAll();
        modelMap.put("resourceList", resourceList);
        modelMap.put("resource", newObj);
        modelMap.put("parent", parent);
        //查询出所有的设备分类
        return "/resource/create";
    }


    /**
     * 查询根节点
     */
    @RequestMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap modelMap) {
        String url = "/resource";
        if (id != null) {
            url += "/detail";
        } else {
            url += "/list/";
        }
        Resource resource = resourceService.findById(id);
        List<Resource> resourceList = resourceService.findByParent(resource);
        modelMap.put("resource", resource);
        modelMap.put("resourceList", resourceList);
        return url;
    }

    /**
     * 根据ID查询
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Resource findById(@PathVariable("id") Long id) {
        Resource resource = resourceService.findById(id);
        return resource;
    }

    /**
     * 保存资源信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(@RequestParam("id") Long id,
                             @RequestParam("resourceName") String resourceName,
                             @RequestParam("resourceCode") String resourceCode,
                             @RequestParam("description") String description,
                             @RequestParam("resourceUrl") String resourceUrl,
                             @RequestParam("iconClass") String iconClass,
                             @RequestParam("appName") String appName,
                             @RequestParam("parentId") Long parentId,
                             @RequestParam(value = "staticFlag", required = false) boolean staticFlag,
                             @RequestParam(value = "sortNo", required = false) Long sortNo) {


        ReturnObject returnObject = new ReturnObject();
        Resource resource = new Resource();
        resource.setResourceName(resourceName);
        resource.setResourceCode(resourceCode);
        resource.setDescription(description);
        resource.setResourceUrl(resourceUrl);
        resource.setIconClass(iconClass);
        resource.setAppName(appName);
        resource.setParent(resourceService.findById(parentId));
        resource.setStaticFlag(staticFlag);
        resource.setSortNo(sortNo);
        resource = resourceService.save(resource);
        returnObject.setResult(resource != null);
        returnObject.setResultDesc("资源信息保存成功");
        return returnObject;
    }


    /**
     * 保存资源信息
     *
     * @param id
     * @param resourceName
     * @param description
     * @param resourceUrl
     * @param iconClass
     * @param appName
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject update(
            @RequestParam("id") Long id,
            @RequestParam("resourceName") String resourceName,
            @RequestParam("description") String description,
            @RequestParam("resourceUrl") String resourceUrl,
            @RequestParam("iconClass") String iconClass,
            @RequestParam("appName") String appName,
            @RequestParam("parentId") Long parentId,
            @RequestParam(value = "staticFlag", required = false) boolean staticFlag,
            @RequestParam(value = "sortNo", required = false) Long sortNo
    ) {
        ReturnObject returnObject = new ReturnObject();
        Resource resource = resourceService.findById(id);
        resource.setResourceName(resourceName);
        resource.setDescription(description);
        resource.setResourceUrl(resourceUrl);
        resource.setIconClass(iconClass);
        resource.setAppName(appName);
        resource.setParent(resourceService.findById(parentId));
        resource.setStaticFlag(staticFlag);
        resource.setSortNo(sortNo);
        resource = resourceService.save(resource);
        returnObject.setResult(resource != null);
        returnObject.setResultDesc("资源更新成功!");
        return returnObject;
    }


    /**
     * 删除位置信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        boolean hasChildren = resourceService.hasChildren(id);
        Resource resource =null;
        if (!hasChildren) {
            resourceService.delete(id);
            resource = resourceService.findById(id);
        }
        return (resource==null);
    }
}

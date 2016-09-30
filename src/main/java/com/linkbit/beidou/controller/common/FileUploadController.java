package com.linkbit.beidou.controller.common;


import com.linkbit.beidou.dao.locations.LocationsRepository;
import com.linkbit.beidou.domain.locations.Locations;
import com.linkbit.beidou.utils.SessionUtil;
import com.linkbit.beidou.utils.UploadUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huangbin on 2015/12/23 0023.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/upload")
public class FileUploadController {


    @Autowired
    LocationsRepository locationsRepository;
    Log log = LogFactory.getLog(FileUploadController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public
    @ResponseBody
    String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Locations handleFileUpload(@RequestParam("llid") Long llid, @RequestParam("name") String name, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        log.info("isMultipart-------------" + isMultipart);

        String contextPath = SessionUtil.getContextPath(request);
        String realPath = "img\\app\\locations\\" + name;
        String filePath = contextPath + realPath;
        log.info(contextPath);
        log.info(realPath);
        log.info(filePath);
        UploadUtil.uploadFile(file, filePath);
        Locations locations = locationsRepository.findById(llid);
        locations.setImgUrl("/"+realPath.replace("\\","/"));
        locations = locationsRepository.save(locations);
        return locations;

    }
}





package com.linkbit.beidou.controller.app;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/error")
public class AppErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public String handleError() {
        return "/pages/error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}




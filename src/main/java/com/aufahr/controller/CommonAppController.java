package com.aufahr.controller;

import com.aufahr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Controller
public class CommonAppController implements ErrorController{

    @RequestMapping("/")
    public String index(){
        return "content/common/index";
    }

    @RequestMapping("/error")
    public String errorPage(Model model){
        model.addAttribute("message", "Terjadi kesalahan sistem");
        model.addAttribute("detail", "");
        return "content/common/defaultMessage";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}

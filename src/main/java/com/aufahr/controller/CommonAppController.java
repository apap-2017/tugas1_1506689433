package com.aufahr.controller;

import com.aufahr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AufaHR on 10/21/2017.
 */
@Controller
public class CommonAppController {

    @RequestMapping("/")
    public String index(){
        return "";
    }

}

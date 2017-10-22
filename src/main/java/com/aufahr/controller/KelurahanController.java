package com.aufahr.controller;

import com.aufahr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AufaHR on 10/21/2017.
 */

@Controller
@RequestMapping("/kelurahan")
public class KelurahanController {
    @Autowired
    private KelurahanService kelurahanService;

}

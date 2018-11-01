package com.baggga.job_test.controller;

import com.baggga.job_test.bean.VisitInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/visit-info")
public class VisitInfoController {
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createVisitInfo(@ModelAttribute VisitInfo visitInfo) {
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getVisitInfo(Model model) {
        return null;
    }
}
package com.baggga.job_test.controller;

import com.baggga.job_test.bean.VisitInfo;
import com.baggga.job_test.service.VisitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("visit-info")
public class VisitInfoController {

    private final VisitInfoService visitInfoService;

    @Autowired
    public VisitInfoController(VisitInfoService visitInfoService) {
        this.visitInfoService = visitInfoService;
    }

    @RequestMapping(value = "/")
    public List<VisitInfo> index() {
        return visitInfoService.findAll();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create() {
        return "Create";
    }
}
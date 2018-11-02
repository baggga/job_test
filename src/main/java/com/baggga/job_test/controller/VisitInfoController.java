package com.baggga.job_test.controller;

import com.baggga.job_test.bean.VisitInfo;
import com.baggga.job_test.service.VisitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("visit-info")
public class VisitInfoController {

    private final VisitInfoService visitInfoService;

    @Autowired
    public VisitInfoController(VisitInfoService visitInfoService) {
        this.visitInfoService = visitInfoService;
    }

    @GetMapping()
    List<VisitInfo> list() {
        return visitInfoService.findAll();
    }

    @PostMapping()
    VisitInfo create(@RequestBody VisitInfo visitInfo) throws InterruptedException, ExecutionException {
        visitInfo.setVisitTime(LocalDate.now());
        return visitInfoService.create(visitInfo).get();
    }
}
package com.baggga.job_test.controller;

import com.baggga.job_test.bean.VisitInfo;
import com.baggga.job_test.service.VisitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("visit-info")
public class VisitInfoController {

    private final VisitInfoService visitInfoService;

    @Autowired
    public VisitInfoController(VisitInfoService visitInfoService) {
        this.visitInfoService = visitInfoService;
    }

    @PostMapping()
    String create(@RequestBody VisitInfo visitInfo) throws ExecutionException, InterruptedException {
        return visitInfoService.asyncCreateVisitInfo(visitInfo).get();
    }

    @GetMapping()
    String list(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(value = "from") LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(value = "to") LocalDate to) {
        return visitInfoService.getStatisticsForPeriod(from, to);
    }
}
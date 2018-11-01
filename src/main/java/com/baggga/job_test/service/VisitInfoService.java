package com.baggga.job_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggga.job_test.bean.VisitInfo;
import com.baggga.job_test.repository.VisitInfoRepository;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;


@Service
public class VisitInfoService {
    @Autowired
    private VisitInfoRepository visitInfoRepository;

    @Transactional
    @PostConstruct
    public void init() {
        visitInfoRepository.save(new VisitInfo(1, "A", new Date()));
    }

    public List<VisitInfo> findAll() {
        return visitInfoRepository.findAll();
    }
}

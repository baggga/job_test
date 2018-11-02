package com.baggga.job_test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baggga.job_test.bean.VisitInfo;
import com.baggga.job_test.repository.VisitInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.List;


@Service
public class VisitInfoService {
    private static final Logger logger = LoggerFactory.getLogger(VisitInfoService.class);

    private final VisitInfoRepository visitInfoRepository;

    @Autowired
    public VisitInfoService(VisitInfoRepository visitInfoRepository) {
        this.visitInfoRepository = visitInfoRepository;
    }

    @Transactional
    @PostConstruct
    public void init() {
        visitInfoRepository.save(new VisitInfo(1, "page1", LocalDate.of(2018, 3, 20)));
        visitInfoRepository.save(new VisitInfo(2, "page2", LocalDate.of(2018, 2, 13)));
        visitInfoRepository.save(new VisitInfo(3, "page3", LocalDate.of(2018, 1, 1)));
    }

    @Async
    public CompletableFuture<VisitInfo> create(VisitInfo visitInfo) throws InterruptedException {
        logger.info("Save visit info for user " + visitInfo.getUserId());
        VisitInfo newVisitInfo = visitInfoRepository.save(visitInfo);
        logger.info("Visit info saved");
        Thread.sleep(3000L);
        return CompletableFuture.completedFuture(newVisitInfo);
    }

    public List<VisitInfo> findAll() {
        return visitInfoRepository.findAll();
    }
}

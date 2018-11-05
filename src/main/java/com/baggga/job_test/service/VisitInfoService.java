package com.baggga.job_test.service;

import com.baggga.job_test.bean.VisitInfo;
import com.baggga.job_test.repository.VisitInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;


@Service
public class VisitInfoService {
    private final VisitInfoRepository visitInfoRepository;

    @Autowired
    public VisitInfoService(VisitInfoRepository visitInfoRepository) {
        this.visitInfoRepository = visitInfoRepository;
    }

    private String createVisitInfo(VisitInfo visitInfo) {
        LocalDate today = LocalDate.now();
        visitInfo.setVisitTime(today);
        visitInfoRepository.save(visitInfo);
        int totalVisitCount = visitInfoRepository.countAllByVisitTimeIsBetween(today, today);
        int uniqueUserCount = visitInfoRepository.uniqueUsersCount(today, today);
        return "{\"totalVisitCount\":" + totalVisitCount + ", \"uniqueUserCount\":" + uniqueUserCount + "}";
    }

    @Async
    public CompletableFuture<String> asyncCreateVisitInfo(VisitInfo visitInfo) {
        return CompletableFuture.completedFuture(createVisitInfo(visitInfo));
    }

    public String getStatisticsForPeriod(LocalDate from, LocalDate to) {
        int totalVisitCount = visitInfoRepository.countAllByVisitTimeIsBetween(from, to);
        int uniqueUserCount = visitInfoRepository.uniqueUsersCount(from, to);
        int regularUserCount = visitInfoRepository.regularUsersCount(from, to);
        return "{\"totalVisitCount\":" + totalVisitCount + ", \"uniqueUserCount\":" + uniqueUserCount + ", " +
                "\"regularUserCount\":" + regularUserCount + "}";
    }
}

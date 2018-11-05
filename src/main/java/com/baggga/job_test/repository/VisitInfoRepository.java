package com.baggga.job_test.repository;

import com.baggga.job_test.bean.VisitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface VisitInfoRepository extends JpaRepository<VisitInfo, Integer> {
    Integer countAllByVisitTimeIsBetween(LocalDate from, LocalDate to);

    @Query(value = "SELECT COUNT(DISTINCT USER_ID) FROM VisitInfo WHERE visitTime BETWEEN :from AND :to",
            nativeQuery = true)
    Integer uniqueUsersCount(LocalDate from, LocalDate to);

    @Query(value = "SELECT COUNT(DISTINCT vi.USER_ID) FROM VisitInfo vi WHERE vi.USER_ID IN (" +
            "SELECT vi1.USER_ID FROM VisitInfo vi1 GROUP BY vi1.USER_ID HAVING COUNT(DISTINCT vi1.PAGE_ID) >= 10) AND vi.visitTime BETWEEN :from AND :to",
            nativeQuery = true)
    Integer regularUsersCount(LocalDate from, LocalDate to);
}

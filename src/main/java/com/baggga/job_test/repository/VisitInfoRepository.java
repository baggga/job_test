package com.baggga.job_test.repository;

import com.baggga.job_test.bean.VisitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface VisitInfoRepository extends JpaRepository<VisitInfo, Integer> {
    Integer countAllByVisitTimeIsBetween(LocalDate from, LocalDate to);

    @Query(value = "SELECT COUNT(DISTINCT USER_ID) FROM VisitInfo WHERE visitTime BETWEEN :from AND :to",
            nativeQuery = true)
    Integer uniqueUsersCount(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query(value = "SELECT COUNT(DISTINCT vi.USER_ID) FROM VisitInfo vi WHERE vi.USER_ID IN (" +
            "SELECT vi1.USER_ID FROM VisitInfo vi1 WHERE vi1.visittime >= :from AND vi1.visittime <= :to GROUP BY vi1.USER_ID HAVING COUNT(DISTINCT vi1.PAGE_ID) >= 10)",
            nativeQuery = true)
    Integer regularUsersCount(@Param("from") LocalDate from, @Param("to") LocalDate to);
}

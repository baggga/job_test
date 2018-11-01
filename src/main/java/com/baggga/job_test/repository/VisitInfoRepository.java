package com.baggga.job_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.baggga.job_test.bean.VisitInfo;

public interface VisitInfoRepository extends JpaRepository<VisitInfo, Integer>{
}

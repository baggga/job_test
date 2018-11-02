package com.baggga.job_test.bean;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class VisitInfo {
    @Id
    @GeneratedValue
    private long userId;

    private String pageId;

    private LocalDate visitTime;

    public VisitInfo() {
    }

    public VisitInfo(long userId, String pageId, LocalDate visitTime) {
        this.userId = userId;
        this.pageId = pageId;
        this.visitTime = visitTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public LocalDate getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDate visitTime) {
        this.visitTime = visitTime;
    }
}

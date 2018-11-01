package com.baggga.job_test.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class VisitInfo {
    @Id
    @GeneratedValue
    private long userId;
    private String pageId;
    private Date visitTime;

    public VisitInfo() {
    }

    public VisitInfo(long userId, String pageId, Date visitTime) {
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

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
}
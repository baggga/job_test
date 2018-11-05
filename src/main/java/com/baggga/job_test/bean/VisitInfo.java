package com.baggga.job_test.bean;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visitinfo")
public class VisitInfo {
    @Id
    @GeneratedValue
    private long id;
    private String userId;
    private String pageId;
    @Column(name = "visittime")
    private LocalDate visitTime;

    public VisitInfo() {
    }

    public VisitInfo(String userId, String pageId) {
        this.userId = userId;
        this.pageId = pageId;
    }

    public VisitInfo(String userId, String pageId, LocalDate visitTime) {
        this.userId = userId;
        this.pageId = pageId;
        this.visitTime = visitTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

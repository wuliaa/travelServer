package com.example.travelserver.model;

import java.util.Date;

public class Comment {
    private Long id;

    private String userId;

    private String content;

    private Date createTime;

    private Long guidelineId;

    private Long parentId;

    private String parentNickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getGuidelineId() {
        return guidelineId;
    }

    public void setGuidelineId(Long guidelineId) {
        this.guidelineId = guidelineId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentNickname() {
        return parentNickname;
    }

    public void setParentNickname(String parentNickname) {
        this.parentNickname = parentNickname;
    }
}
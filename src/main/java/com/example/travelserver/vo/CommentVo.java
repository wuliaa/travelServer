package com.example.travelserver.vo;

import java.util.Date;
import java.util.List;

public class CommentVo {
    private Long id;

    private String userId;

    private String content;

    private Date createTime;

    private Long guidelineId;

    private Long parentId;

    private String parentNickname;

    public String getParentNickname() {
        return parentNickname;
    }

    public void setParentNickname(String parentNickname) {
        this.parentNickname = parentNickname;
    }

    private UserVo user;
    private List<CommentVo> child;

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public List<CommentVo> getChild() {
        return child;
    }

    public void setChild(List<CommentVo> child) {
        this.child = child;
    }

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
}

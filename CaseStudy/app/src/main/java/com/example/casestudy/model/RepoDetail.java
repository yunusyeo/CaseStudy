package com.example.casestudy.model;

import java.io.Serializable;

public class RepoDetail implements Serializable {
    private Integer id;
    private String repoName;
    private String userName;
    private String userAvatarUrl;
    private Integer repoStarsCount;
    private Integer repoOpenIssuesCount;

    public RepoDetail(Integer id, String repoName, String userName, String userAvatarUrl, Integer repoStarsCount, Integer repoOpenIssuesCount) {
        this.id = id;
        this.repoName = repoName;
        this.userName = userName;
        this.userAvatarUrl = userAvatarUrl;
        this.repoStarsCount = repoStarsCount;
        this.repoOpenIssuesCount = repoOpenIssuesCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public Integer getRepoStarsCount() {
        return repoStarsCount;
    }

    public void setRepoStarsCount(Integer repoStarsCount) {
        this.repoStarsCount = repoStarsCount;
    }

    public Integer getRepoOpenIssuesCount() {
        return repoOpenIssuesCount;
    }

    public void setRepoOpenIssuesCount(Integer repoOpenIssuesCount) {
        this.repoOpenIssuesCount = repoOpenIssuesCount;
    }
}

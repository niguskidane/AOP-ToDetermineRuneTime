package com.nk.aoptodetermineruntime.common;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
public class StatusInfo {

    protected int statusCode= HttpStatus.OK.value();
    protected String serverity="SUCCESS";
    protected String userDescription="SUCCESS";
    protected String lable;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getServerity() {
        return serverity;
    }

    public void setServerity(String serverity) {
        this.serverity = serverity;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}

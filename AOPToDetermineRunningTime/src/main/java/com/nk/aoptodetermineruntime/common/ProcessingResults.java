package com.nk.aoptodetermineruntime.common;

import org.springframework.stereotype.Repository;

@Repository
public class ProcessingResults {
    protected StatusInfo statusInfo = new StatusInfo();
    protected String transactionId = Utilities.getTransactionId();


    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(StatusInfo statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}

package com.nk.aoptodetermineruntime.common;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;

@Repository
public class BaseResponse {

    protected ProcessingResults processingResults;
    @JsonIgnore
    protected Object responseData;
    @JsonIgnore
    protected String name = "";
    @JsonIgnore
    protected String respSummary = "NA";

    public BaseResponse() {
    }

    public BaseResponse(String name) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "";
        }
    }

    public ProcessingResults getProcessingResults() {

        return processingResults;
    }

    public void setProcessingResults(ProcessingResults processingResults) {
        this.processingResults = processingResults;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRespSummary() {
        return respSummary;
    }

    public void setRespSummary(String respSummary) {
        this.respSummary = respSummary;
    }

    @JsonAnyGetter
    public Map<String, Object> any(){
        if(!name.isEmpty()){
            return Collections.singletonMap(name, responseData);
        }else{
            return null;
        }
    }

    @Override
    public String toString(){
        if(!respSummary.equals("NA")){
            return respSummary;
        }
        return responseData!=null? responseData.toString(): "";
    }


}

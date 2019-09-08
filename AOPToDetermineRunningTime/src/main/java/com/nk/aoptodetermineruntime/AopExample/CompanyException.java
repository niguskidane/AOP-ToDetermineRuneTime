package com.nk.aoptodetermineruntime.AopExample;

public class CompanyException extends RuntimeException {

    public CompanyException(){

    }

    public CompanyException(String msg){
        super(msg);
    }
}

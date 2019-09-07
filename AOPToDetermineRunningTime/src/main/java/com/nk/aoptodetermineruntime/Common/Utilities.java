package com.nk.aoptodetermineruntime.Common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nk.aoptodetermineruntime.AopExample.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Utilities {

    private static final Logger logger= LoggerFactory.getLogger(Utilities.class);

    public enum CheckType{
        INTEGER, DOUBLE, STRING, TIMESTAMP;
    }

    private Utilities(){
        //don't allow this class to be instantiated
    }


    public static String getTransactionId(){
        //fixing the processing result up with the transaction Id
        if(MDC.get(Constants.TRANS_ID)==null){
            return "no id";
        }

        return MDC.get(Constants.TRANS_ID).substring(8);
    }


    //This Method will take a model type object and convert it to a string in a json format.
    public static String objectToString(Object object){
        logger.info("Converting "+object.getClass().getSimpleName()+" object to String ");

        ObjectMapper objectMapper=new ObjectMapper();
        try{
            return objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException ex){
            logger.error("Unable to convert object to string = "+object.getClass().getName());
            return "Can Not Convert Object";
        }
    }

}

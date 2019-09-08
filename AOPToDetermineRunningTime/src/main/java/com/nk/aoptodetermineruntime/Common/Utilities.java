package com.nk.aoptodetermineruntime.Common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nk.aoptodetermineruntime.AopExample.CompanyException;
import com.nk.aoptodetermineruntime.AopExample.Constants;
import com.nk.aoptodetermineruntime.model.Header;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static void checkInput(CheckType type, boolean isRequired, String data, String fieldName ){
        switch (type){
            case STRING:
                checkString(data, isRequired, fieldName);
                break;
            case INTEGER:
                checkInteger(data, isRequired, fieldName);
        }
    }


    public static void checkString(String s, boolean isRequired, String name){
        if(isRequired){
            if(s==null || s.isEmpty()){
                logger.error("Invalid Input -- " +name);
                throw new CompanyException("INVALID INPUT : " + name);
            }
        }
    }

    public static void checkString(String s){

            if(s==null || s.isEmpty()){
                throw new CompanyException("INVALID INPUT");
            }

    }

    public static void checkString(String s, String paramName){

            if(s==null || s.isEmpty()) {
                throw new CompanyException("INVALID INPUT : " + paramName);
            }
    }

    public static void checkInteger(String s, boolean isRequired, String name){
        if(isRequired){
            if(s==null || s.isEmpty()){
                logger.error("Invalid Input -- " +name);
                throw new CompanyException("INVALID INPUT : " + name);
            }else{
                s="0";
                return;
            }
        }

        try{
            Integer.parseInt(s);
        }catch (Exception e){
            logger.error("Invalid Input -- " +name);
            throw new CompanyException("INVALID INPUT : " + name);
        }
    }

    public static void checkInteger(String s){

            if(s==null || s.isEmpty()) {
                s="0";
                return;
            }

        try{
            Integer.parseInt(s);
        }catch (Exception e){
            throw  new CompanyException("INVALID INPUT");
        }
    }


    public static void checkTimeStamp(String s){
        try{
            new DateTime(s).toGregorianCalendar();
        }catch (Exception ex){
            throw  new CompanyException("INVALID INPUT");
        }
    }

    public static void checkTimeStamp(String s, String fieldName){
        try{
            new DateTime(s).toGregorianCalendar();
        }catch (Exception ex){
            throw  new CompanyException("INVALID INPUT : "+ fieldName);
        }
    }

    public static boolean isTimeStamp(String s){
        try{
            new DateTime(s).toGregorianCalendar();
        }catch (Exception ex){
           return false;
        }
        return true;
    }

    public static void checkDouble(String s){
        try{
            Double.parseDouble(s);
        }catch (Exception ex){
            throw  new CompanyException("INVALID INPUT");
        }
    }

    public static Header fillHeaderForTesting(){
        Header header=new Header();
        header.setTimeStamp("2019-09-07 12:04:34.496");
        header.setLon("-100");
        header.setLat("100");
        header.setSessionId("1234567");
        header.setNetwork("0");
        header.setSignal("0");
        header.setCity("Test");
        header.setState("Ex");
        header.setVersion("0.0.0");
        return header;
    }

    //get the difference in minutes between two date using joda
    public static int differenceInMinutes(String dateThen, String dateNow){
        DateTime olderDate=null;
        DateTime recentDate=null;

        try{
            olderDate=new DateTime(dateThen);
            recentDate=new DateTime(dateNow);
        }catch (Exception ex){
            logger.error("Bad date input old date "+ dateThen + " recent date "+ dateNow);
            return 0;
        }
        return Minutes.minutesBetween(olderDate, recentDate).getMinutes();
    }

    public static void checkHeader(Header header) {
        if (header.getTimeStamp() != null && !header.getTimeStamp().isEmpty() && header.getLat() != null && !header.getLat().isEmpty()
                && header.getLon() != null && !header.getLon().isEmpty() && header.getCity() != null && !header.getCity().isEmpty() && header.getState() != null && !header.getState().isEmpty()
                && header.getVersion() != null && !header.getVersion().isEmpty() && header.getNetwork() != null && !header.getNetwork().isEmpty()
                && header.getSignal() != null && !header.getSignal().isEmpty() && header.getSessionId() != null && !header.getSessionId().isEmpty()) {

        } else {
            logger.info("Header information is not valid");
            throw new CompanyException("INVALID HEADER");
        }

        try {
            Timestamp.valueOf(getConvertedTimeStamp(header.getTimeStamp()));
            Double.parseDouble(header.getLat());
            Double.parseDouble(header.getLon());
            Integer.parseInt(header.getNetwork());
            Integer.parseInt(header.getSignal());
            Long.parseLong(header.getSessionId());
        } catch (Exception ex) {
            logger.info("Header information is not valid");
            throw new CompanyException("INVALID HEADER");
        }
    }


    public static String getConvertedTimeStamp(String timeStamp) {
        char[] chars = timeStamp.toCharArray();
        chars[10] = ' ';
        chars[chars.length] = ' ';
        return new String(chars).trim();
    }

    private void checkVersion(String version) throws Exception {
        if(version==null || version.isEmpty()){
            throw new Exception("Version is a required field");
        }
    }

    public static Date convertStringToDate(final String strDate) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date formatDate=simpleDateFormat.parse(strDate);
        return  formatDate;
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

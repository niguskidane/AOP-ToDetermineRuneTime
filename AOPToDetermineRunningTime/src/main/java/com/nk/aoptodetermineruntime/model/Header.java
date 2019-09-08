package com.nk.aoptodetermineruntime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nk.aoptodetermineruntime.AopExample.CompanyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;

public class Header {

    private static final Logger logger = LoggerFactory.getLogger(Header.class);
    private String timeStamp;
    private String lat;
    private String lon;
    private String city;
    private String state;
    private String version;
    private String network;
    private String signal;
    private String sessionId;
    private String driver;
    private String device;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @JsonIgnore
    public void checkHeader() {
        if (timeStamp != null && !timeStamp.isEmpty() && lat != null && !lat.isEmpty() && lon != null && !lon.isEmpty() && city != null && !city.isEmpty() && state != null && !state.isEmpty()
                && version != null && !version.isEmpty() && network != null && !network.isEmpty() && signal != null && !signal.isEmpty() && sessionId != null && !sessionId.isEmpty()) {

        } else {
            logger.info("Header information is not valid");
            throw new CompanyException("INVALID HEADER");
        }

        try {
            Time.valueOf(getConvertedTimeStamp());
            Double.parseDouble(lat);
            Double.parseDouble(lon);
            Integer.parseInt(network);
            Integer.parseInt(signal);
            Long.parseLong(sessionId);
        } catch (Exception ex) {
            logger.info("Header information is not valid");
            throw new CompanyException("INVALID HEADER");
        }
    }

    //Format of timestamp must be "yyyy-mm-dd hh:mm:ss.sss" and this method puts the given RMS timestamp in that format

    @JsonIgnore
    public String getConvertedTimeStamp() {
        char[] chars = timeStamp.toCharArray();
        chars[10] = ' ';
        chars[chars.length] = ' ';
        return new String(chars).trim();
    }

    @JsonIgnore
    private void checkVersionFormat() throws Exception {
        String[] nums = version.split("\\.");
        if (nums.length != 4) {
            throw new Exception("Improper version format");
        }
        for (String n : nums) {
            Integer.parseInt(n);
        }
    }
}

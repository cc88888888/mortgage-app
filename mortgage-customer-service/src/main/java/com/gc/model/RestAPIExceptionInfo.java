package com.gc.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestAPIExceptionInfo {

    public final String detail;
    public final String message;

    public RestAPIExceptionInfo(Exception ex, String detail) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
    }
}

package com.ogiogio.customerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class BaseResponse {
    private String responseCode;
    private String responseMessage;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}

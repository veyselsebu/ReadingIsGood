package com.tr.getir.ReadingIsGood.Model;

public class CustomExceptionModel {

    private String argName;
    private String errMessage;

    public CustomExceptionModel(String argName, String errMessage) {
        this.argName = argName;
        this.errMessage = errMessage;
    }

    public String getArgName() {
        return argName;
    }

    public void setArgName(String argName) {
        this.argName = argName;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}

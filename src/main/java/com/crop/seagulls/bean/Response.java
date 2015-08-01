package com.crop.seagulls.bean;

import java.sql.Timestamp;

public class Response {

    private ReturnCode returnCode;

    private int code;

    private String message;

    private Timestamp responseTime;

    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCode returnCode) {
        this.returnCode = returnCode;
        this.code = returnCode.getCode();
        this.message = returnCode.getMessage();
    }

    public Timestamp getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Timestamp responseTime) {
        this.responseTime = responseTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response() {
        responseTime = new Timestamp(System.currentTimeMillis());
    }

    public Response(ReturnCode returnCode, Object object) {
        this.returnCode = returnCode;
        this.responseTime = new Timestamp(System.currentTimeMillis());
        this.result = object;
        this.code = returnCode.getCode();
        this.message = returnCode.getMessage();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((responseTime == null) ? 0 : responseTime.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result + ((returnCode == null) ? 0 : returnCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Response other = (Response) obj;
        if (responseTime == null) {
            if (other.responseTime != null)
                return false;
        } else if (!responseTime.equals(other.responseTime))
            return false;
        if (result == null) {
            if (other.result != null)
                return false;
        } else if (!result.equals(other.result))
            return false;
        if (returnCode == null) {
            if (other.returnCode != null)
                return false;
        } else if (!returnCode.equals(other.returnCode))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Response [returnCode=" + returnCode + ", code=" + code + ", message=" + message + ", responseTime=" + responseTime + ", result="
                + result + "]";
    }

    
}

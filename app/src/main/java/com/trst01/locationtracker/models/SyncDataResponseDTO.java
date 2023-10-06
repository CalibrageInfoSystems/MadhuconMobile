package com.trst01.locationtracker.models;

public class SyncDataResponseDTO {
    private String Error;
    private String Message;

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}

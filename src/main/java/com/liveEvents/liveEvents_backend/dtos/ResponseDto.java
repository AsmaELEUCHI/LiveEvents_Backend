package com.liveEvents.liveEvents_backend.dtos;

public class ResponseDto {
    private String message;

    public ResponseDto() {
    }

    public ResponseDto(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

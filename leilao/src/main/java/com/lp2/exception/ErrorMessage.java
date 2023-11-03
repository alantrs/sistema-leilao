package com.lp2.exception;


import io.micronaut.serde.annotation.Serdeable;

@Serdeable.Deserializable
@Serdeable.Serializable
public class ErrorMessage {

    private String message;
    private Integer status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

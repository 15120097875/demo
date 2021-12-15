package com.mylock.handler;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CheckException extends RuntimeException {

    private static final long serialVersionUID = -8879123682017730252L;
    private Integer code;
    private String message;

    public CheckException(String message) {
        super(message);
        this.message = message;
    }

    public CheckException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}

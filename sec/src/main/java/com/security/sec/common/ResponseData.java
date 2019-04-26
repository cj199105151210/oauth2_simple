package com.security.sec.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResponseData implements Serializable {
    private String code;
    private String message;
    private Object data;

    public ResponseData(String code , String message , Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

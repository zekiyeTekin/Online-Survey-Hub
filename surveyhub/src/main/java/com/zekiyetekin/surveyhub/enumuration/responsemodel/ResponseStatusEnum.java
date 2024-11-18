package com.zekiyetekin.surveyhub.enumuration.responsemodel;

public enum ResponseStatusEnum {

    //2xx: Success
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NO_CONTENT(204, "No Content"),


    //4xx: Error
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),

    NOT_FOUND(404, "Not Found"),


    //5xx: Server Error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout");

    private final Integer code;
    private final String message;


    ResponseStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public Integer getCode(){
        return code;
    }

}

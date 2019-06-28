package com.book.store.model;

public class JsonResult {

    public static final String SUCCESS = "SUCCESS";

    public static final String FAILURE = "FAILURE";

    public static final String ERROR = "ERROR";

    private String status;

    private Object body;

    private String message;

    public static JsonResult success(Object body) {
        return new JsonResult(SUCCESS, body);
    }

    public static JsonResult failure(Object body) {
        return new JsonResult(FAILURE, body);
    }

    public static JsonResult error(Object body) {
        return new JsonResult(ERROR, body);
    }

    public JsonResult() {}

    private JsonResult(String status, Object body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

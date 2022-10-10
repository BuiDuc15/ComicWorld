package com.comicworld.layer.domain.model.failed_body_content;

public class FailedBodyContentV1 {

    private String type;
    private String message;

    public FailedBodyContentV1(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

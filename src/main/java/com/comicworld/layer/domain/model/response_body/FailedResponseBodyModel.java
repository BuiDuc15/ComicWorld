package com.comicworld.layer.domain.model.response_body;

public class FailedResponseBodyModel extends ResponseBodyModel {

    private Object error;

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}

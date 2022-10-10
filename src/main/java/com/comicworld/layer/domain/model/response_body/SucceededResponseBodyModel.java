package com.comicworld.layer.domain.model.response_body;

import com.comicworld.layer.domain.model.response_body.ResponseBodyModel;

public class SucceededResponseBodyModel extends ResponseBodyModel {

    private Object body;

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}

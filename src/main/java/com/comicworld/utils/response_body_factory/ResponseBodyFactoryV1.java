package com.comicworld.utils.response_body_factory;

import com.comicworld.layer.domain.model.response_body.FailedResponseBodyModel;
import com.comicworld.layer.domain.model.response_body.SucceededResponseBodyModel;
import com.comicworld.utils.Env;

public class ResponseBodyFactoryV1 {

    public static SucceededResponseBodyModel succeededResponseBody(Object content) {
        SucceededResponseBodyModel model = createSucceededResponseBodyTemplate(Env.OK_RESPONSE_CODE, Env.SUCCEEDED_STATUS);
        model.setBody(content);
        return model;
    }

    public static SucceededResponseBodyModel succeededResponseBody() {
        return createSucceededResponseBodyTemplate(Env.OK_RESPONSE_CODE, Env.SUCCEEDED_STATUS);
    }

    public static SucceededResponseBodyModel createdResponseBody(Object content) {
        SucceededResponseBodyModel model = createSucceededResponseBodyTemplate(Env.CREATED_RESPONSE_CODE, Env.SUCCEEDED_STATUS);
        model.setBody(content);
        return model;
    }

    public static SucceededResponseBodyModel createdResponseBody() {
        return createSucceededResponseBodyTemplate(Env.CREATED_RESPONSE_CODE, Env.SUCCEEDED_STATUS);
    }

    public static SucceededResponseBodyModel noContentResponseBody() {
        return createSucceededResponseBodyTemplate(Env.NO_CONTENT_RESPONSE_CODE, Env.SUCCEEDED_STATUS);
    }

    public static FailedResponseBodyModel badRequestResponseBody(Object content) {
        FailedResponseBodyModel model = createFailedResponseBodyTemplate(Env.BAD_REQUEST_RESPONSE_CODE, Env.FAILED_STATUS);
        model.setError(content);
        return model;
    }

    public static FailedResponseBodyModel unauthorizedResponseBody(Object content) {
        FailedResponseBodyModel model = createFailedResponseBodyTemplate(Env.UNAUTHORIZED_RESPONSE_CODE, Env.FAILED_STATUS);
        model.setError(content);
        return model;
    }

    public static FailedResponseBodyModel accessDeniedResponseBody(Object content) {
        FailedResponseBodyModel model = createFailedResponseBodyTemplate(Env.ACCESS_DENIED_RESPONSE_CODE, Env.FAILED_STATUS);
        model.setError(content);
        return model;
    }

    public static FailedResponseBodyModel unprocessableResponseBody(Object content) {
        FailedResponseBodyModel model = createFailedResponseBodyTemplate(Env.UNPROCESSABLE_RESPONSE_CODE, Env.FAILED_STATUS);
        model.setError(content);
        return model;
    }

    public static FailedResponseBodyModel conflictResponseBody(Object content) {
        FailedResponseBodyModel model = createFailedResponseBodyTemplate(Env.CONFLICT_CODE, Env.FAILED_STATUS);
        model.setError(content);
        return model;
    }

    private static SucceededResponseBodyModel createSucceededResponseBodyTemplate(Integer code, String status) {
        SucceededResponseBodyModel model = new SucceededResponseBodyModel();
        model.setCode(code);
        model.setStatus(status);
        return model;
    }

    private static FailedResponseBodyModel createFailedResponseBodyTemplate(Integer code, String status) {
        FailedResponseBodyModel model = new FailedResponseBodyModel();
        model.setCode(code);
        model.setStatus(status);
        return model;
    }

}

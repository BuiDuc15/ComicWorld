package com.comicworld.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {

    public static void doResetResponseToErrorState(HttpServletResponse response,
                                                   int statusCode,
                                                   Object responseBody) throws IOException {
        response.resetBuffer();;
        response.setContentType("application/json");
        response.setStatus(statusCode);
        response.getWriter().print(responseBody);
        response.flushBuffer();
    }

}

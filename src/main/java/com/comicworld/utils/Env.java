package com.comicworld.utils;


public class Env {

    public static Integer OK_RESPONSE_CODE = 200;
    public static Integer CREATED_RESPONSE_CODE = 201;
    public static Integer NO_CONTENT_RESPONSE_CODE = 204;

    public static Integer BAD_REQUEST_RESPONSE_CODE = 400;
    public static Integer UNAUTHORIZED_RESPONSE_CODE = 401;
    public static Integer ACCESS_DENIED_RESPONSE_CODE = 403;
    public static Integer UNPROCESSABLE_RESPONSE_CODE = 422;
    public static Integer CONFLICT_CODE = 409;

    public static String SUCCEEDED_STATUS = "SUCCEEDED";
    public static String FAILED_STATUS = "FAILED";

    public static String BAD_REQUEST_EXCEPTION_TYPE = "BadRequestException";
    public static String UNAUTHORIZED_EXCEPTION_TYPE = "UnauthorizedException";
    public static String ACCESS_DENIED_EXCEPTION_TYPE = "AccessDeniedException";
    public static String UNPROCESSABLE_EXCEPTION_TYPE = "UnprocessableException";
    public static String ILLEGAL_MEDIA_EXCEPTION_TYPE = "IllegalMediaException";
    public static String UNSUPPORTED_MEDIA_TYPE_EXCEPTION_TYPE = "UnsupportedMediaTypeException";
    public static String CONFLICT_EXCEPTION_TYPE = "ConflictException";

    public static String ADMIN_SIGN_IN_ENDPOINT = "/admin/v1/authentication/signin";
    public static String CLIENT_SIGN_IN_ENDPOINT = "/v1/authentication/signin";

    public static Integer ACCESS_TOKEN_TYPE = 0;
    public static Integer REFRESH_TOKEN_TYPE = 1;

    public static Integer TRANSLATOR_GROUP_SEARCH_LIMIT = 20;
    public static Integer ACCOUNT_SEARCH_LIMIT = 20;
    public static Integer COMIC_SEARCH_LIMIT = 30;
    public static Integer AUTHOR_SEARCH_LIMIT = 10;
    public static Integer CATEGORY_SEARCH_LIMIT = 10;

    public static Integer DEFAULT_COMMENT_PAGE_SIZE = 5;

    public static Integer DEFAULT_PAGE_SIZE = 12;

    public static String S3_STORAGE_TYPE = "S3";

}

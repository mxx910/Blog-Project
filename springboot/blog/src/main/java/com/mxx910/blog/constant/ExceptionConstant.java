package com.mxx910.blog.constant;

/**
 * @author: mxx910
 * @date: 2023/4/19
 * @description:
 */
public class ExceptionConstant {
    public static final String IP_TO_REGION_FILE_LOAD_ERROR = "ip2region.db加载失败";
    public static final String EMAIL_CODE_IS_NULL = "验证码未发送或已过期！";
    public static final String EMAIL_CODE_NOT_MATCH = "验证码错误，请重新输入！";
    public static final String REGISTER_EMAIL_EXISTS = "邮箱已被注册";

    public static final String REGISTER_USER_EXISTS = "用户名已被注册";

    public static final String EMAIL_FORMAT_ERROR = "邮箱格式错误";
    public static final String USER_PASSWORD_ERROR = "用户名或密码错误";
    public static final String TOKEN_IS_NULL = "token校验失败";
    public static final String INVALID_TOKEN = "token校验失败";
    public static final String TOKEN_TIMEOUT = "token已过期";
    public static final String RESOURCE_NOT_FOUND = "token已过期";
}

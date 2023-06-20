package com.mxx910.blog.utils;
import cn.dev33.satoken.secure.SaSecureUtil;
import org.apache.commons.lang3.StringUtils;
/**
 * @author: mxx910
 * @date: 2023/4/19
 * @description:
 */
public class SecurityUtils {
    /**
     * 校验密码
     *
     * @param target 密文
     * @param password 密码
     * @return 是否正确
     */
    public static boolean checkPw(String target, String password) {
        String encryptedPassword = sha256Encrypt(password);
        return StringUtils.equals(encryptedPassword, target);
    }

    /**
     * sha256加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    public static String sha256Encrypt(String password) {
        return SaSecureUtil.sha256(password);
    }

}

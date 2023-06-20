package com.mxx910.blog.service;

import com.mxx910.blog.model.DTO.LoginDTO;
import com.mxx910.blog.model.DTO.RegisterDTO;

/**
 * @author mxx910
 */
public interface LoginService {
    /**
     * 用户登录
     *
     * @param login 登录参数
     * @return token
     */
    String login(LoginDTO login);

    /**
     * 发送验证码
     *
     * @param email 邮箱
     */
    void sendCode(String email);

    /**
     * 用户注册
     *
     * @param register 注册信息
     */
    void register(RegisterDTO register);
}

package com.mxx910.blog.service;

import com.mxx910.blog.model.VO.RouterVO;
import com.mxx910.blog.model.VO.UserBackInfoVO;
import com.mxx910.blog.model.VO.UserInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author mxx910
 */
public interface UserService {
    /**
     * 获取后台登录用户信息
     *
     * @return 后台用户信息
     */
    UserBackInfoVO getUserBackInfo();

    /**
     * 获取登录用户信息
     *
     * @return 用户信息
     */
    UserInfoVO getUserInfo();

    /**
     * 获取登录用户菜单
     *
     * @return 登录用户菜单
     */
    List<RouterVO> getUserMenu();

    /**
     * 修改用户头像
     *
     * @param file 头像
     * @return 头像链接
     */
    String updateUserAvatar(MultipartFile file);
}

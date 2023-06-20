package com.mxx910.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.model.VO.RouterVO;
import com.mxx910.blog.model.VO.UserBackInfoVO;
import com.mxx910.blog.model.VO.UserInfoVO;
import com.mxx910.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/4/27
 * @description:
 */
@Api(tags = "用户信息模块")
@RestController
public class UserInfoController {
    @Autowired
    UserService userService;
    @SaCheckLogin
    @ApiOperation("获取用户信息")
    @GetMapping("/user/getUserInfo")
    public Result<UserInfoVO> getUserInfo(){
        return Result.success(userService.getUserInfo());
    }


    /**
     * 获取后台登录用户信息
     *
     * @return {@link UserBackInfoVO} 登录用户信息
     */
    @ApiOperation(value = "获取后台登录用户信息")
    @GetMapping("/admin/user/getUserInfo")
    public Result<UserBackInfoVO> getUserBackInfo() {
        return Result.success(userService.getUserBackInfo());
    }

    /**
     * 获取登录用户菜单
     *
     * @return {@link RouterVO} 登录用户菜单
     */
    @ApiOperation(value = "获取登录用户菜单")
    @GetMapping("/admin/user/getUserMenu")
    public Result<List<RouterVO>> getUserMenu() {
        return Result.success(userService.getUserMenu());
    }

    /**
     * 修改用户头像
     *
     * @param file 文件
     * @return {@link Result<String>} 头像地址
     */
    @ApiOperation(value = "修改用户头像")
    @SaCheckPermission(value = "user:avatar:update")
    @PostMapping("/user/avatar")
    public Result<String> updateUserAvatar(@RequestParam(value = "file") MultipartFile file) {
        return Result.success(userService.updateUserAvatar(file));
    }
}

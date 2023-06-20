package com.mxx910.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.mxx910.blog.annotation.AccessLimit;
import com.mxx910.blog.model.DTO.LoginDTO;
import com.mxx910.blog.model.DTO.RegisterDTO;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: mxx910
 * @date: 2023/4/19
 * @description:
 */
@Api(tags = "登录模块")
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result<String> login(@Validated @RequestBody LoginDTO loginDTO){
        System.out.println(loginDTO);
        return Result.success(loginService.login(loginDTO));
    }
    @SaCheckLogin
    @ApiOperation("注销")
    @GetMapping("/logout")
    public Result<?> logout(){
        StpUtil.logout();
        return Result.success();
    }
    public int removeDuplicates(int[] nums) {
        int listIdx = 0;
        for(int i = 1; i < nums.length;i++){
            if(nums[listIdx] != nums[i]){
                listIdx ++;
                nums[listIdx] = nums[i];
            }else if(listIdx -1 >= 0 && nums[listIdx] == nums[i] && nums[listIdx - 1] == nums[i]){
                listIdx ++;
                nums[listIdx] = nums[i];
            }
        }
        return listIdx+1;
    }

    @AccessLimit(seconds = 60,maxCount = 1)
    @ApiOperation(value = "邮箱验证码")
    @GetMapping("/email/code")
    public Result<?> sendEmailCode(@RequestParam("username")String email){
        System.out.println("here"+email);
        loginService.sendCode(email);
        return Result.success();
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<?> register(@Validated @RequestBody RegisterDTO registerDTO){
        loginService.register(registerDTO);
        return Result.success();
    }


}

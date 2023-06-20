package com.mxx910.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import com.mxx910.blog.constant.CommonConstant;
import com.mxx910.blog.constant.ExceptionConstant;
import com.mxx910.blog.constant.MqConstant;
import com.mxx910.blog.constant.RedisConstant;
import com.mxx910.blog.entity.SiteConfig;
import com.mxx910.blog.entity.User;
import com.mxx910.blog.entity.UserRole;
import com.mxx910.blog.enums.LoginTypeEnum;
import com.mxx910.blog.enums.RoleEnum;

import com.mxx910.blog.mapper.UserMapper;
import com.mxx910.blog.mapper.UserRoleMapper;
import com.mxx910.blog.model.DTO.LoginDTO;
import com.mxx910.blog.model.DTO.MailDTO;
import com.mxx910.blog.model.DTO.RegisterDTO;
import com.mxx910.blog.service.LoginService;
import com.mxx910.blog.service.RedisService;
import com.mxx910.blog.service.RoleService;
import com.mxx910.blog.service.SiteConfigService;
import com.mxx910.blog.utils.CommonUtils;
import com.mxx910.blog.utils.SecurityUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: mxx910
 * @date: 2023/4/19
 * @description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SiteConfigService siteConfigService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisService redisService;


    @Override
    public String login(LoginDTO login) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getId)
                .eq(User::getUsername, login.getUsername())
                .or().eq(User::getEmail,login.getUsername())
                .eq(User::getPassword, SecurityUtils.sha256Encrypt(login.getPassword())));
        Assert.notNull(user,ExceptionConstant.USER_PASSWORD_ERROR);
        StpUtil.checkDisable(user.getId());
        StpUtil.login(user.getId());
        return StpUtil.getTokenValue();
    }

    @Override
    public void sendCode(String email) {

        Assert.isTrue(CommonUtils.checkEmail(email),ExceptionConstant.EMAIL_FORMAT_ERROR);
        Assert.isNull( userMapper.selectOne((new LambdaQueryWrapper<User>().select(User::getEmail)).eq(User::getEmail,email)),ExceptionConstant.REGISTER_EMAIL_EXISTS);
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
       String code = randomGenerator.generate();
        MailDTO mailDTO = MailDTO.builder()
                .toEmail(email)
                .subject(CommonConstant.CAPTCHA)
                .content("您的验证码为 " + code + " 有效期为" + RedisConstant.CODE_EXPIRE_TIME + "分钟")
                .build();
        rabbitTemplate.convertAndSend(MqConstant.EMAIL_EXCHANGE, MqConstant.EMAIL_SIMPLE_KEY, mailDTO);
        redisService.setObject(RedisConstant.CODE_KEY + email, code, RedisConstant.CODE_EXPIRE_TIME, TimeUnit.MINUTES);
    }

    @Override
    public void register(RegisterDTO register) {
        verifyCode(register.getEmail(), register.getCode());
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getUsername)
                .eq(User::getUsername,register.getUsername()).or().eq(User::getEmail,register.getEmail()));
        if (user != null){
            Assert.equals(user.getUsername(),register.getUsername(),ExceptionConstant.REGISTER_EMAIL_EXISTS);
            Assert.equals(user.getEmail(),register.getEmail(),ExceptionConstant.REGISTER_USER_EXISTS);
        }
        SiteConfig siteConfig = siteConfigService.getSiteConfig();
        User newUser = User.builder()
                .username(register.getUsername())
                .email(register.getEmail())
                .password(SecurityUtils.sha256Encrypt(register.getPassword()))
                .nickname(CommonConstant.USER_NICKNAME+ IdWorker.getId())
                .avatar(siteConfig.getUserAvatar())
                .loginType(LoginTypeEnum.EMAIL.getLoginType())
                .isDisable(CommonConstant.FALSE)
                .build();
        userMapper.insert(newUser);

        UserRole userRole = UserRole.builder()
                .userId(newUser.getId())
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);

    }

    /**
     * 校验验证码
     *
     * @param email    邮箱
     * @param code     验证码
     */
    public void verifyCode(String email, String code) {
        String sysCode = redisService.getObject(RedisConstant.CODE_KEY + email);
        Assert.notBlank(sysCode, ExceptionConstant.EMAIL_CODE_IS_NULL);
        Assert.isTrue(sysCode.equals(code), ExceptionConstant.EMAIL_CODE_NOT_MATCH);
    }
}

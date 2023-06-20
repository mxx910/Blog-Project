package com.mxx910.blog.service.impl;

import com.mxx910.blog.constant.RedisConstant;
import com.mxx910.blog.entity.SiteConfig;
import com.mxx910.blog.mapper.SiteConfigMapper;
import com.mxx910.blog.service.RedisService;
import com.mxx910.blog.service.SiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: mxx910
 * @date: 2023/4/20
 * @description:
 */
@Service
public class SiteConfigServiceImpl implements SiteConfigService {
    @Autowired
    private RedisService redisService;
    @Autowired
    SiteConfigMapper siteConfigMapper;
    @Override
    public SiteConfig getSiteConfig() {
        SiteConfig siteConfig =redisService.getObject(RedisConstant.SITE_SETTING);
        if (siteConfig == null){
            siteConfig = siteConfigMapper.selectById(1);
            redisService.setObject(RedisConstant.SITE_SETTING,siteConfig);
        }
        return siteConfig;

    }

    @Override
    public void updateSiteConfig(SiteConfig siteConfig) {
        siteConfigMapper.updateById(siteConfig);
        redisService.deleteObject(RedisConstant.SITE_SETTING);
    }

    @Override
    public String uploadSiteImg(MultipartFile file) {
        return null;
    }
}

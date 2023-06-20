package com.mxx910.blog.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxx910.blog.entity.BlogFile;
import com.mxx910.blog.mapper.BlogFileMapper;
import com.mxx910.blog.service.BlogFileService;
import com.mxx910.blog.strategy.context.UploadStrategyContext;
import com.mxx910.blog.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.mxx910.blog.constant.CommonConstant.FALSE;

/**
 * @author: mxx910
 * @date: 2023/6/14
 * @description:
 */
@Service
public class BlogFileServiceImpl extends ServiceImpl<BlogFileMapper, BlogFile> implements BlogFileService {
    @Autowired
    private BlogFileMapper blogFileMapper;
    @Autowired
    private UploadStrategyContext uploadStrategyContext;
    @Override
    public void uploadFile(MultipartFile file, String path) {
        try {
            String uploadPath = "/".equals(path) ? path : path + "/";
            // 上传文件
            String url = uploadStrategyContext.executeUploadStrategy(file, uploadPath);
            // 获取文件md5值
            String md5 = FileUtils.getMd5(file.getInputStream());
            // 获取文件扩展名
            String extName = FileUtils.getExtension(file);
            BlogFile existFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                    .select(BlogFile::getId)
                    .eq(BlogFile::getFileName, md5)
                    .eq(BlogFile::getFilePath, path));
            Assert.isNull(existFile, "文件已存在");
            // 保存文件信息
            BlogFile newFile = BlogFile.builder()
                    .fileUrl(url)
                    .fileName(md5)
                    .filePath(path)
                    .extendName(extName)
                    .fileSize((int) file.getSize())
                    .isDir(FALSE)
                    .build();
            blogFileMapper.insert(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

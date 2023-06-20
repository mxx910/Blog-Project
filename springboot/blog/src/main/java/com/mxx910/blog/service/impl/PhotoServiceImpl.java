package com.mxx910.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxx910.blog.entity.Album;
import com.mxx910.blog.entity.Photo;
import com.mxx910.blog.mapper.AlbumMapper;
import com.mxx910.blog.mapper.PhotoMapper;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.PhotoVO;
import com.mxx910.blog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: mxx910
 * @date: 2023/6/12
 * @description:
 */
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {
    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private AlbumMapper albumMapper;
    @Override
    public Map<String, Object> listPhotoVO(ConditionDTO condition) {
        Map<String, Object> result = new HashMap<>(2);
        String albumName = albumMapper.selectOne(new LambdaQueryWrapper<Album>()
                        .select(Album::getAlbumName).eq(Album::getId, condition.getAlbumId()))
                .getAlbumName();
        List<PhotoVO> photoVOList = photoMapper.selectPhotoVOList(condition.getAlbumId());
        result.put("albumName", albumName);
        result.put("photoVOList", photoVOList);
        return result;
    }
}

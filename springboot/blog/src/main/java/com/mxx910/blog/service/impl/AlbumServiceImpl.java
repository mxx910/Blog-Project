package com.mxx910.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxx910.blog.entity.Album;
import com.mxx910.blog.mapper.AlbumMapper;
import com.mxx910.blog.model.DTO.AlbumDTO;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.AlbumBackVO;
import com.mxx910.blog.model.VO.AlbumVO;
import com.mxx910.blog.model.VO.PageResult;
import com.mxx910.blog.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/12
 * @description:
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;
    @Override
    public PageResult<AlbumBackVO> listAlbumBackVO(ConditionDTO condition) {
        return null;
    }

    @Override
    public void addAlbum(AlbumDTO album) {

    }

    @Override
    public void deleteAlbum(Integer albumId) {

    }

    @Override
    public void updateAlbum(AlbumDTO album) {

    }

    @Override
    public AlbumDTO editAlbum(Integer albumId) {
        return null;
    }

    @Override
    public List<AlbumVO> listAlbumVO() {
        return albumMapper.selectAlbumVOList();
    }

    @Override
    public String uploadAlbumCover(MultipartFile file) {
        return null;
    }
}

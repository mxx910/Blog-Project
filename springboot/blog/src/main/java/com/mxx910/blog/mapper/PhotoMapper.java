package com.mxx910.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxx910.blog.entity.Photo;
import com.mxx910.blog.model.VO.PhotoBackVO;
import com.mxx910.blog.model.VO.PhotoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PhotoMapper extends BaseMapper<Photo> {
    /**
     * 查询后台照片列表
     *
     * @param limit   页码
     * @param size    大小
     * @param albumId 相册id
     * @return 后台照片列表
     */
    List<PhotoBackVO> selectPhotoBackVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("albumId") Integer albumId);

    /**
     * 查询照片列表
     *
     * @param albumId 相册id
     * @return 后台照片列表
     */
    List<PhotoVO> selectPhotoVOList(@Param("albumId") Integer albumId);
}

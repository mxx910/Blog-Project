package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.Album;
import com.mxx910.blog.model.DTO.AlbumDTO;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.AlbumBackVO;
import com.mxx910.blog.model.VO.AlbumVO;
import com.mxx910.blog.model.VO.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AlbumService extends IService<Album> {

    /**
     * 查看后台相册列表
     *
     * @param condition 条件
     * @return 后台相册列表
     */
    PageResult<AlbumBackVO> listAlbumBackVO(ConditionDTO condition);

    /**
     * 添加相册
     *
     * @param album 相册
     */
    void addAlbum(AlbumDTO album);

    /**
     * 删除相册
     *
     * @param albumId 相册id
     */
    void deleteAlbum(Integer albumId);

    /**
     * 修改相册
     *
     * @param album 相册
     */
    void updateAlbum(AlbumDTO album);

    /**
     * 编辑相册
     *
     * @param albumId 相册id
     * @return 相册信息
     */
    AlbumDTO editAlbum(Integer albumId);

    /**
     * 查看相册列表
     *
     * @return 相册列表
     */
    List<AlbumVO> listAlbumVO();

    /**
     * 上传相册封面
     *
     * @param file 文件
     * @return 相册封面地址
     */
    String uploadAlbumCover(MultipartFile file);
}

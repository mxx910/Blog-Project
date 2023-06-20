package com.mxx910.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxx910.blog.entity.BlogFile;
import com.mxx910.blog.model.VO.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mxx910
 */
@Mapper
public interface BlogFileMapper extends BaseMapper<BlogFile> {
    /**
     * 查询后台文件列表
     *
     * @param limit    页码
     * @param size     大小
     * @param filePath 文件路径
     * @return 后台文件列表
     */
    List<FileVO> selectFileVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("filePath") String filePath);

}

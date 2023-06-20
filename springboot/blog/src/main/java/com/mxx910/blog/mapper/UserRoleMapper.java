package com.mxx910.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxx910.blog.entity.UserRole;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.RoleVO;
import com.mxx910.blog.model.VO.UserRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/4/19
 * @description:
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 根据用户id查询用户角色
     *
     * @param userId 用户id
     * @return 角色
     */
    List<String> selectRoleListByUserId(@Param("userId") Object userId);

    /**
     * 查询后台角色数量
     *
     * @param condition 查询条件
     * @return 后台角色数量
     */
    Long selectCountRoleVO(@Param("condition") ConditionDTO condition);

    /**
     * 查询后台角色列表
     *
     * @param limit     页码
     * @param size      大小
     * @param condition 查询条件
     * @return 后台角色列表
     */
    List<RoleVO> selectRoleVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);

    /**
     * 查询用户角色选项
     *
     * @return 用户角色选项
     */
    List<UserRoleVO> selectUserRoleList();
}

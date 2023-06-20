package com.mxx910.blog.service;

import cn.hutool.db.PageResult;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.RoleDTO;
import com.mxx910.blog.model.DTO.RoleStatusDTO;
import com.mxx910.blog.model.VO.RoleVO;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/4/20
 * @description:
 */
public interface RoleService {

    /**
     * 查看角色列表
     *
     * @param condition 查询条件
     * @return 角色列表
     */
    PageResult<RoleVO> listRoleVO(ConditionDTO condition);

    /**
     * 添加角色
     *
     * @param role 角色信息
     */
    void addRole(RoleDTO role);

    /**
     * 删除角色
     *
     * @param roleIdList 角色id集合
     */
    void deleteRole(List<String> roleIdList);

    /**
     * 修改角色
     *
     * @param role 角色信息
     */
    void updateRole(RoleDTO role);

    /**
     * 修改角色状态
     *
     * @param roleStatus 角色状态
     */
    void updateRoleStatus(RoleStatusDTO roleStatus);

    /**
     * 查看角色的菜单权限
     *
     * @param roleId 角色id
     * @return 角色的菜单权限
     */
    List<Integer> listRoleMenuTree(String roleId);
}

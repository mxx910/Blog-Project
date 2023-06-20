package com.mxx910.blog.service.impl;

import cn.hutool.db.PageResult;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.RoleDTO;
import com.mxx910.blog.model.DTO.RoleStatusDTO;
import com.mxx910.blog.model.VO.RoleVO;
import com.mxx910.blog.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/4/20
 * @description:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public PageResult<RoleVO> listRoleVO(ConditionDTO condition) {
        return null;
    }

    @Override
    public void addRole(RoleDTO role) {

    }

    @Override
    public void deleteRole(List<String> roleIdList) {

    }

    @Override
    public void updateRole(RoleDTO role) {

    }

    @Override
    public void updateRoleStatus(RoleStatusDTO roleStatus) {

    }

    @Override
    public List<Integer> listRoleMenuTree(String roleId) {
        return null;
    }
}

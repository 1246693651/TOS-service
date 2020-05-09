package com.hnist.tos.service.impl;

import com.hnist.tos.dao.RoleDao;
import com.hnist.tos.entity.Role;
import com.hnist.tos.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pany
 * @date 2020-05-01 14:44
 * @content
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 创建角色
     * @param role
     */
    @Override
    public void add(Role role) {
        roleDao.save(role);
    }

    /**
     * 删除角色
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }

    /**
     * 更新角色
     * @param role
     */
    @Override
    public void update(Role role) {
        Role mid = roleDao.findById(role.getId()).get();
        mid.setName(role.getName());
        mid.setDescription(role.getDescription());
        roleDao.save(mid);
    }

    /**
     * 查找角色
     * @param id
     * @return
     */
    @Override
    public Role findById(Long id) {
        return roleDao.findById(id).get();
    }
}

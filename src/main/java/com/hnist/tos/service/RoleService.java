package com.hnist.tos.service;

import com.hnist.tos.entity.Role;

/**
 * @author Pany
 * @date 2020-05-01 14:44
 * @content
 */
public interface RoleService {
    void add(Role role);

    void deleteById(Long id);

    void update(Role role);

    Role findById(Long id);
}

package com.hnist.tos.dao;

import com.hnist.tos.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pany
 * @date 2020-05-01 14:43
 * @content
 */
public interface RoleDao extends JpaRepository<Role,Long> {
}

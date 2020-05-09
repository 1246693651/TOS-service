package com.hnist.tos.dao;

import com.hnist.tos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    /**
     * 根据OpenId查询用户信息
     */
    User findByOpenId(String openId);
}

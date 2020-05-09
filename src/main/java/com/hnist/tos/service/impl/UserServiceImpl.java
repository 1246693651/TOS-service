package com.hnist.tos.service.impl;


import com.hnist.tos.dao.UserDao;
import com.hnist.tos.entity.User;
import com.hnist.tos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Pany
 * @date 2020-04-21 19:59
 * @content
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    /**
     * 创建用户
     * @param user
     */
    @Override
    public void add(User user) {
        userDao.save(user);
    }

    /**
     * 删除用户 （理论上不直接提供）
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    /**
     * 更新用户
     * @param user
     */
    @Override
    public void update(User user) {
        User mid= userDao.findById(user.getId()).get();
        mid.setAvatar(user.getAvatar());
        mid.setNickname(user.getNickname());
        userDao.save(mid);
      }

      /**
     * 查找用户
     * @param id
     * @return 用户对象
     */
    @Override
    public User findById(Long id) {
        return userDao.findById(id).get();
    }

    /**
     * 通过openId查找用户
     * @param openId
     * @return
     */
    @Override
    public User findByOpenId(String openId) {
        return userDao.findByOpenId(openId);
    }

}

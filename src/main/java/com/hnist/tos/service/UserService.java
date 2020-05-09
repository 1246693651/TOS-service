package com.hnist.tos.service;

import com.hnist.tos.entity.User;

import java.util.Map;

public interface UserService {
    void add(User user);

    void deleteById(Long id);

    void update(User user);

    User findById(Long id);

    User findByOpenId(String openId);
}

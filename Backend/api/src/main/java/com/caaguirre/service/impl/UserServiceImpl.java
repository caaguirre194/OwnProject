package com.caaguirre.service.impl;

import com.caaguirre.common.GenericServiceImpl;
import com.caaguirre.model.User;
import com.caaguirre.repository.IUserRepository;
import com.caaguirre.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public CrudRepository<User, Long> getDao() {
        return userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
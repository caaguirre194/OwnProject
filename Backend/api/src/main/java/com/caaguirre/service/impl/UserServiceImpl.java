package com.caaguirre.service.impl;

import com.caaguirre.commons.GenericServiceImpl;
import com.caaguirre.repository.IUserDAO;
import com.caaguirre.model.User;
import com.caaguirre.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public CrudRepository<User, Long> getDao() {
        return userDAO;
    }

}
package com.caaguirre.service;

import com.caaguirre.common.IGenericService;
import com.caaguirre.model.User;

public interface IUserService extends IGenericService<User, Long>{
     User findByUsername(String username);
     User findByEmail(String email);
}

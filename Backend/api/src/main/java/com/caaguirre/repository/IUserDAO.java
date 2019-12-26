package com.caaguirre.repository;

import com.caaguirre.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDAO extends CrudRepository <User,Long>{
}

package com.caaguirre.repository;

import com.caaguirre.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User,Long> {

    @Query("SELECT t FROM User t WHERE t.username = ?1")
    User findByUsername(String username);

    @Query("SELECT t FROM User t WHERE t.email = ?1")
    User findByEmail(String email);

}

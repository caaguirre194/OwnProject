package com.caaguirre.repository;

import com.caaguirre.model.Rol;
import com.caaguirre.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends CrudRepository<Rol,Long> {
}

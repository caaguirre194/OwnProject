package com.caaguirre.repository;

import com.caaguirre.model.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends CrudRepository<Rol,Long> {
}

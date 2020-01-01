package com.caaguirre.repository;

import com.caaguirre.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends CrudRepository<Status,Long> {
}

package com.caaguirre.repository;

import com.caaguirre.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends CrudRepository<Person,Long> {
}

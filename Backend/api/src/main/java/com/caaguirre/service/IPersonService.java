package com.caaguirre.service;

import com.caaguirre.commons.IGenericService;
import com.caaguirre.model.Person;
import org.springframework.stereotype.Service;

public interface IPersonService extends IGenericService<Person, Long>{
    Person save(Person person);
}

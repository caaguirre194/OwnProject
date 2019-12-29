package com.caaguirre.service.impl;

import com.caaguirre.common.GenericServiceImpl;
import com.caaguirre.model.Person;
import com.caaguirre.repository.IPersonRepository;
import com.caaguirre.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, Long> implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public CrudRepository<Person, Long> getDao() {
        return personRepository;
    }

}

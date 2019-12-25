package com.caaguirre.service.impl;

import com.caaguirre.commons.GenericServiceImpl;
import com.caaguirre.dao.PersonaDAO;
import com.caaguirre.model.Persona;
import com.caaguirre.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Long> implements PersonaService {

    @Autowired
    private PersonaDAO personaDao;

    @Override
    public CrudRepository<Persona, Long> getDao() {
        return personaDao;
    }

}
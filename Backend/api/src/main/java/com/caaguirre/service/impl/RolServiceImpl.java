package com.caaguirre.service.impl;

import com.caaguirre.commons.GenericServiceImpl;
import com.caaguirre.model.Rol;
import com.caaguirre.model.User;
import com.caaguirre.repository.IRolRepository;
import com.caaguirre.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends GenericServiceImpl<Rol, Long> implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public CrudRepository<Rol, Long> getDao() {
        return rolRepository;
    }

    @Override
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

}

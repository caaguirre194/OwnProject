package com.caaguirre.service;

import com.caaguirre.commons.IGenericService;
import com.caaguirre.model.Rol;
import org.springframework.stereotype.Service;

public interface IRolService extends IGenericService<Rol, Long>{
    Rol save(Rol rol);
}

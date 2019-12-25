package com.caaguirre.dao;

import com.caaguirre.model.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaDAO extends CrudRepository <Persona,Long>{
}

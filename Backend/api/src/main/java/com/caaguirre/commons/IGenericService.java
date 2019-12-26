package com.caaguirre.commons;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<T, ID extends Serializable>{

    T save(T entity);

    void delete(ID id);

    T get(ID id);

    List<T> getAll();

}

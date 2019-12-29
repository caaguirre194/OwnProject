package com.caaguirre.service.impl;

import com.caaguirre.common.GenericServiceImpl;
import com.caaguirre.model.Status;
import com.caaguirre.repository.IStatusRepository;
import com.caaguirre.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl extends GenericServiceImpl<Status, Long> implements IStatusService  {

    @Autowired
    private IStatusRepository statusRepository;

    @Override
    public CrudRepository<Status, Long> getDao() {
        return statusRepository;
    }

}

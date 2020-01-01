package com.caaguirre.service.impl;

import com.caaguirre.service.IMessageManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.IOException;

@Configuration
@PropertySource("classpath:messages.properties")
public class MessageManagerServiceImpl implements IMessageManagerService {

    @Autowired
    private Environment env;


    public MessageManagerServiceImpl() throws IOException {}

    @Override
    public String getValue(String key) {
        String resultado = env.getProperty(key);
        return resultado;
    }

    @Override
    public String getValue(String key, String defaultValue) {
        String value = env.getProperty(key);
        return value != null ? value : defaultValue;

    }

}

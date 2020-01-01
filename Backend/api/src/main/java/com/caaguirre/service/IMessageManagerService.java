package com.caaguirre.service;

public interface IMessageManagerService {
    String getValue(String key);
    String getValue(String key, String defaultValue);
}

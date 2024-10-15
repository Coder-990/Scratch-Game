package com.java.home.assignment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.home.assignment.service.ConfigReaderService;
import com.java.home.assignment.model.Game;

import java.io.File;
import java.io.IOException;

public class ConfigReaderImpl implements ConfigReaderService {

    @Override
    public Game loadConfig(String filePath) {
        try {
            return new ObjectMapper().readValue(new File(filePath), Game.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config file");
        }
    }
}

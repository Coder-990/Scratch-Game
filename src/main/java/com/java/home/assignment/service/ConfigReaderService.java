package com.java.home.assignment.service;

import com.java.home.assignment.model.Game;

public interface ConfigReaderService {
    Game loadConfig(String filePath);
}

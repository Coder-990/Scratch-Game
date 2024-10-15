package com.java.home.assignment;

import com.java.home.assignment.service.*;
import com.java.home.assignment.service.impl.*;

public class Main {

    public static void main(String[] args) {

        GameEngineService gameEngine = new GameEngineServiceImpl();
        gameEngine.runGame(args);
    }
}

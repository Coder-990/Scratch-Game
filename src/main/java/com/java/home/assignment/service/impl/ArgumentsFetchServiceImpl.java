package com.java.home.assignment.service.impl;

import com.java.home.assignment.service.ArgumentsFetchService;

import java.math.BigDecimal;

public class ArgumentsFetchServiceImpl implements ArgumentsFetchService {

    @Override
    public String getConfigPath(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].contains("--config") && i + 1 < args.length) {
                return args[i + 1];
            }
        }
        System.out.println("Error: Config path is missing.");
        return null;
    }

    @Override
    public BigDecimal getBettingAmount(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--betting-amount") && i + 1 < args.length) {
                try {
                    return new BigDecimal(args[i + 1]);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Betting amount must be a valid number.");
                    return null;
                }
            }
        }
        System.out.println("Error: Betting amount is missing.");
        return null;
    }
}

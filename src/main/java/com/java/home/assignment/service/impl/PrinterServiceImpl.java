package com.java.home.assignment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.home.assignment.service.PrinterService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrinterServiceImpl implements PrinterService {

    @Override
    public void printMatrix(String[][] matrix, Map<String, List<String>> appliedWinningCombinations, BigDecimal reward, String appliedBonusSymbol) {
        System.out.println("Matrix:");
        for (String[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        Map<String, Object> output = new HashMap<>();
        output.put("reward", reward);
        output.put("applied_winning_combinations", appliedWinningCombinations);
        output.put("applied_bonus_symbol", appliedBonusSymbol);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(output);
            System.out.println(jsonOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BigDecimal errorAndExit() {
        System.out.println("Error: Missing arguments. Usage: --config <config-file> --betting-amount <amount>");
        System.exit(1);
        return null;
    }
}

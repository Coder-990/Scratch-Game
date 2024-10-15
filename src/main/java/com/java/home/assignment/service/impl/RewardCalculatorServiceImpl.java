package com.java.home.assignment.service.impl;

import com.java.home.assignment.model.Symbol;
import com.java.home.assignment.model.WinCombination;
import com.java.home.assignment.service.RewardCalculatorService;

import java.math.BigDecimal;
import java.util.*;

public class RewardCalculatorServiceImpl implements RewardCalculatorService {

    @Override
    public BigDecimal calculateBaseReward(String[][] matrix, BigDecimal betAmount, Map<String, WinCombination> winCombinations,
                                          Map<String, List<String>> appliedWinningCombinations, Map<String, Symbol> symbols) {
        BigDecimal totalReward = BigDecimal.ZERO;
        Map<String, Integer> symbolCounts = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                String symbol = matrix[i][j];
                if (symbol != null && !symbol.equals("MISS")) {
                    symbolCounts.put(symbol, symbolCounts.getOrDefault(symbol, 0) + 1);
                }
            }
        }

        for (String symbol : symbolCounts.keySet()) {
            if (symbols.containsKey(symbol)) {
                Symbol symbolData = symbols.get(symbol);
                BigDecimal symbolRewardMultiplier = symbolData.getRewardMultiplier();
                int count = symbolCounts.get(symbol);

                if (symbolRewardMultiplier != null && symbolData.getType().equals("standard")) {
                    for (WinCombination winCombo : winCombinations.values()) {
                        if (winCombo.getCount() <= count) {
                            BigDecimal winMultiplier = winCombo.getRewardMultiplier();

                            if (winMultiplier != null) {
                                totalReward = totalReward.add(betAmount.multiply(symbolRewardMultiplier).multiply(winMultiplier));
                            }

                            String direction = determineDirection(matrix, symbol);
                            applyWinCombination(symbol, count, direction, appliedWinningCombinations, winCombinations);
                        }
                    }
                }
            }
        }

        if (appliedWinningCombinations.isEmpty()) {
            totalReward = BigDecimal.ZERO;
        }
        return totalReward;
    }

    @Override
    public void applyWinCombination(String symbol, int count, String direction, Map<String, List<String>> appliedWinningCombinations, Map<String, WinCombination> winCombinations) {
        String winComboKey = "same_symbol_" + count + "_times";
        WinCombination winCombo = winCombinations.get(winComboKey);

        if (winCombo == null || count < 3) {
            return;
        }

        appliedWinningCombinations.putIfAbsent(symbol, new ArrayList<>());

        if (!appliedWinningCombinations.get(symbol).contains(winComboKey)) {
            appliedWinningCombinations.get(symbol).add(winComboKey);
        }

        switch (direction) {
            case "horizontally" -> {
                if (!appliedWinningCombinations.get(symbol).contains("same_symbols_horizontally")) {
                    appliedWinningCombinations.get(symbol).add("same_symbols_horizontally");
                }
            }
            case "vertically" -> {
                if (!appliedWinningCombinations.get(symbol).contains("same_symbols_vertically")) {
                    appliedWinningCombinations.get(symbol).add("same_symbols_vertically");
                }
            }
            case "diagonal_ltr" -> {
                if (!appliedWinningCombinations.get(symbol).contains("same_symbols_diagonally_left_to_right")) {
                    appliedWinningCombinations.get(symbol).add("same_symbols_diagonally_left_to_right");
                }
            }
            case "diagonal_rtl" -> {
                if (!appliedWinningCombinations.get(symbol).contains("same_symbols_diagonally_right_to_left")) {
                    appliedWinningCombinations.get(symbol).add("same_symbols_diagonally_right_to_left");
                }
            }
        }
    }

    @Override
    public void checkWinningCombinations(String[][] matrix, Map<String, List<String>> appliedWinningCombinations, Map<String, WinCombination> winCombinations) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            String firstSymbol = matrix[i][0];
            int count = 1;

            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] != null && matrix[i][j].equals(firstSymbol)) {
                    count++;
                }
            }

            if (count >= 3 && firstSymbol != null) {
                applyWinCombination(firstSymbol, count, "horizontally", appliedWinningCombinations, winCombinations);
            }
        }

        for (int j = 0; j < cols; j++) {
            String firstSymbol = matrix[0][j];
            int count = 1;

            for (int i = 1; i < rows; i++) {
                if (matrix[i][j] != null && matrix[i][j].equals(firstSymbol)) {
                    count++;
                }
            }

            if (count >= 3 && firstSymbol != null) {
                applyWinCombination(firstSymbol, count, "vertically", appliedWinningCombinations, winCombinations);
            }
        }

        String firstSymbol = matrix[0][0];
        if (firstSymbol != null && firstSymbol.equals(matrix[1][1]) && firstSymbol.equals(matrix[2][2])) {
            applyWinCombination(firstSymbol, 3, "diagonal_ltr", appliedWinningCombinations, winCombinations);
        }

        firstSymbol = matrix[0][2];
        if (firstSymbol != null && firstSymbol.equals(matrix[1][1]) && firstSymbol.equals(matrix[2][0])) {
            applyWinCombination(firstSymbol, 3, "diagonal_rtl", appliedWinningCombinations, winCombinations);
        }

        checkCoveredAreas(matrix, appliedWinningCombinations, winCombinations);
    }

    private String determineDirection(String[][] matrix, String symbol) {
        boolean horizontal = false;
        boolean vertical = false;
        boolean diagonalLTR = false;
        boolean diagonalRTL = false;

        for (int i = 0; i < matrix.length; i++) {
            if (Arrays.stream(matrix[i]).allMatch(s -> s != null && s.equals(symbol))) {
                horizontal = true;
                break;
            }
        }

        for (int j = 0; j < matrix[0].length; j++) {
            boolean allSame = true;
            for (int i = 0; i < matrix.length; i++) {
                if (!symbol.equals(matrix[i][j])) {
                    allSame = false;
                    break;
                }
            }
            if (allSame) {
                vertical = true;
                break;
            }
        }

        diagonalLTR = symbol.equals(matrix[0][0]) && symbol.equals(matrix[1][1]) && symbol.equals(matrix[2][2]);

        diagonalRTL = symbol.equals(matrix[0][2]) && symbol.equals(matrix[1][1]) && symbol.equals(matrix[2][0]);

        if (horizontal) return "horizontally";
        if (vertical) return "vertically";
        if (diagonalLTR) return "diagonal_ltr";
        if (diagonalRTL) return "diagonal_rtl";

        return "none";
    }



    private void checkCoveredAreas(String[][] matrix, Map<String, List<String>> appliedWinningCombinations, Map<String, WinCombination> winCombinations) {
        for (Map.Entry<String, WinCombination> entry : winCombinations.entrySet()) {
            WinCombination winCombo = entry.getValue();
            String[][] coveredAreas = winCombo.getCoveredAreas();
            String group = winCombo.getGroup();

            if (coveredAreas != null) {
                for (String[] area : coveredAreas) {
                    String firstSymbol = null;
                    boolean isWinningArea = true;

                    for (String position : area) {
                        String[] coordinates = position.split(":");
                        int row = Integer.parseInt(coordinates[0]);
                        int col = Integer.parseInt(coordinates[1]);

                        if (matrix[row][col] == null) {
                            isWinningArea = false;
                            break;
                        }

                        if (firstSymbol == null) {
                            firstSymbol = matrix[row][col];
                        } else if (!firstSymbol.equals(matrix[row][col])) {
                            isWinningArea = false;
                            break;
                        }
                    }

                    if (isWinningArea && firstSymbol != null) {
                        applyWinCombination(firstSymbol, area.length, group, appliedWinningCombinations, winCombinations);
                    }
                }
            }
        }
    }
}

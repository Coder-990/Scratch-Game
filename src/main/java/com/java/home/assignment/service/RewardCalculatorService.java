package com.java.home.assignment.service;

import com.java.home.assignment.model.Symbol;
import com.java.home.assignment.model.WinCombination;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RewardCalculatorService {

    BigDecimal calculateBaseReward(String[][] matrix, BigDecimal betAmount, Map<String, WinCombination> winCombinations,
                                   Map<String, List<String>> appliedWinningCombinations, Map<String, Symbol> symbols);

    void applyWinCombination(String symbol, int count, String direction, Map<String, List<String>> appliedWinningCombinations, Map<String, WinCombination> winCombinations);

    void checkWinningCombinations(String[][] matrix, Map<String, List<String>> appliedWinningCombinations, Map<String, WinCombination> winCombinations);
}

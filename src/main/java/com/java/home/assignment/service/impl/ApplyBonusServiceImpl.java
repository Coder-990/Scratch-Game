package com.java.home.assignment.service.impl;

import com.java.home.assignment.model.Symbol;
import com.java.home.assignment.service.ApplyBonusService;

import java.math.BigDecimal;
import java.util.Map;

public class ApplyBonusServiceImpl implements ApplyBonusService {

    @Override
    public String applyBonus(String[][] matrix, BigDecimal totalReward, Map<String, Symbol> symbols) {
        String appliedBonusSymbol = null;

        if (totalReward.compareTo(BigDecimal.ZERO) > 0) {
            for (String[] row : matrix) {
                for (String symbol : row) {
                    if (symbols.containsKey(symbol)) {
                        Symbol symbolData = symbols.get(symbol);

                        if (symbolData.getType().equals("bonus")) {
                            if (symbol.equals("MISS")) continue; // Skip MISS in favor of other bonuses

                            switch (symbol) {
                                case "10x" -> {
                                    totalReward = totalReward.multiply(BigDecimal.TEN);
                                    appliedBonusSymbol = "10x";
                                }
                                case "5x" -> {
                                    totalReward = totalReward.multiply(BigDecimal.valueOf(5));
                                    appliedBonusSymbol = "5x";
                                }
                                case "+1000" -> {
                                    totalReward = totalReward.add(BigDecimal.valueOf(1000));
                                    appliedBonusSymbol = "+1000";
                                }
                                case "+500" -> {
                                    totalReward = totalReward.add(BigDecimal.valueOf(500));
                                    appliedBonusSymbol = "+500";
                                }
                            }
                        }
                    }
                }
            }
        }
        if (appliedBonusSymbol == null && totalReward.compareTo(BigDecimal.ZERO) > 0) {
            appliedBonusSymbol = "MISS";
        }
        return appliedBonusSymbol;
    }

}

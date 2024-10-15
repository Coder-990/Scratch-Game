package com.java.home.assignment.service;

import com.java.home.assignment.model.Symbol;

import java.math.BigDecimal;
import java.util.Map;

public interface ApplyBonusService {
    String applyBonus(String[][] matrix, BigDecimal totalReward, Map<String, Symbol> symbols);
}

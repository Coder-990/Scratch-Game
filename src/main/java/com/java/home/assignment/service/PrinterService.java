package com.java.home.assignment.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PrinterService {
    void printMatrix(String[][] matrix, Map<String, List<String>> appliedWinningCombinations, BigDecimal reward, String appliedBonusSymbol);

    BigDecimal errorAndExit();
}

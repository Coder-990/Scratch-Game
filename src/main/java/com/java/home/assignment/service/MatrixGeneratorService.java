package com.java.home.assignment.service;

import com.java.home.assignment.model.Probability;

import java.util.List;
import java.util.Map;

public interface MatrixGeneratorService {
    String[][] generateMatrix(List<Probability> probabilities, int columns, int rows, Map<String, Integer> bonusSymbols);
}

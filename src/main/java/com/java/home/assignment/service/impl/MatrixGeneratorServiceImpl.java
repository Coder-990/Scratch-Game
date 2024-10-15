package com.java.home.assignment.service.impl;

import com.java.home.assignment.model.Probability;
import com.java.home.assignment.service.MatrixGeneratorService;

import java.util.*;

public class MatrixGeneratorServiceImpl implements MatrixGeneratorService {

    @Override
    public String[][] generateMatrix(List<Probability> probabilities, int columns, int rows, Map<String, Integer> bonusSymbols) {
        String[][] matrix = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Probability probability = getProbabilityForRowAndColumn(probabilities, i, j);
                if (probability != null) {
                    matrix[i][j] = pickSymbol(probability.getSymbols(), bonusSymbols);
                } else {
                    matrix[i][j] = "N/A";
                }
            }
        }
        addBonusSymbols(matrix, bonusSymbols, addAvailablePositions(matrix));
        return matrix;
    }

    private void addBonusSymbols(String[][] matrix, Map<String, Integer> bonusSymbols, List<int[]> availablePositions) {
        for (Map.Entry<String, Integer> entry : bonusSymbols.entrySet()) {
            String bonusSymbol = entry.getKey();
            int countToAdd = entry.getValue();

            for (int i = 0; i < countToAdd && !availablePositions.isEmpty(); i++) {
                int randomIndex = new Random().nextInt(availablePositions.size());
                int[] position = availablePositions.remove(randomIndex);
                int randomRow = position[0];
                int randomCol = position[1];

                matrix[randomRow][randomCol] = bonusSymbol;
            }
        }
    }

    private List<int[]> addAvailablePositions(String[][] matrix) {
        List<int[]> availablePositions = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == null) {
                    availablePositions.add(new int[]{row, col});
                }
            }
        }
        return availablePositions;
    }

    private Probability getProbabilityForRowAndColumn(List<Probability> probabilities, int row, int column) {
        for (Probability probability : probabilities) {
            if (probability.getRow() == row && probability.getColumn() == column) {
                return probability;
            }
        }
        return null;
    }

    private String pickSymbol(Map<String, Integer> symbolWeights, Map<String, Integer> bonusSymbols) {
        Map<String, Integer> combinedSymbolWeights = new HashMap<>(symbolWeights);
        combinedSymbolWeights.putAll(bonusSymbols);

        if (combinedSymbolWeights.isEmpty()) {
            return "N/A";
        }

        int totalWeight = combinedSymbolWeights.values().stream().mapToInt(Integer::intValue).sum();
        int randomValue = new Random().nextInt(totalWeight);
        int currentSum = 0;

        for (Map.Entry<String, Integer> entry : combinedSymbolWeights.entrySet()) {
            currentSum += entry.getValue();
            if (randomValue < currentSum) {
                return entry.getKey();
            }
        }
        return "N/A";
    }


}

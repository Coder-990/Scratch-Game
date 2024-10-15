package com.java.home.assignment.service.impl;

import com.java.home.assignment.model.Game;
import com.java.home.assignment.service.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEngineServiceImpl implements GameEngineService {

    private final ConfigReaderService configReaderService = new ConfigReaderImpl();
    private final MatrixGeneratorService matrixGeneratorService = new MatrixGeneratorServiceImpl();
    private final RewardCalculatorService rewardCalculatorService = new RewardCalculatorServiceImpl();
    private final ApplyBonusService applyBonusService = new ApplyBonusServiceImpl();
    private final PrinterService printerService = new PrinterServiceImpl();
    private final ArgumentsFetchService argumentsFetchService = new ArgumentsFetchServiceImpl();

    @Override
    public void runGame(String[] args) {
        String configPath = (args.length < 4 || (configPath = argumentsFetchService.getConfigPath(args)) == null) ?
                String.valueOf(printerService.errorAndExit()) : configPath;
        BigDecimal betAmount = (args.length < 4 || (betAmount = argumentsFetchService.getBettingAmount(args)) == null) ?
                printerService.errorAndExit() : betAmount;

        Game game = configReaderService.loadConfig(configPath);
        game.setBetAmount(betAmount);

        String[][] matrix = matrixGeneratorService.generateMatrix(
                game.getProbabilities().getStandardSymbols(),
                game.getColumns(),
                game.getRows(),
                game.getProbabilities().getBonusSymbols().getSymbols());

        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();
        BigDecimal totalReward = rewardCalculatorService.calculateBaseReward(
                matrix, betAmount, game.getWinCombinations(), appliedWinningCombinations, game.getSymbols());

        String appliedBonusSymbol = applyBonusOrNotify(matrix, totalReward, appliedWinningCombinations, game);

        rewardCalculatorService.checkWinningCombinations(matrix, appliedWinningCombinations, game.getWinCombinations());
        printerService.printMatrix(matrix, appliedWinningCombinations, totalReward, appliedBonusSymbol);
    }

    private String applyBonusOrNotify(String[][] matrix, BigDecimal totalReward, Map<String, List<String>> appliedWinningCombinations, Game game) {
        if (appliedWinningCombinations.isEmpty()) {
            System.out.println("The game is settled as LOST, so bonus symbol has not been applied because there are no winning combinations.");
            return "Game was LOST";
        } else {
            String appliedBonusSymbol = applyBonusService.applyBonus(matrix, totalReward, game.getSymbols());
            System.out.println("The game was settled as WON! Total reward: " + totalReward);
            return appliedBonusSymbol;
        }
    }


}

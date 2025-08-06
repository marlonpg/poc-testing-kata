package com.gamba.software.testingapp.services;

import com.gamba.software.testingapp.entities.GameResult;
import com.gamba.software.testingapp.repositories.GameResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DungeonGameService {

    private final GameResultRepository gameResultRepository;

    @Autowired
    public DungeonGameService(GameResultRepository gameResultRepository) {
        this.gameResultRepository = gameResultRepository;
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int rows = dungeon.length;
        int cols = dungeon[0].length;

        // Create a DP table with extra row and column
        int[][] dp = new int[rows + 1][cols + 1];

        // Initialize all cells to Integer.MAX_VALUE
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[rows][cols - 1] = 1;
        dp[rows - 1][cols] = 1;

        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                int minHealthOnExit = Math.min(dp[r + 1][c], dp[r][c + 1]);
                dp[r][c] = Math.max(1, minHealthOnExit - dungeon[r][c]);
            }
        }

        int result = dp[0][0];

        // Save result to database (example values for player and outcome)
        GameResult gameResult = new GameResult();
        gameResult.setPlayer("player1");
        gameResult.setScore(result);
        gameResult.setOutcome("COMPLETED");
        gameResultRepository.save(gameResult);

        return result;
    }
}

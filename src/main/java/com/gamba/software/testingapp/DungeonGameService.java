package com.gamba.software.testingapp;

public class DungeonGameService {
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

        return dp[0][0];
    }
}

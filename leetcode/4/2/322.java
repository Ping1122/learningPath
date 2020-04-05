class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount+1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
            row[0] = 0;
        }
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = 1; j < amount+1; j++) {
                int cell = Integer.MAX_VALUE;
                if (i > 0 && dp[i-1][j] != -1) 
                    cell = Math.min(cell, dp[i-1][j]);
                if (j >= coin && dp[i][j-coin] != -1) 
                    cell = Math.min(cell, dp[i][j-coin]+1);
                if (cell == Integer.MAX_VALUE) cell = -1;
                dp[i][j] = cell;
            }
        }
        return dp[coins.length-1][amount];
    }
}
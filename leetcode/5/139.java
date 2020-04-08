class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<String>(wordDict);
        boolean[] dp = new boolean[s.length()];
        Arrays.fill(dp, false);
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < i+1; j++) {
                if ((j == 0 || dp[j-1]) && words.contains(s.substring(j, i+1))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()-1];
    }
}
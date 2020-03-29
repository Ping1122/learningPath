class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int result = 0;
        for (int price : prices) {
            if (price < minPrice) 
                minPrice = price;
            else 
                result = Math.max(price-minPrice, result);
        }
        return result;
    }
}
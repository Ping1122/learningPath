class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> memory = new HashMap<>();
        memory.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;
            if (memory.containsKey(sum-k)) 
                count += memory.get(sum-k);
            memory.putIfAbsent(sum, 0);
            memory.put(sum, memory.get(sum)+1);
        }
        return count;
    }
}
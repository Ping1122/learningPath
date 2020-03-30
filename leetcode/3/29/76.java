class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> target = getTargetFrequency(t);
        Queue<Integer> pq = new LinkedList<>();
        Map<Character, Integer> current = new HashMap<>();
        int left = getLeft(target, s);
        int right = findWindowStartWithZero(target, current, s, pq);
        if (right == s.length()) return "";
        int minLeft = left;
        int minRight = right;
        int minRange = right - left;
        char d = s.charAt(pq.peek());
            while (current.get(d) > target.get(d)) {
                pq.poll();
                left = pq.peek();
                if (right-left < minRange) {
                    minRight = right;
                    minLeft = left;
                    minRange = right - left;
                }
                current.put(d, current.get(d)-1);
                d = s.charAt(pq.peek());
        }
        for (int index = right+1; index < s.length(); index++) {
            char c = s.charAt(index);
            if (!target.containsKey(c)) continue;
            current.put(c, current.get(c)+1);
            pq.offer(index);
            d = s.charAt(pq.peek());
            while (current.get(d) > target.get(d)) {
                pq.poll();
                left = pq.peek();
                if (index-left < minRange) {
                    minRight = index;
                    minLeft = left;
                    minRange = index - left;
                }
                current.put(d, current.get(d)-1);
                d = s.charAt(pq.peek());
            }
        }
        return s.substring(minLeft, minRight+1);
    }
    
    private int getLeft(Map<Character, Integer> target, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (target.containsKey(s.charAt(i))) {
                return i;
            }
        }
        return s.length();
    }
    
    private int findWindowStartWithZero(Map<Character, Integer> target, Map<Character, Integer> current, String s, Queue pq) {
        Set<Character> lack = new HashSet<>(target.keySet());
        int index = 0;
        for (; index < s.length(); index++) {
            char c = s.charAt(index);
            if (target.containsKey(c)) {
                current.putIfAbsent(c, 0);
                current.put(c, current.get(c)+1);
                pq.offer(index);
                if ((int)target.get(c) == (int)current.get(c)) {
                    lack.remove(c);
                    if (lack.size() == 0) break;
                }
            }  
        }
        return index;
    }
    
    private Map<Character, Integer> getTargetFrequency(String t) {
        Map<Character, Integer> result = new HashMap<Character, Integer>();
        for (char c : t.toCharArray()) {
            result.putIfAbsent(c, 0);
            result.put(c, result.get(c)+1);
        }
        return result;
    }
}
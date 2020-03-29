class Solution {
    public String alienOrder(String[] words) {
        int[] indegree = new int[26];
        Map<Character, Set<Character>> graph = new HashMap<>();
        if(!buildGraph(words, graph, indegree)) return "";
        return searchGraph(graph, indegree);
    }
    
    private String searchGraph(Map<Character, Set<Character>> graph, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<Character>();
        for (char c : graph.keySet()) {
            if (indegree[c-'a'] == 0) {
                queue.offer(c);
            }
        }
        while (!queue.isEmpty()) {
            char out = queue.poll();
            sb.append(out);
            for (char in : graph.get(out)) {
                indegree[in-'a'] -= 1;
                if (indegree[in-'a'] == 0) {
                    queue.offer(in);
                }
            }
        }
        return sb.length() == graph.size()? sb.toString() : "";
    }
    
    private boolean buildGraph(String[] words, Map<Character, Set<Character>> graph, int[] indegree) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<Character>());
            }
        }
        for (int i = 1; i < words.length; i++) {
            String s1 = words[i-1];
            String s2 = words[i];
            int length = Math.min(s1.length(), s2.length());
            int j;
            for (j = 0; j < length; j++) {
                char c1 = s1.charAt(j);
                char c2 = s2.charAt(j);
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        indegree[c2-'a'] += 1;
                    }
                    break;
                }
            }
            if (j == length && s1.length() > s2.length()) {
                return false;
            }
        }
        return true;
    }
}
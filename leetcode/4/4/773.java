class Solution {
    private Set<List<Integer>> visited;
    
    public int slidingPuzzle(int[][] b) {
        List<Integer> board = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                board.add(b[i][j]);
            }
        }
        List<Integer> target = new ArrayList<>(List.of(1, 2, 3, 4, 5, 0));
        visited = new HashSet<>();
        List<List<Integer>> current = new ArrayList<>();
        int steps = 0;
        current.add(board);
        while (!current.isEmpty()) {
            List<List<Integer>> next = new ArrayList<>();
            for (List<Integer> bd : current) {
                if (bd.equals(target)) return steps;
                if (visited.contains(bd)) continue;
                visited.add(bd);
                next.addAll(getAllMoveResult(bd));
            }
            steps += 1;
            current = next;
        }
        return -1;
    }
    
    private List<List<Integer>> getAllMoveResult(List<Integer> board) {
        List<List<Integer>> result = new ArrayList<>();
        int zeroIndex = board.indexOf(0);
        if (zeroIndex != 0 && zeroIndex != 3) {
            List<Integer> temp = swapCells(board, zeroIndex, zeroIndex-1);
            if (!visited.contains(temp)) result.add(temp);
        }
        if (zeroIndex != 5 && zeroIndex != 2) {
            List<Integer> temp = swapCells(board, zeroIndex, zeroIndex+1);
            if (!visited.contains(temp)) result.add(temp);
        }
        if (zeroIndex <= 2) {
            List<Integer> temp = swapCells(board, zeroIndex, zeroIndex+3);
            if (!visited.contains(temp)) result.add(temp);
        }
        else {
            List<Integer> temp = swapCells(board, zeroIndex, zeroIndex-3);
            if (!visited.contains(temp)) result.add(temp);
        }
        return result;
    }
    
    private List<Integer> swapCells(List<Integer> board, int a, int b) {
        List<Integer> dummy = new ArrayList<Integer>(board);
        dummy.set(a, dummy.get(b));
        dummy.set(b, 0);
        return dummy;
    }
}
class Solution {
    private int[][] board;
    public void gameOfLife(int[][] board) {
        // 0 dead
        // 1 alive
        // 2 dead -> live
        // 3 live -> dead
        this.board = board;
        if (board == null || board.length == 0 || board[0].length == 0) return;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int aliveNeighbor = getAliveNeighbor(i, j);
                //System.out.println(aliveNeighbor);
                if (board[i][j] == 1) {
                    if (aliveNeighbor == 2 || aliveNeighbor == 3) continue;
                    board[i][j] = 3;
                }
                else {
                    if (aliveNeighbor == 3) board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 3) board[i][j] = 0;
                else if (board[i][j] == 2) board[i][j] = 1;
            }
        }
    }
    
    private int getAliveNeighbor(int x, int y) {
        int[] delta = new int[]{-1, 0, 1};
        int result = 0;
        for (int dx : delta) {
            for (int dy : delta) {
                if (dx == 0 && dy == 0) continue;
                if (x+dx < 0 || x+dx >= board.length) continue;
                if (y+dy < 0 || y+dy >= board[0].length) continue;
                result += board[x+dx][y+dy] % 2;
            }
        }
        return result;
    }
}
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) return 0;
        int a = checkNum(A[0], A, B);
        int b = checkNum(B[0], A, B);
        if (a == -1 && b == -1) return -1;
        if (a == -1) return b;
        if (b == -1) return a;
        return Math.min(a, b);
    }
    
    private int checkNum(int n, int[] A, int[] B) {
        int countA = 0;
        int countB = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != n && B[i] != n) return -1;
            if (A[i] != n) countA += 1;
            if (B[i] != n) countB += 1;
        }
        countA = (countA > A.length/2)? A.length-countA : countA;
        countB = (countB > A.length/2)? A.length-countB : countB;
        return Math.min(countA, countB);
    }
}
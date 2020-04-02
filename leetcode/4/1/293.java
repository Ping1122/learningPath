class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int[][] endPoints = new int[intervals.length*2][];
        int index = 0;
        for (int[] interval : intervals) {
            endPoints[index] = new int[]{interval[0], 1};
            endPoints[index+1] = new int[]{interval[1], -1};
            index += 2;
        }
        Arrays.sort(endPoints, new Comparator<int[]>(){
            @Override
            public int compare(int[] left, int[] right) {
                if (left[0] == right[0]) return left[1] - right[1];
                return left[0] - right[0];
            }
        });
        int maxRoom = 0;
        int currentRoom = 0;
        for (int[] endPoint : endPoints) {
            currentRoom += endPoint[1];
            maxRoom = Math.max(currentRoom, maxRoom);
        }
        return maxRoom;
    }
}
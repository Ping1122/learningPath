class Solution {
    
    class Node {
        double distance;
        int[] point;
        
        Node(int[] point) {
            this.distance = (point[0]*point[0]+point[1]*point[1]);
            this.point = point;
        }
        
        @Override
        public String toString() {
            return "[" + point[0] + ", " + point[1] + "]";
        }
    }
    
    public int[][] kClosest(int[][] points, int K) {
        Queue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node left, Node right) {
                return (int)(right.distance - left.distance);
            }
        });
        for (int[] point : points) {
            Node node = new Node(point);
            if (q.size() < K) q.offer(node);
            else if (node.distance < q.peek().distance) {
                q.poll();
                q.offer(node);
            }
        }
        int[][] result = new int[K][];
        for (int i = 0; i < K; i++) {
            Node node = q.poll();
            result[i] = node.point;
        }
        return result;
    }
}
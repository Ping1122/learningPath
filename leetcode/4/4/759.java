

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        Queue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval left, Interval right) {
                double leftEnd = left.start == -1? left.end : left.start-0.5;
                double rightEnd = right.start == -1? right.end : right.start-0.5;
                return (int)(2*leftEnd - 2*rightEnd);
            }
        });
        for (List<Interval> employeeSchedule : schedule) {
            pq.addAll(employeeSchedule);
        }
        int currentEmployee = 0;
        Interval currentFreeTime = null;
        List<Interval> freeTime = new ArrayList<Interval>();
        while (pq.size() != 0) {
            Interval current = pq.poll();
            int start = current.start;
            if (current.start != -1) {
                currentEmployee += 1;
                current.start = -1;
                pq.offer(current);
            }
            else currentEmployee -= 1;
            if (currentEmployee == 0 && currentFreeTime == null) {
                currentFreeTime = new Interval();
                currentFreeTime.start = current.end;
            }
            if (currentFreeTime != null && currentEmployee != 0) {
                currentFreeTime.end = start;
                freeTime.add(currentFreeTime);
                currentFreeTime = null;
            }
            // for (Interval i : pq) {
            //     System.out.print("["+i.start+","+i.end+"]");
            // }
            // System.out.println(" ");
        }
        return freeTime;
    }
}









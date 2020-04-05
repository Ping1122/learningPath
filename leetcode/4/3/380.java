class RandomizedSet {
    
    private List<Integer> list;
    private Map<Integer, Integer> map;
    private Random random;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        // move the last element to the place idx of the element to delete
        int lastElement = list.get(list.size() - 1);
        int idx = map.get(val);
        list.set(idx, lastElement);
        map.put(lastElement, idx);
        // delete the last element
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
    
    private void removeFromIndex(int index) {
        if (index == list.size()-1) {
            list.remove(index);
            return;
        }
        int temp = list.get(index);
        list.set(index, temp);
        list.remove(list.size()-1);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
package decipher;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Container {

    private Queue<String> queue;
    private ReentrantLock lock = new ReentrantLock();

    public Container() {
        queue = new LinkedList<>();
    }

    public String get() {
        lock.lock();
        try {
            if (queue.isEmpty()) return null;
            return queue.poll();
        }
        finally {
            lock.unlock();
        }
    }

    public void put(String str) {
        lock.lock();
        try {
            queue.offer(str);
        }
        finally {
            lock.unlock();
        }
    }
}

import java.util.ArrayList;

public class BlockingQueue<T> {

    private final int capacity;

    ArrayList<T> queue = new ArrayList<>();

    public BlockingQueue(int maxCapacity) {
        this.capacity = maxCapacity;
    }

    public synchronized void enqueue(T element) {

        while (queue.size() >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.add(element);
        notify();
    }

    public synchronized T dequeue() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        T queueFirst = queue.getFirst();
        queue.removeFirst();
        notify();
        return queueFirst;
    }


    public synchronized int size() {
        return queue.size();
    }


}

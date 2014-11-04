package co.tjcelaya.panchi;

import com.google.common.collect.EvictingQueue;

/**
 * Created by yser on 11/2/14.
 */
public class PeekableRingBuffer<T> {

    EvictingQueue<T> ring;
    private T top;

    public PeekableRingBuffer(int size) {
        ring = EvictingQueue.create(size);
    }

    public boolean add(T el) {
        return ring.add(top = el);
    }

    public void clear() {
        top = null;
        ring.clear();
    }

    public T top() { return top; }

    public boolean addFresh(T el) {
        return el != top && ring.add(top = el);
    }
}

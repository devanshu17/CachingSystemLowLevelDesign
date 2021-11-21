package com.devanshu.cache.policies;
import java.util.*;

public class LRUEvictionPolicy<Key> implements IF_EvictionPolicy<Key> {

    private Deque<Key> dll;
    private HashSet<Key> keySet;
    private final int capacity;

    public LRUEvictionPolicy(int capacity) {
        this.dll = new LinkedList<>();
        this.keySet = new HashSet<>();
        this.capacity = capacity;
    }

    @Override
    public void keyAccessed(Key key) {
        if(!keySet.contains(key))
        {
            if(dll.size()==capacity)
            {
                evictKey();
                keySet.remove(key);
            }
        }
        else{
            dll.remove(key);
        }
        dll.addFirst(key);
        keySet.add(key);
    }

    @Override
    public Key evictKey() {
        Key evicted_key = dll.removeLast();
        if(evicted_key==null)
            return null;
        return evicted_key;
    }
}

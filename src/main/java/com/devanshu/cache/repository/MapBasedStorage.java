package com.devanshu.cache.repository;
import com.devanshu.cache.exception.NotFoundException;
import com.devanshu.cache.exception.StroageFullException;

import java.util.*;

public class MapBasedStorage<Key, Value> implements IF_storage<Key, Value> {
    
    Map<Key, Value> storage;
    private final Integer capacity;

    public MapBasedStorage(Integer capacity) {
        this.storage = new HashMap<>();
        this.capacity = capacity;
    }


    @Override
    public void add(Key key, Value value) throws StroageFullException {
        if(isStorageFull()) throw new StroageFullException("Capacity is full......");
            storage.put(key,value);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if(!storage.containsKey(key)) throw new NotFoundException(key + " doesn't exist in cache");
        return storage.get(key);
    }

    @Override
    public void remove(Key key) throws NotFoundException {
        if(!storage.containsKey(key)) throw new NotFoundException(key + " doesn't exist in cache");
        storage.remove(key);
    }

    private boolean isStorageFull(){
        return storage.size() == capacity;
    }
}

package com.devanshu.cache;

import com.devanshu.cache.exception.NotFoundException;
import com.devanshu.cache.exception.StroageFullException;
import com.devanshu.cache.policies.IF_EvictionPolicy;
import com.devanshu.cache.policies.LRUEvictionPolicy;
import com.devanshu.cache.repository.IF_storage;
import com.devanshu.cache.repository.MapBasedStorage;


public class Cache<Key, Value> {
    private final IF_EvictionPolicy<Key> evictionPolicy;
    private final IF_storage<Key, Value> storage;

    public Cache(LRUEvictionPolicy<Key> LRUEvictionPolicy, MapBasedStorage<Key, Value> MapBasedStorage) {
        this.evictionPolicy = LRUEvictionPolicy;
        this.storage = MapBasedStorage;
    }

    public void put(Key key, Value value)
    {
        try {
            storage.add(key,value);
            evictionPolicy.keyAccessed(key);
        }
        catch ( StroageFullException exception)
        {
            System.out.println("Got the storage full. Will try to evict");
            Key removed_key = evictionPolicy.evictKey();
            if(removed_key == null)
            {
                throw new RuntimeException("Unexpected Error.Storage full and no key to remove");
            }
            storage.remove(removed_key);
            System.out.println("Creating space by removing " + removed_key);
            put(key,value);
        }
    }

    public Value get(Key key)
    {
        try {
            Value val = storage.get(key);
            evictionPolicy.keyAccessed(key);
            return val;
        }
        catch ( NotFoundException notFoundException){
            System.out.println("Tried to access non-existing key");
            return null;
        }
    }
}

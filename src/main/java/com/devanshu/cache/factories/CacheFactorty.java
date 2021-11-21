package com.devanshu.cache.factories;

import com.devanshu.cache.Cache;
import com.devanshu.cache.policies.LRUEvictionPolicy;
import com.devanshu.cache.repository.MapBasedStorage;

import java.util.HashMap;

public class CacheFactorty<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity){
        return new Cache<Key, Value>(new LRUEvictionPolicy<Key>(capacity),
                new MapBasedStorage<Key, Value>(capacity));
    }
}

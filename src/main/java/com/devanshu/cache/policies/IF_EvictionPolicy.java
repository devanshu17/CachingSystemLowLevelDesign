package com.devanshu.cache.policies;


public interface IF_EvictionPolicy<Key> {

    void keyAccessed(Key key);

    Key evictKey();
}

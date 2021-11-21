package com.devanshu.cache.repository;

import com.devanshu.cache.exception.NotFoundException;
import com.devanshu.cache.exception.StroageFullException;

public interface IF_storage<Key, Value> {
     void add(Key key, Value value) throws StroageFullException;

     Value get(Key key) throws NotFoundException;

     void remove(Key key) throws NotFoundException;

}

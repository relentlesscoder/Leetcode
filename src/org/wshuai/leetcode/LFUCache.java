package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Created by Wei on 9/15/19.
 * #460 https://leetcode.com/problems/lfu-cache/
 */
public class LFUCache {
    int capacity;
    int min;
    private Map<Integer, Integer> vals;
    private Map<Integer, Integer> counts;
    private Map<Integer, LinkedHashSet<Integer>> lists;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        min = -1;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if(!vals.containsKey(key)){
            return -1;
        }
        int count = counts.get(key);
        counts.put(key, count+1);
        lists.get(count).remove(key);
        if(count == min && lists.get(count).size() == 0){
            min++;
        }
        if(!lists.containsKey(count+1)){
            lists.put(count+1, new LinkedHashSet<>());
        }
        lists.get(count+1).add(key);
        return vals.get(key);
    }

    public void put(int key, int value) {
        if(capacity <= 0){
            return;
        }
        if(vals.containsKey(key)){
            vals.put(key, value);
            get(key);
            return;
        }
        if(vals.size() >= capacity){
            int evict = lists.get(min).iterator().next();
            lists.get(min).remove(evict);
            vals.remove(evict);
        }
        vals.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

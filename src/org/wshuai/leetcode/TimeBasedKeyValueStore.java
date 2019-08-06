package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 8/6/19.
 * #981 https://leetcode.com/problems/time-based-key-value-store/
 */
public class TimeBasedKeyValueStore {
    private Map<String, TimeMapEntry> map;

    /** Initialize your data structure here. */
    public TimeBasedKeyValueStore() {
        map = new HashMap<String, TimeMapEntry>();
    }

    public void set(String key, String value, int timestamp) {
        TimeMapEntry entry = map.getOrDefault(key, new TimeMapEntry());
        map.put(key, entry);
        entry.setValue(value, timestamp);
    }

    public String get(String key, int timestamp) {
        TimeMapEntry entry = map.getOrDefault(key, null);
        if(entry == null){
            return "";
        }
        return entry.getValue(timestamp);
    }
}

class TimeMapEntry{

    private Map<Integer, String> map;
    private List<Integer> ts;

    /** Initialize your data structure here. */
    public TimeMapEntry() {
        map = new HashMap<Integer, String>();
        ts = new ArrayList<Integer>();
    }

    public void setValue(String value, int timestamp){
        ts.add(timestamp);
        map.put(timestamp, value);
    }

    public String getValue(int timestamp){
        int left = 0;
        int right = ts.size()-1;
        while(left < right){
            int idx = (left+right)/2;
            if(ts.get(idx) > timestamp){
                right = idx-1;
            }else if(ts.get(idx+1) <= timestamp){
                left = idx+1;
            }else{
                break;
            }
        }
        int max = ts.get(left);
        if(max > timestamp){
            return "";
        }
        return map.get(max);
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */

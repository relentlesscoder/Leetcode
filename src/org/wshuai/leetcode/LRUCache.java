package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 8/18/2016.
 * #146 https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {

  private Map<Integer, DListNode> kMap = new HashMap<Integer, DListNode>();
  private int capacity = 0;
  private DListNode lru = null;
  private DListNode mru = null;

  public LRUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    DListNode x = kMap.get(key);
    if(x == null){
      return -1;
    }else{
      if(mru != x){
        if(lru == x){
          if(x.prev != null){
            lru = x.prev;
            x.prev.next = null;
          }
        }else{
          x.prev.next = x.next;
          x.next.prev = x.prev;
        }
        mru.prev = x;
        x.next = mru;
        mru = x;
      }
      return x.value;
    }
  }

  public void set(int key, int value) {
    int size = kMap.size();
    DListNode x = kMap.get(key);
    if(x == null){
      if(size < capacity){
        DListNode n = new DListNode(value, key);
        if(size == 0){
          lru = n;
          mru = n;
        }else{
          mru.prev = n;
          n.next = mru;
          mru = n;
        }
        kMap.put(key, n);
      }else{
        DListNode o = lru;
        int rKey = o.key;
        kMap.remove(rKey);
        kMap.put(key, o);
        o.value = value;
        o.key = key;
        // only one entry
        if(lru.prev != null){
          lru.prev.next = null;
          lru = lru.prev;
          mru.prev = o;
          o.next = mru;
          o.prev = null;
          mru = o;
        }
      }
    }else{
      if(mru == x){
        x.value = value;
      }else{
        x.value = value;
        if(lru == x){
          if(x.prev != null){
            lru = x.prev;
            x.prev.next = null;
          }
        }else{
          x.prev.next = x.next;
          x.next.prev = x.prev;
        }
        mru.prev = x;
        x.next = mru;
        x.prev = null;
        mru = x;
      }
    }
  }
}

class DListNode{
  int value = 0;
  int key = 0;
  DListNode prev = null;
  DListNode next = null;

  public DListNode(int value, int key){
    this.value = value;
    this.key = key;
  }
}

package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 9/13/2019.
 * #889 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 */
public class InsertDeleteGetRandomO1DuplicatesAllowed {
    List<Integer> vals;
    Map<Integer, Set<Integer>> map;
    Random rand = new Random();

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1DuplicatesAllowed() {
        vals = new ArrayList<>();
        map = new HashMap<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        vals.add(val);
        if(map.containsKey(val)){
            map.get(val).add(vals.size()-1);
            return false;
        }
        Set<Integer> set = new HashSet<>();
        set.add(vals.size()-1);
        map.put(val, set);
        return true;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val) || map.get(val).size() == 0){
            return false;
        }
        Set<Integer> set = map.get(val);
        int idx = set.iterator().next();
        set.remove(idx);
        int last = vals.get(vals.size()-1);
        vals.set(idx, last);
        map.get(last).add(idx);
        map.get(last).remove(vals.size()-1);
        vals.remove(vals.size()-1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return vals.get(rand.nextInt(vals.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

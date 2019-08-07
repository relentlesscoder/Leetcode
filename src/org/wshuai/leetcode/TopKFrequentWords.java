package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 8/6/19.
 * #692 https://leetcode.com/problems/top-k-frequent-words/
 */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String str: words){
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        PriorityQueue<String> queue = new PriorityQueue<String>(
                (w1, w2) -> map.get(w1).intValue() == map.get(w2).intValue() ? w1.compareTo(w2) : map.get(w2)-map.get(w1)
        );

        for(String key: map.keySet()){
            queue.offer(key);
        }
        List<String> lst = new ArrayList<String>();
        int i = 0;
        while(i < k){
            lst.add(queue.poll());
            i++;
        }
        return lst;
    }
}

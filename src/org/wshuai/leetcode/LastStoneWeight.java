package org.wshuai.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by Wei on 8/21/19.
 * #1046 https://leetcode.com/problems/last-stone-weight/
 */
public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(11, Collections.reverseOrder());
        for(int s: stones){
            queue.offer(s);
        }
        while(queue.size() > 1){
            int x = queue.poll();
            int y = queue.poll();
            if(x == y){
                continue;
            }else{
                queue.offer(x > y ? x - y : y - x);
            }
        }
        return queue.isEmpty() ? 0 : queue.poll();
    }
}

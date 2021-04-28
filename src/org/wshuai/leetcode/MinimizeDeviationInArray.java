package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 04/28/2021.
 * #1675 https://leetcode.com/problems/minimize-deviation-in-array/
 */
public class MinimizeDeviationInArray {

    // time O(n*log(n)), space O(n)
    public int minimumDeviation(int[] nums) {
        int res = Integer.MAX_VALUE, min = Integer.MAX_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int num : nums){
            num = num % 2 == 0 ? num : num << 1;
            pq.offer(num);
            min = Math.min(min, num);
        }
        while(pq.peek() % 2 == 0){
            res = Math.min(res, pq.peek() - min);
            int next = pq.poll() / 2;
            min = Math.min(min, next);
            pq.offer(next);
        }
        return Math.min(res, pq.peek() - min);
    }
}

package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 11/08/2020.
 * #1636 https://leetcode.com/problems/sort-array-by-increasing-frequency/
 */
public class SortArrayByIncreasingFrequency {

    // time O(max(n, d*log(d))), space O(d)
    public int[] frequencySort(int[] nums) {
        int[] res = new int[nums.length], count = new int[201];
        for(int num : nums){
            count[num + 100]++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        for(int i = 0; i < 201; i++){
            if(count[i] == 0){
                continue;
            }
            pq.offer(new int[]{count[i], i - 100});
        }
        int k = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int v = cur[1], c = cur[0];
            while(c-- > 0){
                res[k++] = v;
            }
        }
        return res;
    }
}

package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by Wei on 9/14/19.
 * #1057 https://leetcode.com/problems/campus-bikes/
 */
public class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] == b[2] ? (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]) : a[2] - b[2]);
        for(int i = 0; i < workers.length; i++){
            for(int j = 0; j < bikes.length; j++){
                int d = Math.abs(workers[i][0]-bikes[j][0]) +  Math.abs(workers[i][1]-bikes[j][1]);
                int[] wb = new int[]{i, j, d};
                pq.add(wb);
            }
        }
        int[] res = new int[workers.length];
        Arrays.fill(res, -1);
        Set<Integer> used = new HashSet<>();
        while(!pq.isEmpty()){
            int[] wb  = pq.poll();
            if(res[wb[0]] == -1 && !used.contains(wb[1])){
                res[wb[0]] = wb[1];
                used.add(wb[1]);
            }
        }
        return res;
    }
}

package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 03/05/2021.
 * #1654 https://leetcode.com/problems/minimum-jumps-to-reach-home/
 */
public class MinimumJumpsToReachHome {

    // time O(max(x, f) + a + b), space O(max(x, f) + a + b)
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int jumps = 0, furthest = x + a + b;
        Set<String> visited = new HashSet<>();
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offerLast(new int[]{0, 0});
        visited.add("0,0");
        for(int f : forbidden){
            visited.add(f + ",0");
            visited.add(f + ",1");
            furthest = Math.max(furthest, f + a + b);
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                int[] cur = queue.pollFirst();
                if(cur[0] == x){
                    return jumps;
                }
                int forward = cur[0] + a, backward = cur[0] - b;
                if(forward <= furthest && visited.add(forward + ",0")){
                    queue.offerLast(new int[]{forward, 0});
                }
                if(cur[1] == 0 && backward >= 0 && visited.add(backward + ",1")){
                    queue.offerLast(new int[]{backward, 1});
                }
            }
            jumps++;
        }
        return -1;
    }
}

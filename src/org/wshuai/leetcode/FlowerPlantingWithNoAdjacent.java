package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 9/17/19.
 * #1042 https://leetcode.com/problems/flower-planting-with-no-adjacent/
 */
public class FlowerPlantingWithNoAdjacent {
    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for(int i = 0; i < N; i++){
            adj.put(i, new HashSet<>());
        }
        for(int[] p: paths){
            adj.get(p[0] - 1).add(p[1] - 1);
            adj.get(p[1] - 1).add(p[0] - 1);
        }
        int[] res = new int[N];
        for(int i = 0; i < N; i++){
            int[] colors = new int[5];
            for(int j: adj.get(i)){
                colors[res[j]] = 1;
            }
            for(int c = 4; c > 0; c--){
                if(colors[c] == 0){
                    res[i] = c;
                }
            }
        }
        return res;
    }
}

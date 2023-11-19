package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/14/2021.
 * #1722 https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/
 */
public class MinimizeHammingDistanceAfterSwapOperations {

    // time O(n*log(n)), space O(n)
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int res = 0, n = source.length;
        int[] root = new int[n];
        for(int i = 0; i < n; i++){
            root[i] = i;
        }
        for(int[] swap : allowedSwaps){
            int r1 = find(swap[0], root);
            int r2 = find(swap[1], root);
            if(r1 == r2){
                continue;
            }
            root[r2] = r1;
        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            int r = find(i, root);
            map.putIfAbsent(r, new HashMap<>());
            Map<Integer, Integer> count = map.get(r);
            count.put(source[i], count.getOrDefault(source[i], 0) + 1);
        }
        for(int i = 0; i < n; i++){
            int r = find(i, root);
            Map<Integer, Integer> count = map.get(r);
            if(count.getOrDefault(target[i], 0) > 0){
                count.put(target[i], count.get(target[i]) - 1);
            }else{
                res++;
            }
        }
        return res;
    }

    private int find(int r, int[] root){
        if(r != root[r]){
            root[r] = find(root[r], root);
        }
        return root[r];
    }
}

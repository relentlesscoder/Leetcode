package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/14/2020.
 * #1583 https://leetcode.com/problems/count-unhappy-friends/
 */
public class CountUnhappyFriends {

    // time O(n^2), space O(n^2)
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int res = 0;
        int[] map = new int[n];
        for(int[] p : pairs){
            map[p[0]] = p[1];
            map[p[1]] = p[0];
        }
        Map<Integer, Integer>[] pref = new Map[n];
        for(int i = 0; i < n; i++){
            pref[i] = new HashMap<>();
            for(int j = 0; j < n - 1; j++){
                pref[i].put(preferences[i][j], j);
            }
        }
        for(int i = 0; i < n; i++){
            for(int j : preferences[i]){
                if(pref[i].get(map[i]) > pref[i].get(j)
                        && pref[j].get(map[j]) > pref[j].get(i)){
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}

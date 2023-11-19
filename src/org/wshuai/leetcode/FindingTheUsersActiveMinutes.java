package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 05/21/2021.
 * #1817 https://leetcode.com/problems/finding-the-users-active-minutes/
 */
public class FindingTheUsersActiveMinutes {

    // time O(n), space O(n)
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        int[] res = new int[k];
        Map<Integer, Set<Integer>> uam = new HashMap<>();
        for(int[] log : logs) {
            uam.putIfAbsent(log[0], new HashSet<>());
            uam.get(log[0]).add(log[1]);
        }
        for(int key : uam.keySet()){
            res[uam.get(key).size() - 1]++;
        }
        return res;
    }
}

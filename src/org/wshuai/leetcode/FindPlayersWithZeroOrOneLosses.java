package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 01/24/2024.
 * #2225 https://leetcode.com/problems/find-players-with-zero-or-one-losses/
 */
public class FindPlayersWithZeroOrOneLosses {

    // time O(n + l1 * log(l1) + l2 * log(l2)), space O(n)
    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] m : matches) {
            map.put(m[0], map.getOrDefault(m[0], 0));
            map.put(m[1], map.getOrDefault(m[1], 0) + 1);
        }
        for (int key : map.keySet()) {
            int lost = map.get(key);
            if (lost == 0) {
                l1.add(key);
            } else if (lost == 1) {
                l2.add(key);
            }
        }
        Collections.sort(l1);
        Collections.sort(l2);
        res.add(l1);
        res.add(l2);
        return res;
    }
}

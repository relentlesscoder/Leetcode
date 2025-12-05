package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 01/11/2024.
 * #2766 https://leetcode.com/problems/relocate-marbles/
 */
public class RelocateMarbles {

    // time O(n * log(n)), space O(n)
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i < moveFrom.length; i++) {
            set.remove(moveFrom[i]);
            set.add(moveTo[i]);
        }
        for (int p : set) {
            res.add(p);
        }
        Collections.sort(res);
        return res;
    }
}

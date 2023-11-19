package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/18/2023.
 * #2150 https://leetcode.com/problems/find-all-lonely-numbers-in-the-array/
 */
public class FindAllLonelyNumbersInTheArray {

    // time O(n), space O(n)
    public List<Integer> findLonely(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> unique = new HashSet<>(), duplicate = new HashSet<>();
        for (int num : nums) {
            if (!unique.add(num)) {
                duplicate.add(num);
            }
        }
        for (int num : nums) {
            if (duplicate.contains(num)) {
                continue;
            }
            if (!unique.contains(num - 1) && !unique.contains(num + 1)) {
                res.add(num);
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public List<Integer> findLonelyOnePass(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        for (int num : counter.keySet()) {
            if (counter.get(num) != 1) {
                continue;
            }
            if (!counter.containsKey(num - 1) && !counter.containsKey(num + 1)) {
                res.add(num);
            }
        }
        return res;
    }
}

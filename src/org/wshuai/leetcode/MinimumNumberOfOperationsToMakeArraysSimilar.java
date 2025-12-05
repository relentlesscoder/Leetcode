package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 02/03/2024.
 * #2449 https://leetcode.com/problems/minimum-number-of-operations-to-make-arrays-similar/
 */
public class MinimumNumberOfOperationsToMakeArraysSimilar {

    // time O(n * log(n)), space O(n)
    public long makeSimilar(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);
        List<Integer> odd1 = new ArrayList<>(), odd2 = new ArrayList<>(),
                even1 = new ArrayList<>(), even2 = new ArrayList<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                even1.add(num);
            } else {
                odd1.add(num);
            }
        }
        for (int num : target) {
            if (num % 2 == 0) {
                even2.add(num);
            } else {
                odd2.add(num);
            }
        }
        return count(even1, even2) + count(odd1, odd2);
    }

    private long count(List<Integer> l1, List<Integer> l2) {
        long res = 0;
        for (int i = 0; i < l1.size(); i++) {
            int num = l1.get(i), target = l2.get(i);
            if (num == target) {
                continue;
            }
            if (num > target) {
                res += (num - target) / 2;
            }
        }
        return res;
    }
}

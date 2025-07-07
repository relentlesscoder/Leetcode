package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 07/06/2025.
 * #2948 https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/
 */
public class MakeLexicographicallySmallestArrayBySwappingElements {

    // time O(n * log(n)), space O(n)
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length, group = 0;
        int[] res = new int[n];
        Map<Integer, Integer> groupMapping = new HashMap<>();
        Map<Integer, Deque<Integer>> valuesMapping = new HashMap<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
        }
        Arrays.sort(arr);
        groupMapping.put(arr[0], group);
        valuesMapping.putIfAbsent(group, new ArrayDeque<>());
        valuesMapping.get(group).offer(arr[0]);
        for (int i = 1; i < n; i++) {
            if (arr[i] - arr[i - 1] > limit) {
                group++;
            }
            groupMapping.put(arr[i], group);
            valuesMapping.putIfAbsent(group, new ArrayDeque<>());
            valuesMapping.get(group).offer(arr[i]);
        }
        for (int i = 0; i < n; i++) {
            res[i] = valuesMapping.get(groupMapping.get(nums[i])).poll();
        }
        return res;
    }
}

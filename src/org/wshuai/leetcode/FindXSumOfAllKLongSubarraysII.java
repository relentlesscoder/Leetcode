package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Wei on 09/26/2025.
 * #3321 https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii/
 */
public class FindXSumOfAllKLongSubarraysII {

    private final TreeSet<int[]> minQueue = new TreeSet<>((a, b) ->
            a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
    private final TreeSet<int[]> maxQueue = new TreeSet<>((a, b) ->
            a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
    private final Map<Integer, Integer> count = new HashMap<>();
    private long sum = 0;

    // time O(n * log(k)), space O(n)
    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] res = new long[n - k + 1];
        for (int i = 0; i < n; i++) {
            delete(nums[i]);
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            insert(nums[i]);

            int l = i + 1 - k;
            if (l < 0) {
                continue;
            }

            while (!maxQueue.isEmpty() && minQueue.size() < x) {
                maxToMin();
            }
            while (minQueue.size() > x) {
                minToMax();
            }
            res[l] = sum;

            delete(nums[l]);
            count.put(nums[l], count.getOrDefault(nums[l], 0) - 1);
            insert(nums[l]);
        }
        return res;
    }

    private void insert(int val) {
        int cnt = count.getOrDefault(val, 0);
        if (cnt == 0) {
            return;
        }
        int[] arr = new int[]{cnt, val};
        if (!minQueue.isEmpty() && minQueue.comparator().compare(arr, minQueue.first()) > 0) {
            sum += (long) arr[0] * arr[1];
            minQueue.add(arr);
        } else {
            maxQueue.add(arr);
        }
    }

    private void delete(int val) {
        int cnt = count.getOrDefault(val, 0);
        if (cnt == 0) {
            return;
        }
        int[] arr = new int[]{cnt, val};
        if (minQueue.contains(arr)) {
            sum -= (long) arr[0] * arr[1];
            minQueue.remove(arr);
        } else {
            maxQueue.remove(arr);
        }
    }

    private void minToMax() {
        int[] arr = minQueue.pollFirst();
        sum -= (long) arr[0] * arr[1];
        maxQueue.add(arr);
    }

    private void maxToMin() {
        int[] arr = maxQueue.pollLast();
        sum += (long) arr[0] * arr[1];
        minQueue.add(arr);
    }
}

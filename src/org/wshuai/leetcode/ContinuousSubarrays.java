package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * Created by Wei on 09/12/2023.
 * #2762 https://leetcode.com/problems/continuous-subarrays/
 */
public class ContinuousSubarrays {

    // time O(n), space O(n)
    public long continuousSubarraysMonotonicStack(int[] nums) {
        long res = 0;
        int n = nums.length;
        Deque<Integer> minStack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; i++) {
            // maxStack maintains a monotonic decreasing array so stack bottom
            // is the max
            while (!maxStack.isEmpty() && nums[maxStack.peek()] <= nums[i]) {
                maxStack.pop();
            }
            // minStack maintains a monotonic increasing array so stack bottom
            // is the min
            while (!minStack.isEmpty() && nums[minStack.peek()] >= nums[i]) {
                minStack.pop();
            }
            minStack.push(i);
            maxStack.push(i);
            // If the difference between max and min is larger than 2, poll the
            // bottom values out.
            while (nums[maxStack.peekLast()] - nums[minStack.peekLast()] > 2) {
                if (minStack.peekLast() == j) {
                    minStack.pollLast();
                }
                if (maxStack.peekLast() == j) {
                    maxStack.pollLast();
                }
                j++;
            }
            res += i - j + 1;
        }
        return res;
    }

    // time O(n * log(D)), space O(D)
    public long continuousSubarraysTreeMap(int[] nums) {
        // For each subarray {i, j}, it adds j - i + 1 continuous subarrays
        // to the result, e.g. 2434 -> 4, 34, 434, 2434
        long res = 0;
        int n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            map.merge(nums[i], 1, Integer::sum);
            while (map.lastKey() - map.firstKey() > 2) { // D will not be larger than 3
                int cnt = map.merge(nums[j], -1, Integer::sum);
                if (cnt == 0) {
                    map.remove(nums[j]);
                }
                j++;
            }
            res += i - j + 1;
        }
        return res;
    }
}

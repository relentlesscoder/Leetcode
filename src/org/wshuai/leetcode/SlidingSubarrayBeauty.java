package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/06/2025.
 * #2653 https://leetcode.com/problems/sliding-subarray-beauty/
 */
public class SlidingSubarrayBeauty {

    // time O(n * log(m) * log(m)), space O(m)
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray(); // O(m * log(m))
        int m = sorted.length;
        Map<Integer, Integer> idxMap = new HashMap<>(), valueMap = new HashMap<>();
        for (int i = 0; i < m; i++) { // O(m) use 1 based index
            idxMap.put(sorted[i], i + 1);
            valueMap.put(i + 1, sorted[i]);
        }
        BIT bit = new BIT(m);
        for (int i = 0; i < n; i++) { // O(n)
            bit.update(idxMap.get(nums[i]), 1); // O(log(m))
            if (i - k + 1 < 0) {
                continue;
            }
            int ans = bit.search(x); // O(log(m) * log(m))
            res[i - k + 1] = Math.min(0, valueMap.get(ans));
            bit.update(idxMap.get(nums[i - k + 1]), -1); // O(log(m))
        }
        return res;
    }

    private static class BIT {

        private int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += (index & -index);
            }
        }

        public int search(int x) {
            int left = 1, right = tree.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (pre(mid) < x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }

        private int pre(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= (index & -index);
            }
            return res;
        }
    }

    // time O(n * MAX), space O(MAX)
    public int[] getSubarrayBeautyBrutalForceOptimized(int[] nums, int k, int x) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int[] freq = new int[101];
        for (int i = 0; i < n; i++) {
            freq[nums[i] + 50]++;
            if (i - k + 1 < 0) {
                continue;
            }
            int ans = 0, count = 0;
            for (int v = -50; v < 0; v++) {
                count += freq[v + 50];
                if (count >= x) {
                    ans = v;
                    break;
                }
            }
            res[i - k + 1] = ans;
            freq[nums[i - k + 1] + 50]--;
        }
        return res;
    }

    // time O(n * MAX), space O(MAX)
    public int[] getSubarrayBeautyBrutalForce(int[] nums, int k, int x) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freq.merge(nums[i], 1, Integer::sum);
            if (i - k + 1 < 0) {
                continue;
            }
            int ans = 0, count = 0;
            for (int v = -50; v < 0; v++) {
                count += freq.getOrDefault(v, 0);
                if (count >= x) {
                    ans = v;
                    break;
                }
            }
            res[i - k + 1] = ans;
            int f = freq.merge(nums[i - k + 1], -1, Integer::sum);
            if (f == 0) {
                freq.remove(nums[i - k + 1]);
            }
        }
        return res;
    }
}

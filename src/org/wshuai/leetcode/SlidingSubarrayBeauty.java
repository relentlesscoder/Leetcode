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
        Map<Integer, Integer> map = new HashMap<>(), reverseMap = new HashMap<>();
        for (int i = 1; i <= m; i++) { // O(m)
            map.put(sorted[i - 1], i);
            reverseMap.put(i, sorted[i - 1]);
        }
        BIT bit = new BIT(m);
        for (int i = 0; i < k - 1; i++) {
            bit.update(map.get(nums[i]), 1); // O(log(m))
        }
        for (int i = k - 1, j = 0; i < n; i++) { // O(n)
            bit.update(map.get(nums[i]), 1); // O(log(m))
            int index = bit.search(x); // O(log(m) * log(m))
            res[j++] = Math.min(0, reverseMap.get(index));
            bit.update(map.get(nums[i + 1 - k]), -1); // O(log(m))
        }
        return res;
    }

    private class BIT {

        private int m;
        private int[] tree;

        public BIT(int n) {
            m = n + 1;
            tree = new int[m];
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += index & -index;
            }
        }

        public int query(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }

        public int search(int x) {
            int low = 1, high = m - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (query(mid) < x) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }

    // time O(n * MAX), space O(MAX)
    public int[] getSubarrayBeautyBrutalForce(int[] nums, int k, int x) {
        int n = nums.length, max = -1;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; i++) { // O(n)
            nums[i] += 50;
            max = Math.max(max, nums[i]);
        }
        // This solution works due to small value space
        int[] map = new int[max + 1];
        for (int i = 0, j = 0, l = 0; i < n; i++) {
            map[nums[i]]++;
            if (i >= k) {
                map[nums[j++]]--;
            }
            if (i >= k - 1) {
                int val = -1, count = 0;
                for (int v = 0; v <= max; v++) {
                    count += map[v];
                    if(count >= x) {
                        val = v;
                        break;
                    }
                }
                res[l++] = Math.min(val - 50, 0);
            }
        }
        return res;
    }
}

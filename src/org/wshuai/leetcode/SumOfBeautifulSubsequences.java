package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/07/2025.
 * #3671 https://leetcode.com/problems/sum-of-beautiful-subsequences/
 */
public class SumOfBeautifulSubsequences {

    private static final int MOD = (int)1e9 + 7;

    // time O(n * sqrt(m) + m^2), space O(m^2)
    public int totalBeauty(int[] nums) {
        long res = 0;
        int n = nums.length, m = 0;
        for (int num : nums) {
            m = Math.max(m, num);
        }

        // Build factor to number mapping
        List<Integer>[] groups = new ArrayList[m + 1];
        Arrays.setAll(groups, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) { // O(n)
            // Find all factors for nums[i]
            for (int d = 1; d * d <= nums[i]; d++) { // O(sqrt(m))
                if (nums[i] % d == 0) {
                    groups[d].add(nums[i]);
                    if (d * d < nums[i]) {
                        groups[nums[i] / d].add(nums[i]);
                    }
                }
            }
        }

        int[] gcd = new int[m + 1];
        for (int i = m; i >= 1; i--) { // O(m)
            // Count GCD for i, 2*i, 3*i, ...
            long count = countLIS(groups[i], i, m); // total O(n * log(m/g))
            // Minus count of GCD for 2*i, 3*i, ...
            for (int j = i * 2; j <= m; j += i) { // O(m)
                count -= gcd[j];
            }
            count %= MOD;
            gcd[i] = (int) count;
            res += count * i % MOD;
        }
        return (int) ((res % MOD + MOD) % MOD); // Keep res positive
    }

    private long countLIS(List<Integer> nums, int gcd, int max) {
        BIT bit = new BIT(max / gcd);
        long res = 0;
        for (int num : nums) { // O(n)
            int index = num / gcd + 1;
            long count = bit.query(index - 1) + 1; // O(log(m/g))
            count %= MOD;
            res += count;
            bit.update(index, count); // O(log(m/g))
        }
        return res;
    }

    private class BIT {

        private long[] tree;

        public BIT(int n) {
            tree = new long[n + 1];
        }

        public void update(int index, long val) {
            while (index < tree.length) {
                tree[index] += val;
                index += index & -index;
            }
        }

        public long query(int index) {
            long res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }
    }
}

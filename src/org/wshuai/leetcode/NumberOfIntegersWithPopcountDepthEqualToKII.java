package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/05/2025.
 * #3624 https://leetcode.com/problems/number-of-integers-with-popcount-depth-equal-to-k-ii/
 */
public class NumberOfIntegersWithPopcountDepthEqualToKII {

    // time O((m + n) * log(n) + 6 * n), space O(6 * n)
    public int[] popcountDepth(long[] nums, long[][] queries) {
        int n = nums.length, m = queries.length;
        BIT[] bits = new BIT[6];
        Arrays.setAll(bits, i -> new BIT(n)); // 6 * n
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) { // O(n)
            int depth = popcountDepth(nums[i]); // O(log(d)) <= 50
            if (depth <= 5) {
                bits[depth].update(i + 1, 1); // O(log(n))
            }
        }
        for (int i = 0; i < m; i++) { // O(m)
            long[] query = queries[i];
            if (query[0] == 1L) {
                int left = (int) query[1], right = (int) query[2], k = (int) query[3];
                ans.add(bits[k].query(right + 1) - bits[k].query(left)); // O(log(n))
            } else {
                int index = (int) query[1],
                        oldVal = popcountDepth(nums[index]),
                        newVal = popcountDepth(query[2]); // O(log(d)) <= 50
                nums[index] = query[2];
                if (oldVal <= 5) {
                    bits[oldVal].update(index + 1, -1); // O(log(n))
                }
                if (newVal <= 5) {
                    bits[newVal].update(index + 1, 1); // O(log(n))
                }
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) { // O(m)
            res[i] = ans.get(i);
        }
        return res;
    }

    private static int popcountDepth(long x) {
        int res = 0;
        while (x > 1) {
            res++;
            x = Long.bitCount(x);
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
    }
}

package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/06/2025.
 * #2736 https://leetcode.com/problems/maximum-sum-queries/
 */
public class MaximumSumQueries {

    // time O((m + n) * log(m + n)), space O(m + n)
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        // Same idea as #2250
        int n = nums1.length, m = queries.length;
        int[][] nums = new int[n][2];
        Arrays.setAll(nums, i -> new int[]{nums1[i], nums2[i]}); // O(n)
        int[][] nq = new int[m][3];
        Arrays.setAll(nq, i -> new int[]{queries[i][0], queries[i][1], i}); // O(m)

        Set<Integer> set = new HashSet<>();
        for (int[] a : nums) {
            set.add(a[1]);
        }
        for (int[] q : nq) {
            set.add(q[1]);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list); // O(k * log(k)), k <= m + n
        int k = list.size();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(list.get(i), i);
        }

        // Sort nums and nq by nums[0] in DESC
        Arrays.sort(nums, (a, b) -> b[0] - a[0]); // O(n * log(n))
        Arrays.sort(nq, (a, b) -> b[0] - a[0]); // O(m * log(m))

        BIT bit = new BIT(k);
        int[] res = new int[m];
        int idx = 0;
        for (int[] q : nq) { // O(m)
            int x = q[0], y = q[1], i = q[2];
            // The sorting above ensures all previous added nums[idx][0] >= current x
            while (idx < n && nums[idx][0] >= x) { // total O(n * log(k))
                // Update in BIT
                bit.update(k - map.get(nums[idx][1]), nums[idx][0] + nums[idx][1]);
                idx++;
            }
            // k - map.get(y) to map index greater than y to the left of BIT
            res[i] = bit.query(k - map.get(y)); // O(log(k))
        }
        return res;
    }

    private static class BIT {

        private int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
            Arrays.fill(tree, -1);
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] = Math.max(tree[index], val);
                index += index & -index;
            }
        }

        public int query(int index) {
            int res = -1;
            while (index > 0) {
                res = Math.max(res, tree[index]);
                index -= index & -index;
            }
            return res;
        }
    }
}

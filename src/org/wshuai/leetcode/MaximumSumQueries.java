package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by Wei on 11/06/2025.
 * #2736 https://leetcode.com/problems/maximum-sum-queries/
 */
public class MaximumSumQueries {

    // time O(m * log(m) + n * log(n) + m * log(n)), space O(m + n)
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        // https://leetcode.cn/problems/maximum-sum-queries/solutions/2305395/pai-xu-dan-diao-zhan-shang-er-fen-by-end-of9h/
        int n = nums1.length, m = queries.length;
        int[][] nums = new int[n][2];
        Arrays.setAll(nums, i -> new int[]{nums1[i], nums2[i]}); // O(n)
        int[][] nq = new int[m][3];
        Arrays.setAll(nq, i -> new int[]{queries[i][0], queries[i][1], i}); // O(m)

        // Sort nums and nq by nums[0] in DESC
        Arrays.sort(nums, (a, b) -> b[0] - a[0]); // O(n * log(n))
        Arrays.sort(nq, (a, b) -> b[0] - a[0]); // O(m * log(m))

        int[] res = new int[m];
        List<int[]> stack = new ArrayList<>();
        int idx = 0;
        for (int[] q : nq) { // O(m)
            int x = q[0], y = q[1], i = q[2];
            // The sorting above ensures all previous added nums[idx][0] >= current x
            for (; idx < n && nums[idx][0] >= x; idx++) { // Total O(n)
                while (!stack.isEmpty() && stack.get(stack.size() - 1)[1] <= nums[idx][0] + nums[idx][1]) {
                    stack.remove(stack.size() - 1);
                }
                if (stack.isEmpty() || stack.get(stack.size() - 1)[0] < nums[idx][1]) {
                    stack.add(new int[] {nums[idx][1], nums[idx][0] + nums[idx][1]});
                }
            }
            int l = binarySearch(stack, y); // O(log(n))
            res[i] = l < stack.size() ? stack.get(l)[1] : -1;
        }
        return res;
    }

    private int binarySearch(List<int[]> stack, int target) {
        int left = 0, right = stack.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (stack.get(mid)[0] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // time O((m + n) * log(m + n)), space O(m + n)
    public int[] maximumSumQueriesBinaryIndexedTree(int[] nums1, int[] nums2, int[][] queries) {
        // Same idea as #2250
        int n = nums1.length, m = queries.length;
        int[][] nums = new int[n][2];
        Arrays.setAll(nums, i -> new int[]{nums1[i], nums2[i]}); // O(n)
        int[][] nq = new int[m][3];
        Arrays.setAll(nq, i -> new int[]{queries[i][0], queries[i][1], i}); // O(m)

        // Do discretization on nums[1] and queries[1] values
        int[] qy = IntStream
                .range(0, m)
                .map(i -> nq[i][1])
                .toArray();
        int[] sorted = IntStream
                .concat(Arrays.stream(nums2), Arrays.stream(qy))
                .distinct()
                .sorted()
                .toArray(); // k * log(k)
        int k = sorted.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(sorted[i], i);
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
            // k - (k - 1) -> 1
            // k - (k - 2) -> 2
            //   ...
            // k - (1) -> k - 1
            // k - (0) -> k
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

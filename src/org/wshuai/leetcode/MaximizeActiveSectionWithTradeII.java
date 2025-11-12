package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/13/2025.
 * #3501 https://leetcode.com/problems/maximize-active-section-with-trade-ii/
 */
public class MaximizeActiveSectionWithTradeII {

    // time O(n + k + m * log(k)), space O(m + k)
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length(), m = queries.length, ones = n;
        // Calculate all-0s segments
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0, j = 0; ; ) { // O(n)
            while (i < n && s.charAt(i) == '1') {
                i++;
            }
            if (i == n) {
                break;
            }
            j = i + 1;
            while (j < n && s.charAt(j) == '0') {
                j++;
            }
            intervals.add(new int[] {i, j - 1});
            ones -= j - i; // Total count of ones
            i = j;
        }
        Integer[] res = new Integer[m];
        int k = intervals.size();
        // No zeros in input
        if (k == 0) {
            Arrays.fill(res, ones); // O(m)
            return Arrays.asList(res);
        }
        // Sum of two adjacent all-0s segments
        int[] zeroCounts = new int[k];
        for (int i = 1; i < k; i++) { // O(k)
            zeroCounts[i] = intervals.get(i)[1] - intervals.get(i)[0] + 1 +
                    intervals.get(i - 1)[1] - intervals.get(i - 1)[0] + 1;
        }
        SegmentTree st = new SegmentTree(zeroCounts); // O(k)
        for (int i = 0; i < m; i++) { // O(m)
            int[] q = queries[i];
            int start = q[0], end = q[1];
            if (start > intervals.get(k - 1)[1] || end < intervals.get(0)[0]) {
                res[i] = ones;
                continue;
            }
            // Search for lower bound for start
            int lb = binarySearchLowerBound(intervals, start); // O(log(k))
            // Search for upper bound for end
            int ub = binarySearchUpperBound(intervals, end); // O(log(k))
            int maxZeros = 0;
            // There are at least 2 all-0s segments between (lb, ub),
            // the range is exclusive since They might be "incomplete"
            // all-0s segments cut by query start or end.
            // For example, we have below 5 all-0s segments:
            // [1,3] [5,8] [12,14] [16,17] [19,22]
            // After binary search, we find lower bound is 0 and upper
            // bound is 4. By checking lb + 2 <= ub -1, it ensures that
            // at least 2 all-0s segments between (0,4). Then we query
            // in segment tree for range [2,3] to find the maximum zeros
            // for two adjacent segments. Note that since segment tree
            // stores the count[i] = zeros[i] + zeros[i - 1] so we start
            // at index lb + 2 (2).
            if (lb + 2 <= ub - 1) {
                maxZeros = st.query(lb + 2, ub - 1); // O(log(k))
            }
            // Now we handle the two all-0s segment ub and lb.
            if (ub > lb) {
                maxZeros = Math.max(maxZeros,
                        // calculate zeros for segment lb + 1 (may cut by end)
                        Math.min(intervals.get(lb + 1)[1], end) - intervals.get(lb + 1)[0] + 1
                        // calculate zeros for segment lb (may cut by start)
                                + intervals.get(lb)[1] - Math.max(intervals.get(lb)[0], start) + 1);
                maxZeros = Math.max(maxZeros,
                        // calculate zeros for segment ub - 1 (may cut by start)
                        intervals.get(ub - 1)[1] - Math.max(intervals.get(ub - 1)[0], start) + 1
                        // calculate zeros for segment ub (may cut by end)
                                + Math.min(intervals.get(ub)[1], end) - intervals.get(ub)[0] + 1);
            }
            res[i] = ones + maxZeros;
        }
        return Arrays.asList(res);
    }

    private int binarySearchLowerBound(List<int[]> nums, int target) {
        int low = 0, high = nums.size() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums.get(mid)[1] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int binarySearchUpperBound(List<int[]> nums, int target) {
        int low = 0, high = nums.size() - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (nums.get(mid)[0] > target) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }

    private static class SegmentTree {

        private final int n;
        private final int[] tree;

        public SegmentTree(int[] nums) {
            this.n = nums.length;
            tree = new int[2 << (32 - Integer.numberOfLeadingZeros(n))];
            build(1, 0, n - 1, nums);
        }

        public int query(int start, int end) {
            return query(1, 0, n - 1, start, end);
        }

        private int query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node];
            }
            int mid = left + (right - left) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end);
            }
            if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end);
            }
            int leftRes = query(node * 2, left, mid, start, end);
            int rightRes = query(node * 2 + 1, mid + 1, right, start, end);
            return merge(leftRes, rightRes);
        }

        private void build(int node, int left, int right, int[] nums) {
            if (left == right) {
                tree[node] = nums[left];
                return;
            }
            int mid = left + (right - left) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);
            maintain(node);
        }

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private int merge(int left, int right) {
            return Math.max(left, right);
        }
    }
}

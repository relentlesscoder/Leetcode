package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2025.
 * #2569 https://leetcode.com/problems/handling-sum-queries-after-update/
 */
public class HandlingSumQueriesAfterUpdate {

    // time O(n + q * log(n)), space O(n)
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        SegmentTree st = new SegmentTree(nums1);
        long sum = 0;
        // Pre-calculate the sum of nums2
        for (int num : nums2) {
            sum += num;
        }
        int m = 0;
        for (int[] q : queries) {
            if (q[0] == 3) {
                m++;
            }
        }
        long[] res = new long[m];
        int i = 0;
        for (int[] q : queries) {
            if (q[0] == 1) {
                st.update(q[1], q[2]); // Lazy range update
            } else if (q[0] == 2) {
                sum += (long) q[1] * st.allCount(); // Add total p * count[1] to sum
            } else {
                res[i++] = sum;
            }
        }
        return res;
    }

    private static class SegmentTree {

        private final int n;
        private final int[] count;
        private final boolean[] flip;

        public SegmentTree(int[] nums) {
            this.n = nums.length;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
            count = new int[size];
            flip = new boolean[size];
            build(1, 0, n - 1, nums);
        }

        public int allCount() {
            return count[1];
        }

        public void update(int start, int end) {
            update(1, 0, n - 1, start, end);
        }

        private void update(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                apply(node, left, right);
                return;
            }
            spread(node, left, right);
            int mid = (left + right) / 2;
            if (start <= mid) {
                update(node * 2, left, mid, start, end);
            }
            if (end > mid) {
                update(node * 2 + 1, mid + 1, right, start, end);
            }
            maintain(node);
        }

        private int query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return count[node];
            }
            spread(node, left, right);
            int mid = (left + right) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end);
            }
            if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end);
            }
            int leftRes = query(node * 2, left, mid, start, end);
            int rightRes = query(node * 2 + 1, mid + 1, right, start, end);
            return leftRes + rightRes;
        }

        private void build(int node, int left, int right, int[] nums) {
            if (left == right) {
                count[node] = nums[left];
                return;
            }
            int mid = (left + right) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);
            maintain(node);
        }

        private void apply(int node, int left, int right) {
            count[node] = right - left + 1 - count[node];
            flip[node] = !flip[node];
        }

        private void spread(int node, int left, int right) {
            boolean toFlip = flip[node];
            if (!toFlip) {
                return;
            }
            int mid = (left + right) / 2;
            apply(node * 2, left, mid);
            apply(node * 2 + 1, mid + 1, right);
            flip[node] = false;
        }

        private void maintain(int node) {
            count[node] = count[node * 2] + count[node * 2 + 1];
        }
    }
}

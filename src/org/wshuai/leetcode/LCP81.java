package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2025.
 * #LCP81 https://leetcode.cn/problems/ryfUiz/
 */
public class LCP81 {

    // time O(n * k + m * k * log(n)), space O(n * k)
    public int getNandResult(int k, int[] arr, int[][] operations) {

        /**
         Treat NAND operation like each bit of number y pass through each bit in numbers in
         array, NAND has attribute that bit 0 turns input bit to 1 and bit 1 flip the input
         bit. Therefore, we only need to maintain the last index i in array that has each bit
         equals to 0 and use this to calculate how many 1 bit after this using n - 1 - i (and
         flip the bit based on even/odd of 1 bit count). The only special case is all bits are
         1 for this bit position for all numbers in the input array, in this case we set last
         index to -1. And we can just calculate the total number of 1 bit to x*n. Since we need
         to support single element update for input array, we can use segment tree.

         NAND operation example:

         k = 3
         arr = [1,4,6,3,4,3,6]
         operations = [[1,5,7],[1,7,4],[0,1,7],[0,5,1],[1,6,5]]

         1 -> 0 1 1 0 1 0 1 -> 0
         1 -> 0 0 1 1 0 1 1 -> 1
         1 -> 1 0 0 1 0 1 0 -> 1

         1 -> 0 1 1 0 1 0 1 -> 0
         0 -> 0 0 1 1 0 1 1 -> 1
         0 -> 1 0 0 1 0 1 0 -> 1

         1 -> 0 1 1 0 1 0 1 -> 0
         0 -> 0 1 1 1 0 0 1 -> 0
         1 -> 1 1 0 1 0 1 0 -> 1
         */

        int res = 0, n = arr.length, m = operations.length;
        SegmentTree st = new SegmentTree(arr, k); // O(n * k)
        for (int i = 0; i < m; i++) { // O(m)
            int[] op = operations[i];
            int type = op[0], x = op[1], y = op[2];
            if (type == 0) {
                st.update(x, y); // O(k * log(n))
            } else {
                int[] bits = st.queryAll();
                int ans = calc(y, x, n, k, bits); // O(k)
                res ^= ans;
            }
        }
        return res;
    }

    private int calc(int y, int x, int n, int k, int[] bits) {
        int res = 0;
        for (int i = 0; i < k; i++) {
            int b = 0, count = 0;
            if (bits[i] != -1) {
                count = n - 1 - bits[i];
                b = 1 - (count & 1);
            } else {
                b = ((y >> i) & 1);
                count = ((x * n) & 1);
                b = count == 1 ? 1 - b : b;
            }
            res += (b << i);
        }
        return res;
    }

    private static class SegmentTree {

        private final int n, k;
        private int[][] tree;

        public SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            tree = new int[2 << (32 - Integer.numberOfLeadingZeros(n))][k];
            build(1, 0, n - 1, nums);
        }

        public int[] queryAll() {
            return tree[1];
        }

        public void update(int index, int val) {
            update(1, 0, n - 1, index, val);
        }

        public void update(int node, int left, int right, int index, int val) {
            if (left == right) {
                tree[node] = buildBitsArray(val, left);
                return;
            }
            int mid = (left + right) / 2;
            if (index <= mid) {
                update(node * 2, left, mid, index, val);
            } else {
                update(node * 2 + 1, mid + 1, right, index, val);
            }
            maintain(node);
        }

        private void build(int node, int left, int right, int[] nums) {
            if (left == right) {
                tree[node] = buildBitsArray(nums[left], left);
                return;
            }
            int mid = (left + right) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);
            maintain(node);
        }

        private void maintain(int node) {
            for (int i = 0; i < k; i++) {
                tree[node][i] = Math.max(tree[node * 2][i], tree[node * 2 + 1][i]);
            }
        }

        private int[] buildBitsArray(int num, int idx) {
            int[] bits = new int[k];
            for (int i = 0; i < k; i++) {
                bits[i] = ((num >> i) & 1) == 1 ? -1 : idx;
            }
            return bits;
        }
    }
}

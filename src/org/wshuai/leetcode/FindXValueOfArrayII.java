package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2025.
 * #3525 https://leetcode.com/problems/find-x-value-of-array-ii/
 */
public class FindXValueOfArrayII {

    // time O(m * log(n)), space O(n)
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length, m = queries.length;
        int[] res = new int[m];
        SegmentTree st = new SegmentTree(nums, k);
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            st.update(q[0], q[1]);
            res[i] = st.rangeQuery(q[2], n - 1, q[3]);
        }
        return res;
    }

    private static class SegmentTree {

        private record Data(int mul, int[] freq) {}

        private final int n, k;
        private final Data[] tree;

        public SegmentTree(int[] nums, int k) {
            this.k = k;
            this.n = nums.length;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
            tree = new Data[size];
            build(1, 0, n - 1, nums);
        }

        public int rangeQuery(int start, int end, int x) {
            Data data = rangeQuery(1, 0, n - 1, start, end);
            return data.freq[x];
        }

        public void update(int index, int val) {
            update(1, 0, n - 1, index, val);
        }

        private Data rangeQuery(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node];
            }
            int mid = (left + right) / 2;
            if (end <= mid) {
                return rangeQuery(node * 2, left, mid, start, end);
            }
            if (start > mid) {
                return rangeQuery(node * 2 + 1, mid + 1, right, start, end);
            }
            Data leftRes = rangeQuery(node * 2, left, mid, start, end);
            Data rightRes = rangeQuery(node * 2 + 1, mid + 1, right, start, end);
            return mergeData(leftRes, rightRes);
        }

        private void update(int node, int left, int right, int index, int val) {
            if (left == right) {
                tree[node] = newData(val);
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
                tree[node] = newData(nums[left]);
                return;
            }
            int mid = (left + right) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);
            maintain(node);
        }

        private void maintain(int node) {
            tree[node] = mergeData(tree[node * 2], tree[node * 2 + 1]);
        }

        private Data mergeData(Data d1, Data d2) {
            int[] freq = d1.freq.clone();
            for (int r = 0; r < k; r++) {
                // https://leetcode.cn/problems/find-x-value-of-array-ii/solutions/3656585/xian-duan-shu-by-tsreaper-nuku/
                freq[d1.mul * r % k] += d2.freq[r];
            }
            return new Data(d1.mul * d2.mul % k, freq);
        }

        private Data newData(int num) {
            int mul = num % k;
            int[] freq = new int[k];
            freq[mul] = 1;
            return new Data(mul, freq);
        }
    }
}

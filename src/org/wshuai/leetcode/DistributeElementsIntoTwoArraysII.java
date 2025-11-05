package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/05/2025.
 * #3072 https://leetcode.com/problems/distribute-elements-into-two-arrays-ii/
 */
public class DistributeElementsIntoTwoArraysII {

    // time O(n * log(m)), space O(n + m)
    public int[] resultArrayBinaryIndexedTree(int[] nums) {
        // BIT + Discretization
        int n = nums.length;
        List<Integer> arr1 = new ArrayList<>(), arr2 = new ArrayList<>();
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray(); // O(m*log(m))
        int m = sorted.length;
        BIT bit = new BIT(m + 1);
        arr1.add(nums[0]);
        bit.update(binarySearch(sorted, nums[0]) + 1, 0); // O(log(m))
        arr2.add(nums[1]);
        bit.update(binarySearch(sorted, nums[1]) + 1, 1); // O(log(m))
        for (int i = 2; i < n; i++) {
            int index = binarySearch(sorted, nums[i] + 1);
            int count1 = arr1.size() - bit.query(index, 0);
            int count2 = arr2.size() - bit.query(index, 1);
            int indexToInsert = binarySearch(sorted, nums[i]) + 1;
            if (count2 > count1 || (count2 == count1 && arr1.size() > arr2.size())) {
                arr2.add(nums[i]);
                bit.update(indexToInsert, 1);
            } else {
                arr1.add(nums[i]);
                bit.update(indexToInsert, 0);
            }
        }
        int[] res = new int[n];
        int i = 0;
        for (int num : arr1) {
            res[i++] = num;
        }
        for (int num : arr2) {
            res[i++] = num;
        }
        return res;
    }

    // time O(n * log(m)), space O(n + m)
    public int[] resultArraySegmentTree(int[] nums) {
        // Segment Tree + Discretization
        int n = nums.length;
        List<Integer> arr1 = new ArrayList<>(), arr2 = new ArrayList<>();
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray(); // O(m*log(m))
        int m = sorted.length;
        SegmentTree st = new SegmentTree(m);
        arr1.add(nums[0]);
        st.update(binarySearch(sorted, nums[0]), 0); // O(log(m))
        arr2.add(nums[1]);
        st.update(binarySearch(sorted, nums[1]), 1); // O(log(m))
        for (int i = 2; i < n; i++) { // O(n)
            int index = binarySearch(sorted, nums[i] + 1); // O(log(m))
            // For segment tree, query index needs to be in [0, m - 1] so we need to do
            // boundary check.
            int count1 = index == m ? 0 : st.query(index, m - 1, 0); // O(log(m))
            int count2 = index == m ? 0 : st.query(index, m - 1, 1); // O(log(m))
            int indexToInsert = binarySearch(sorted, nums[i]); // O(log(m))
            if (count2 > count1 || (count2 == count1 && arr1.size() > arr2.size())) {
                arr2.add(nums[i]);
                st.update(indexToInsert, 1); // O(log(m))
            } else {
                arr1.add(nums[i]);
                st.update(indexToInsert, 0); // O(log(m))
            }
        }
        int[] res = new int[n];
        int i = 0;
        for (int num : arr1) {
            res[i++] = num;
        }
        for (int num : arr2) {
            res[i++] = num;
        }
        return res;
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static class BIT {

        private int[][] tree;

        public BIT(int n) {
            tree = new int[n + 1][2];
        }

        public void update(int index, int i) {
            while (index < tree.length) {
                tree[index][i]++;
                index += index & -index;
            }
        }

        public int query(int index, int i) {
            int res = 0;
            while (index > 0) {
                res += tree[index][i];
                index -= index & -index;
            }
            return res;
        }
    }

    private static class SegmentTree {

        private int n;
        private int[][] tree;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n][2];
        }

        public void update(int index, int i) {
            update(1, 0, n - 1, index, i);
        }

        private void update(int node, int left, int right, int index, int i) {
            if (left == right) {
                tree[node][i]++;
                return;
            }
            int mid = (left + right) / 2;
            if (index <= mid) {
                update(node * 2, left, mid, index, i);
            } else {
                update(node * 2 + 1, mid + 1, right, index, i);
            }
            maintain(node, i);
        }

        public int query(int start, int end, int i) {
            return query(1, 0, n - 1, start, end, i);
        }

        private int query(int node, int left, int right, int start, int end, int i) {
            if (left >= start && right <= end) {
                return tree[node][i];
            }
            int mid = (left + right) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end, i);
            }
            if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end, i);
            }
            int leftRes = query(node * 2, left, mid, start, end, i);
            int rightRes = query(node * 2 + 1, mid + 1, right, start, end, i);
            return leftRes + rightRes;
        }

        private void maintain(int node, int i) {
            tree[node][i] = tree[node * 2][i] + tree[node * 2 + 1][i];
        }
    }
}

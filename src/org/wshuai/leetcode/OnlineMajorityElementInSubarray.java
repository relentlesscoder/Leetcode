package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/14/2019.
 * #1157 https://leetcode.com/problems/online-majority-element-in-subarray/
 */
public class OnlineMajorityElementInSubarray {

    private class MajorityCheckerSegmentTree {

        private final SegmentTree st;
        private final Map<Integer, List<Integer>> indexMap;

        // time O(n), space O(n)
        public MajorityCheckerSegmentTree(int[] arr) {
            st = new SegmentTree(arr);
            indexMap = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                indexMap.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
            }
        }

        // time O(log(n)), space O(1)
        public int query(int left, int right, int threshold) {
            int[] ans = st.query(left, right); // log(n)
            if (ans[1] <= 0) {
                return -1;
            }
            List<Integer> pos = indexMap.get(ans[0]);
            int cnt = binarySearch(pos, right + 1) - binarySearch(pos, left); // O(log(n))
            if (cnt >= threshold) {
                return ans[0];
            }
            return -1;
        }

        private int binarySearch(List<Integer> list, int target) {
            int low = 0, high = list.size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (list.get(mid) < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        private static class SegmentTree {

            private final int n;
            private final int[][] tree;

            public SegmentTree(int[] nums) {
                this.n = nums.length;
                tree = new int[2 << (32 - Integer.numberOfLeadingZeros(n))][2];
                build(1, 0, n - 1, nums);
            }

            public int[] query(int start, int end) {
                return query(1, 0, n - 1, start, end);
            }

            private int[] query(int node, int left, int right, int start, int end) {
                if (left >= start && right <= end) {
                    return tree[node];
                }
                int mid = (left + right) / 2;
                if (end <= mid) {
                    return query(node * 2, left, mid, start, end);
                }
                if (start > mid) {
                    return query(node * 2 + 1, mid + 1, right, start, end);
                }
                int[] leftRes = query(node * 2, left, mid, start, end);
                int[] rightRes = query(node * 2 + 1, mid + 1, right, start, end);
                return merge(leftRes, rightRes);
            }

            private void build(int node, int left, int right, int[] nums) {
                if (left == right) {
                    tree[node] = new int[]{nums[left], 1};
                    return;
                }
                int mid = (left + right) / 2;
                build(node * 2, left, mid, nums);
                build(node * 2 + 1, mid + 1, right, nums);
                maintain(node);
            }

            private void maintain(int node) {
                tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
            }

            private int[] merge(int[] l, int[] r) {
                int[] arr = new int[2];
                if (l[0] == r[0]) {
                    arr[0] = l[0];
                    arr[1] = l[1] + r[1];
                } else if (l[1] > r[1]) {
                    arr[0] = l[0];
                    arr[1] = l[1] - r[1];
                } else {
                    arr[0] = r[0];
                    arr[1] = r[1] - l[1];
                }
                return arr;
            }
        }
    }

    private class MajorityCheckerRandomPicker {

        private final int[] arr;
        private final Map<Integer, List<Integer>> idxs;

		// time O(n), space O(n)
        public MajorityCheckerRandomPicker(int[] arr) {
            this.arr = arr;
            idxs = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                idxs.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
            }
        }

		// time O(log(n)), space O(1)
        public int query(int left, int right, int threshold) {
			// https://leetcode.com/problems/online-majority-element-in-subarray/solutions/355848/python-binary-search-find-the-majority-e-ib7i/
            for (int i = 0; i < 20; i++) {
                int rand = left + (new Random().nextInt(right - left + 1)); // [0, right - left + 1)
                int num = arr[rand];
                List<Integer> pos = idxs.get(num);
                int cnt = binarySearch(pos, right + 1) - binarySearch(pos, left);
                if (cnt >= threshold) {
                    return num;
                }
            }
            return -1;
        }

        private int binarySearch(List<Integer> list, int target) {
            int low = 0, high = list.size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (list.get(mid) < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }
}

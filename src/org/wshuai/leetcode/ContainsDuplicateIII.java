package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Wei on 10/10/2016.
 * #0220 https://leetcode.com/problems/contains-duplicate-iii/
 */
public class ContainsDuplicateIII {

    // time O(n * log(n)), space O(n)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
            list.add(num + valueDiff);
            list.add(num - valueDiff);
        }
        int[] sorted = list.stream().mapToInt(Integer::intValue).distinct().sorted().toArray();
        int m = sorted.length;
        SegmentTree st = new SegmentTree(m + 1);
        for (int i = 0; i < n; i++) {
            if (i > indexDiff) {
                int indexToRemove = binarySearch(sorted, nums[i - indexDiff - 1]);
                st.update(indexToRemove, indexToRemove, -1);
            }
            int cnt = st.query(binarySearch(sorted, nums[i] - valueDiff), binarySearch(sorted, nums[i] + valueDiff));
            if (cnt > 0) {
                return true;
            }
            int indexToAdd = binarySearch(sorted, nums[i]);
            st.update(indexToAdd, indexToAdd, 1);
        }
        return false;
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

    private class SegmentTree {

        private final int n;
        private final int[] tree;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        }

        public int query(int start, int end) {
            return query(1, 0, n - 1, start, end);
        }

        public void update(int start, int end, int val) {
            update(1, 0, n - 1, start, end, val);
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
            int lr = query(node * 2, left, mid, start, end);
            int rr = query(node * 2 + 1, mid + 1, right, start, end);
            return lr + rr;
        }

        private void update(int node, int left, int right, int start, int end, int val) {
            if (left >= start && right <= end) {
                tree[node] += val;
                return;
            }
            int mid = left + (right - left) / 2;
            if (start <= mid) {
                update(node * 2, left, mid, start, end, val);
            }
            if (end > mid) {
                update(node * 2 + 1, mid + 1, right, start, end, val);
            }
            maintain(node);
        }

        private void maintain(int node) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    // time O(n * log(n)), space O(n * log(n))
    public boolean containsNearbyAlmostDuplicateDynamicSegmentTree(
            int[] nums, int indexDiff, int valueDiff
    ) {
        int n = nums.length;
        DynamicSegmentTree st = new DynamicSegmentTree();
        for (int i = 0; i < n; i++) {
            if (i > indexDiff) {
                int indexToRemove = i - indexDiff - 1;
                st.update(nums[indexToRemove], nums[indexToRemove], -1);
            }
            int cnt = st.query(nums[i] - valueDiff, nums[i] + valueDiff);
            if (cnt > 0) {
                return true;
            }
            st.update(nums[i], nums[i], 1);
        }
        return false;
    }

    private static class DynamicSegmentTree {

        private static final int MAX = (int) 1e9;
        private final Node root;

        public DynamicSegmentTree() {
            root = new Node();
        }

        public int query(int start, int end) {
            return query(root, -MAX, MAX, start, end);
        }

        public void update(int start, int end, int val) {
            update(root, -MAX, MAX, start, end, val);
        }

        private int query(Node node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return node.val;
            }
            spread(node);
            int mid = left + (right - left) / 2;
            if (end <= mid) {
                return query(node.left, left, mid, start, end);
            }
            if (start > mid) {
                return query(node.right, mid + 1, right, start, end);
            }
            int lr = query(node.left, left, mid, start, end);
            int rr = query(node.right, mid + 1, right, start, end);
            return lr + rr;
        }

        private void update(Node node, int left, int right, int start, int end, int val) {
            if (left >= start && right <= end) {
                node.val += val;
                node.mark += val;
                return;
            }
            spread(node);
            int mid = left + (right - left) / 2;
            if (start <= mid) {
                update(node.left, left, mid, start, end, val);
            }
            if (end > mid) {
                update(node.right, mid + 1, right, start, end, val);
            }
            maintain(node);
        }

        private void maintain(Node node) {
            node.val = node.left.val + node.right.val;
        }

        private void spread(Node node) {
            if (node.left == null) {
                node.left = new Node();
            }
            if (node.right == null) {
                node.right = new Node();
            }
            if (node.mark == 0) {
                return;
            }
            node.left.val += node.mark;
            node.right.val += node.mark;
            node.left.mark += node.mark;
            node.right.mark += node.mark;
            node.mark = 0;
        }

        private class Node {
            Node left;
            Node right;
            int val;
            int mark;

            public Node() {
                val = 0;
                mark = 0;
            }
        }
    }

    // time O(n * log(n)), space O(n)
	public boolean containsNearbyAlmostDuplicateTreeSet(int[] nums, int indexDiff, int valueDiff) {
		int n = nums.length;
		TreeSet<Long> set = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			long val = (long) nums[i];
			Long floor = set.floor(val + valueDiff);
			if (floor != null && floor >= val - valueDiff) {
				return true;
			}
			set.add(val);
			if (set.size() > indexDiff) {
				set.remove((long) nums[i - indexDiff]);
			}
		}
		return false;
	}
}

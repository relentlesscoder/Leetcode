package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 02/04/2024.
 * #2502 https://leetcode.com/problems/design-memory-allocator/
 */
public class DesignMemoryAllocator {

    private class AllocatorSegmentTree {

        private final int n;
        private final SegmentTree st;
        private final Map<Integer, List<int[]>> blocks;

        // time O(n), space O(n)
        public AllocatorSegmentTree(int n) {
            this.n = n;
            this.st = new SegmentTree(n);
            this.blocks = new HashMap<>();
        }

        // time O(log(n)), space O(1)
        public int allocate(int size, int mID) {
            int i = st.findFirst(size);
            if (i < 0) {
                return -1;
            }
            blocks.computeIfAbsent(mID, k -> new ArrayList<>()).add(new int[]{i, i + size - 1});
            st.update(i, i + size - 1, 1);
            return i;
        }

        // time O(log(n)), space O(1)
        public int freeMemory(int mID) {
            int res = 0;
            List<int[]> list = blocks.get(mID);
            if (list != null) {
                for (int[] range : list) {
                    res += range[1] - range[0] + 1;
                    st.update(range[0], range[1], 0);
                }
                blocks.remove(mID);
            }
            return res;
        }

        private static class SegmentTree {

            private final int n;
            private final int[] pre;
            private final int[] suf;
            private final int[] max;
            private final int[] mark;

            public SegmentTree(int n) {
                this.n = n;
                int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
                pre = new int[size];
                suf = new int[size];
                max = new int[size];
                mark = new int[size];
                build(1, 0, n - 1);
            }

            public void update(int start, int end, int val) {
                update(1, 0, n - 1, start, end, val);
            }

            private void update(int node, int left, int right, int start, int end, int val) {
                if (left >= start && right <= end) {
                    apply(node, left, right, val);
                    return;
                }
                spread(node, left, right);
                int mid = left + (right - left) / 2;
                int leftNode = node * 2;
                int rightNode = node * 2 + 1;
                if (start <= mid) {
                    update(leftNode, left, mid, start, end, val);
                }
                if (end > mid) {
                    update(rightNode, mid + 1, right, start, end, val);
                }
                // Merge values from child nodes
                pre[node] = pre[leftNode];
                if (pre[leftNode] == mid - left + 1) {
                    pre[node] += pre[rightNode];
                }
                suf[node] = suf[rightNode];
                if (suf[rightNode] == right - mid) {
                    suf[node] += suf[leftNode];
                }
                max[node] = Math.max(Math.max(max[leftNode], max[rightNode]), suf[leftNode] + pre[rightNode]);
            }

            public int findFirst(int size) {
                return findFirst(1, 0, n - 1, size);
            }

            private int findFirst(int node, int left, int right, int size) {
                if (max[node] < size) {
                    return -1;
                }
                if (left == right) {
                    return left;
                }
                spread(node, left, right);
                int mid = left + (right - left) / 2;
                int leftNode = node * 2;
                int rightNode = node * 2 + 1;
                // Since we need to find the leftmost empty blocks
                // Find in left subtree first
                int idx = findFirst(leftNode, left, mid, size);
                if (idx < 0) {
                    // Check if blocks can be found in middle
                    if (suf[leftNode] + pre[rightNode] >= size) {
                        return mid - suf[leftNode] + 1;
                    }
                    // Otherwise find in right subtree
                    idx = findFirst(rightNode, mid + 1, right, size);
                }
                return idx;
            }

            private void build(int node, int left, int right) {
                apply(node, left, right, -1);
                if (left == right) {
                    return;
                }
                int mid = left + (right - left) / 2;
                build(node * 2, left, mid);
                build(node * 2 + 1, mid + 1, right);
            }

            private void apply(int node, int left, int right, int val) {
                int size = val <= 0 ? right - left + 1 : 0;
                pre[node] = suf[node] = max[node] = size;
                mark[node] = val;
            }

            private void spread(int node, int left, int right) {
                int act = mark[node];
                if (act == -1) {
                    return;
                }
                int mid = left + (right - left) / 2;
                apply(node * 2, left, mid, act);
                apply(node * 2 + 1, mid + 1, right, act);
                mark[node] = -1;
            }
        }
    }
}

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator obj = new Allocator(n);
 * int param_1 = obj.allocate(size,mID);
 * int param_2 = obj.free(mID);
 */

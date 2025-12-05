package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/09/2025.
 * #2940 https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/
 */
public class FindBuildingWhereAliceAndBobCanMeet {

    // time O(n + m * log(n)), space O(n + m)
    public int[] leftmostBuildingQueriesSegmentTree(int[] heights, int[][] queries) {
        int n = heights.length, m = queries.length;
        int[] res = new int[m];
        Arrays.fill(res, -1);
        List<int[]>[] queryList = new ArrayList[n];
        Arrays.setAll(queryList, index -> new ArrayList<>());

        for (int i = 0; i < m; i++) { // O(m)
            int a = queries[i][0], b = queries[i][1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == b || heights[a] < heights[b]) {
                res[i] = b;
            } else {
                queryList[b].add(new int[]{heights[a], i});
            }
        }
        SegmentTree st = new SegmentTree(n);
        for (int i = n - 1; i >= 0; i--) { // O(n)
            for (int[] q : queryList[i]) {
                int h = q[0], idx = q[1];
                res[idx] = st.findFirst(h); // O(log(n))
            }
            st.update(i, heights[i]); // O(log(n))
        }
        return res;
    }

    private static class SegmentTree {

        private final int n;
        private final int[] tree;

        public SegmentTree(int[] nums) {
            n = nums.length;
            tree = new int[4 * n];
            build(nums, 1, 0, n - 1);
        }

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
            Arrays.fill(tree, -1);
        }

        public int findFirst(int val) {
            return findFirst(1, 0, n - 1, val);
        }

        private int findFirst(int node, int left, int right, int val) {
            if (tree[node] <= val) {
                return -1;
            }
            if (left == right) {
                return left;
            }
            int mid = (left + right) / 2;
            int res = findFirst(node * 2, left, mid, val);
            if (res == -1) {
                res = findFirst(node * 2 + 1, mid + 1, right, val);
            }
            return res;
        }

        public void update(int index, int val) {
            update(1, 0, n - 1, index, val);
        }

        private void update(int node, int left, int right, int index, int val) {
            if (left == right) {
                tree[node] = val;
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

        private void build(int[] nums, int node, int left, int right) {
            if (left == right) {
                tree[node] = nums[left];
                return;
            }
            int mid = (left + right) / 2;
            build(nums, node * 2, left, mid);
            build(nums, node * 2 + 1, mid + 1, right);
            maintain(node);
        }

        private void maintain(int node) {
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    // time O(n + m * log(m)), O(n + m)
    public int[] leftmostBuildingQueriesPriorityQueue(int[] heights, int[][] queries) {
        int n = heights.length, m = queries.length;
        int[] res = new int[m];
        Arrays.fill(res, -1);
        List<int[]>[] queryList = new ArrayList[n];
        Arrays.setAll(queryList, index -> new ArrayList<>());

        // For queries [a, b], there are 3 different cases:
        //   1. a == b: answer is a
        //   2. a < b and heights[a] < heights[b]: answer is b since a can move to b
        //   3. a < b and heights[a] >= heights[b]: then answer is the next index i that satisfies
        //      i > b and heights[i] > heights[a]. Since heights[a] >= heights[b], we know
        //      heights[i] > heights[a] >= heights[b]
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            if (a > b) { // make sure a <= b
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == b || heights[a] < heights[b]) {
                res[i] = b;
            } else {
                queryList[b].add(new int[]{heights[a], i});
            }
        }

        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            while (!minQueue.isEmpty() && minQueue.peek()[0] < heights[i]) {
                res[minQueue.poll()[1]] = i;
            }
            // after index b is processed, we add all it's queries to the min queue
            for (int[] q : queryList[i]) {
                minQueue.offer(q);
            }
        }
        return res;
    }

    // time O(n + m * log(n)), space O(n + m)
    public int[] leftmostBuildingQueriesMonotonicQueueAndBinarySearch(int[] heights, int[][] queries) {
        int n = heights.length, m = queries.length;
        int[] res = new int[m];
        Arrays.fill(res, -1);
        List<int[]>[] queryList = new ArrayList[n];
        Arrays.setAll(queryList, index -> new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == b || heights[a] < heights[b]) {
                res[i] = b;
            } else {
                queryList[b].add(new int[]{heights[a], i});
            }
        }
        // Maintain a monotonically increasing stack
        int[] stack = new int[n];
        int top = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int[] q : queryList[i]) {
                // For each query find next index on right that is greater
                res[q[1]] = binarySearch(heights, stack, top, q[0]);
            }
            // Remove all top elements that are less or equals than/to the current heights
            // because if we have a heights[i] it makes no sense to jump to a lower building
            // on right. Therefore, we maintain a monotonically increasing stack to store all
            // possible buildings on right Alice/Bob can move to.
            // For Input heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
            // when i = 5,
            // query: qs[5] -> [3,2]
            // stack:
            //   [6(7),4(6)] top = 2
            // res[2] (query [3,5], value [2,1]) = 6
            // res[3] (query [2,5], value [8,1]) = -1
            // when i = 4
            // stack:
            //   [6(4)] top = 1 - note here we remove all elements that are <= 6
            // when i = 3
            // query: qs[3] -> [0]
            // stack:
            //   [6(4)] top = 1
            // res[3] (query [0,3], value [5,2]) = 4
            while (top > 0 && heights[i] >= heights[stack[top - 1]]) {
                top--;
            }
            // Push current building to the top of the stack
            stack[top++] = i;
        }
        return res;
    }

    private int binarySearch(int[] heights, int[] stack, int length, int val) {
        if (length == 0) {
            return -1;
        }
        int left = 0, right = length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (heights[stack[mid]] > val) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return heights[stack[left]] <= val ? -1 : stack[left];
    }
}

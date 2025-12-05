package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/13/2025.
 * #2589 https://leetcode.com/problems/minimum-time-to-complete-all-tasks/
 */
public class MinimumTimeToCompleteAllTasks {

    // time O(n * log(n)), space O(n)
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        List<int[]> stack = new ArrayList<>();
        stack.add(new int[] {-2, -2, 0});
        for (int[] task : tasks) {
            int start = task[0], end = task[1], duration = task[2];
            int[] running = stack.get(binarySearch(stack, start) - 1);
            duration -= stack.get(stack.size() - 1)[2] - running[2];
            if (start <= running[1]) {
                duration -= running[1] - start + 1;
            }
            if (duration <= 0) {
                continue;
            }
            while (end - stack.get(stack.size() - 1)[1] <= duration) {
                running = stack.remove(stack.size() - 1);
                duration += running[1] - running[0] + 1;
            }
            stack.add(new int[]{end - duration + 1, end, stack.get(stack.size() - 1)[2] + duration});
        }
        return stack.get(stack.size() - 1)[2];
    }

    private int binarySearch(List<int[]> nums, int target) {
        int left = 0, right = nums.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid)[0] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // time O(n * log(n) + n * log(m)), space O(m)
    public int findMinimumTimeSegmentTree(int[][] tasks) {
        // To optimize run time of below brutal force solution, we will need
        // a data structure that can query total running times in a range and
        // also add the new times to run in a range thus we can use segment
        // tree with lazy propagation.
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int n = tasks.length, m = tasks[n - 1][1];
        SegmentTree st = new SegmentTree(m);
        for (int i = 0; i < n; i++) {
            int start = tasks[i][0], end = tasks[i][1], duration = tasks[i][2];
            st.toRun = duration - st.query(start, end); // Remove running times
            if (st.toRun > 0) {
                st.update(start, end); // Add new time to run
            }
        }
        return st.getAll();
    }

    // time O(n * log(n) + n * t), space O(m)
    public int findMinimumTimeBrutalForce(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]); // O(n * log(n))
        int res = 0, n = tasks.length, m = tasks[n - 1][1];
        boolean[] run = new boolean[m + 1];
        for (int i = 0; i < n; i++) { // O(n)
            int start = tasks[i][0], end = tasks[i][1], duration = tasks[i][2];
            for (int j = start; j <= end; j++) { // O(t)
                if (run[j]) {
                    duration--;
                }
            }
            for (int j = end; duration > 0; j--) {
                if (!run[j]) {
                    run[j] = true;
                    duration--;
                    res++;
                }
            }
        }
        return res;
    }

    private static class SegmentTree {

        private int n;
        private int[] tree;
        private boolean[] marks;
        private int toRun;

        public SegmentTree(int n) {
            this.n = n;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n - 1));
            tree = new int[size];
            marks = new boolean[size];
            toRun = 0;
        }

        public int getAll() {
            return tree[1];
        }

        public void update(int start, int end) {
            update(1, 1, n, start, end);
        }

        public int query(int start, int end) {
            return query(1, 1, n, start, end);
        }

        private void update(int node, int left, int right, int start, int end) {
            int size = right - left + 1;
            if (toRun == 0 || tree[node] == size) {
                return;
            }
            if (left >= start && right <= end && size - tree[node] <= toRun) {
                toRun -= size - tree[node];
                apply(node, left, right);
                return;
            }
            spread(node, left, right);
            int mid = (left + right) / 2;
            if (end > mid) {
                update(node * 2 + 1, mid + 1, right, start, end);
            }
            if (start <= mid) {
                update(node * 2, left, mid, start, end);
            }
            maintain(node);
        }

        private int query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node];
            }
            spread(node, left, right);
            int mid = (left + right) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end);
            }
            if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end);
            }
            int lr = query(node * 2, left, mid, start, end);
            int rr = query(node * 2 + 1, mid + 1, right, start, end);
            return merge(lr, rr);
        }

        private void spread(int node, int left, int right) {
            if (!marks[node]) {
                return;
            }
            int mid = (left + right) / 2;
            apply(node * 2, left, mid);
            apply(node * 2 + 1, mid + 1, right);
            marks[node] = false;
        }

        private void apply(int node, int left, int right) {
            tree[node] = right - left + 1;
            marks[node] = true;
        }

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private int merge(int v1, int v2) {
            return v1 + v2;
        }
    }
}

package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/13/2025.
 * #3454 https://leetcode.com/problems/separate-squares-ii/
 */
public class SeparateSquaresII {

    // time O(n * log(n)), space O(n)
    public double separateSquares(int[][] squares) {
        // https://leetcode.cn/problems/separate-squares-ii/solutions/3078402/lazy-xian-duan-shu-sao-miao-xian-pythonj-eeqk/
        int n = squares.length * 2;
        int[] xs = new int[n];
        Event[] events = new Event[n];
        int idx = 0;
        for (int[] sq : squares) {
            int y = sq[1];
            int l = sq[2];
            int lx = sq[0]; // Left x coordinate
            int rx = lx + l; // Right x coordinate
            xs[idx] = lx;
            xs[idx + 1] = rx;
            // Horizontal lines
            events[idx++] = new Event(y, lx, rx, 1);
            events[idx++] = new Event(y + l, lx, rx, -1);
        }

        // Sort for discretization
        Arrays.sort(xs);
        SegmentTree st = new SegmentTree(xs);
        // Sweep lines bottom up
        Arrays.sort(events, (a, b) -> Double.compare(a.y, b.y));

        Record[] records = new Record[n - 1];
        long totalArea = 0;
        for (int i = 0; i < n - 1; i++) {
            Event e = events[i];
            int lidx = binarySearch(xs, e.lx);
            int ridx = binarySearch(xs, e.rx) - 1;
            // Range update for [lx, rx];
            st.update(lidx, ridx, e.delta);
            // All covered length
            int sumLen = xs[n - 1] - xs[0] - st.getUncoveredLength();
            records[i] = new Record(totalArea, sumLen);
            // Calculate the area
            totalArea += 1L * sumLen * (events[i + 1].y - e.y);
        }

        int i = 0;
        while (i < n - 1 && records[i].area * 2 < totalArea) {
            i++;
        }
        i--;
        return events[i].y + (totalArea - records[i].area * 2) / (records[i].sumLen * 2.0);
    }

    private record Event(int y, int lx, int rx, int delta) {
    }

    private record Record(long area, int sumLen) {
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

    private static class SegmentTree {

        private final int n;
        private final int[] minCoverLen; // Sum of length for lines with minimum covered times in the range
        private final int[] minCover; // Minimum covered times in the range
        private final int[] mark; // Amount to increase for minCover in the range

        public SegmentTree(int[] xs) {
            n = xs.length - 1;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n - 1));
            minCoverLen = new int[size];
            minCover = new int[size];
            mark = new int[size];
            build(1, 0, n - 1, xs);
        }

        public int getUncoveredLength() {
            // minCover[1] == 0 means the entire range [minX, maxX] are covered
            // by at least one rectangle so return 0. Otherwise, return minCoverLen[1]
            // for minCover[1] == 0
            return minCover[1] == 0 ? minCoverLen[1] : 0;
        }

        public void update(int start, int end, int val) {
            update(1, 0, n - 1, start, end, val);
        }

        private void update(int node, int left, int right, int start, int end, int val) {
            if (left >= start && right <= end) {
                apply(node, val);
                return;
            }
            spread(node);
            int mid = left + (right - left) / 2;
            if (start <= mid) {
                update(node * 2, left, mid, start, end, val);
            }
            if (end > mid) {
                update(node * 2 + 1, mid + 1, right, start, end, val);
            }
            maintain(node);
        }

        private void build(int node, int left, int right, int[] xs) {
            if (left == right) {
                minCoverLen[node] = xs[left + 1] - xs[left];
                return;
            }
            int mid = left + (right - left) / 2;
            build(node * 2, left, mid, xs);
            build(node * 2 + 1, mid + 1, right, xs);
            maintain(node);
        }

        private void maintain(int node) {
            // We only care about minCover[node] and its minCoverLen[node]
            int min = Math.min(minCover[node * 2], minCover[node * 2 + 1]);
            minCover[node] = min;
            minCoverLen[node] = (minCover[node * 2] == min ? minCoverLen[node * 2] : 0) +
                    (minCover[node * 2 + 1] == min ? minCoverLen[node * 2 + 1] : 0);
        }

        private void spread(int node) {
            if (mark[node] == 0) {
                return;
            }
            apply(node * 2, mark[node]);
            apply(node * 2 + 1, mark[node]);
            mark[node] = 0;
        }

        private void apply(int node, int val) {
            minCover[node] += val;
            mark[node] += val;
        }
    }
}

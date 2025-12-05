package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/15/2019.
 * #0850 https://leetcode.com/problems/rectangle-area-ii/
 */
public class RectangleAreaII {

    private static final int MOD = (int)1e9 + 7;

    private record Event(int y, int lx, int rx, int delta) {
    }

    // time O(n * log(n)), space O(n)
    public int rectangleAreaSegmentTree(int[][] rectangles) {
        long res = 0;
        int n = rectangles.length * 2;
        int[] xs = new int[n];
        Event[] events = new Event[n];
        int idx = 0;
        for (int[] rect : rectangles) {
            int lx = rect[0]; // Left x coordinate
            int rx = rect[2]; // Right x coordinate
            xs[idx] = lx;
            xs[idx + 1] = rx;
            // Horizontal lines
            events[idx++] = new Event(rect[1], lx, rx, 1);
            events[idx++] = new Event(rect[3], lx, rx, -1);
        }

        // Sort for discretization
        Arrays.sort(xs);
        SegmentTree st = new SegmentTree(xs);
        // Sweep lines bottom up
        Arrays.sort(events, (a, b) -> a.y - b.y);
        for (int i = 0; i < n - 1; i++) {
            Event e = events[i];
            int lidx = binarySearch(xs, e.lx);
            int ridx = binarySearch(xs, e.rx) - 1;
            // Range update for [lx, rx];
            st.update(lidx, ridx, e.delta);
            // All covered length
            int sumLen = xs[n - 1] - xs[0] - st.getUncoveredLength();
            // Calculate the area
            res += 1L * sumLen * (events[i + 1].y - e.y);
        }
        return (int) (res % MOD);
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

    // time O(n^2 * log(n)), space O(n)
    public int rectangleAreaLineSweep(int[][] rectangles) {
        // https://leetcode.cn/problems/rectangle-area-ii/solutions/1826992/gong-shui-san-xie-by-ac_oier-9r36/
        long res = 0;
        List<Integer> vLines = new ArrayList<>();
        for (int[] rec : rectangles) { // O(n)
            vLines.add(rec[0]);
            vLines.add(rec[2]);
        }
        Collections.sort(vLines); // O(n * log(n))
        for (int i = 1; i < vLines.size(); i++) { // O(n)
            int right = vLines.get(i), left = vLines.get(i - 1), length = right - left;
            if (length == 0) {
                continue;
            }
            List<int[]> hLines = new ArrayList<>();
            for (int[] rec : rectangles) { // O(n)
                if (rec[0] <= left && rec[2] >= right) {
                    hLines.add(new int[] {rec[1], rec[3]});
                }
            }
            Collections.sort(hLines, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]); // O(n * log(n))
            long total = 0;
            int low = -1, high = -1;
            for (int[] curr : hLines) { // O(n)
                if (curr[0] > high) {
                    total += high - low;
                    low = curr[0];
                    high = curr[1];
                } else if (curr[1] > high) {
                    high = curr[1];
                }
            }
            total += high - low;
            res += total * length;
            res %= MOD;
        }
        return (int) res;
    }
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2025.
 * #2286 https://leetcode.com/problems/booking-concert-tickets-in-groups/
 */
public class BookingConcertTicketsInGroups {

    private class BookMyShow {

        private final int n;
        private final int m;
        private final SegmentTree st;

        // time O(n), space O(n)
        public BookMyShow(int n, int m) {
            this.n = n;
            this.m = m;
            this.st = new SegmentTree(n);
        }

        // time O(log(n)), space O(1)
        public int[] gather(int k, int maxRow) {
            // Find first row in rows in range [0,maxRow] with
            // occupied seats <= m - k (empty seats >= k)
            int row = st.findFirst(1, 0, n - 1, maxRow, m - k);
            // Row not found
            if (row < 0) {
                return new int[0];
            }
            // Find sum in this row, since we always allocate
            // from the leftmost empty seat sum is also the
            // index of the first empty seat
            int col = (int) st.querySum(1, 0, n - 1, row, row);
            // Take the k seats
            st.update(1, 0, n - 1, row, k);
            return new int[] {row, col};
        }

        // time O(log(n)), space O(1)
        public boolean scatter(int k, int maxRow) {
            // Find total number of occupied seats in rows in
            // range [0,maxRow]
            long sum = st.querySum(1, 0, n - 1, 0, maxRow);
            // Not enough empty seats found
            if (sum > (long) m * (maxRow + 1) - k) {
                return false;
            }
            // Find first row that has at least 1 empty seats
            int row = st.findFirst(1, 0, n - 1, maxRow, m - 1);
            // Allocate empty seats greedily
            while (k > 0) {
                // Find number of empty seats in current row
                int left = Math.min(m - (int) st.querySum(1, 0, n - 1, row, row), k);
                // Take the seats
                st.update(1, 0, n - 1, row, left);
                // Update k
                k -= left;
                // Go to the next row
                row++;
            }
            return true;
        }

        private static class SegmentTree {

            private final int n;
            private final int[] minST;
            private final long[] sumST;

            public SegmentTree(int n) {
                this.n = n;
                int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
                // minST[i] is the minimum value in range [0,i]
                minST = new int[size];
                // sumST[i] is the sum in range [0,i]
                sumST = new long[size];
            }

            // Increment the value at index by val
            public void update(int node, int left, int right, int index, int val) {
                if (left == right) {
                    minST[node] += val;
                    sumST[node] += val;
                    return;
                }
                int mid = (left + right) / 2;
                if (index <= mid) {
                    update(node * 2, left, mid, index, val);
                } else {
                    update(node * 2 + 1, mid + 1, right, index, val);
                }
                minST[node] = Math.min(minST[node * 2], minST[node * 2 + 1]);
                sumST[node] = sumST[node * 2] + sumST[node * 2 + 1];
            }

            // Return sum in range [start, end]
            public long querySum(int node, int left, int right, int start, int end) {
                if (left >= start && right <= end) {
                    return sumST[node];
                }
                int mid = (left + right) / 2;
                if (end <= mid) {
                    return querySum(node * 2, left, mid, start, end);
                } else if (start > mid) {
                    return querySum(node * 2 + 1, mid + 1, right, start, end);
                }
                long leftSum = querySum(node * 2, left, mid, start, end);
                long rightSum = querySum(node * 2 + 1, mid + 1, right, start, end);
                return leftSum + rightSum;
            }

            // Find rightmost index in [0,end] with min <= val or else -1
            public int findFirst(int node, int left, int right, int end, int val) {
                if (minST[node] > val) {
                    return -1;
                }
                if (left == right) {
                    return left;
                }
                int mid = (left + right) / 2;
                // Search in left tree if there is value <= val
                if (minST[node * 2] <= val) {
                    return findFirst(node * 2, left, mid, end, val);
                }
                // Or else search in right tree if mid < end (in range [0,end])
                if (end > mid) {
                    return findFirst(node * 2 + 1, mid + 1, right, end, val);
                }
                return -1;
            }
        }
    }
/**
 * Your BookMyShow object will be instantiated and called as such:
 * BookMyShow obj = new BookMyShow(n, m);
 * int[] param_1 = obj.gather(k,maxRow);
 * boolean param_2 = obj.scatter(k,maxRow);
 */
}

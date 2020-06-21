package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 05/02/2020.
 * #1428 https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
 */
public class LeftmostColumnWithAtLeastAOne {

    // time O(m*log(n))
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int res = Integer.MAX_VALUE, m = dim.get(0), n = dim.get(1);
        for (int i = 0; i < m; i++) {
            int low = 0, high = n - 1;
            while (low < high) {
                int mid = low + (high - low) / 2, val = binaryMatrix.get(i, mid);
                if (val == 0) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            if (binaryMatrix.get(i, low) == 1) {
                res = Math.min(res, low);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // This is the BinaryMatrix's API interface.
    // You should not implement it, or speculate about its implementation
    private interface BinaryMatrix {

        public int get(int row, int col);

        public List<Integer> dimensions();
    }
}

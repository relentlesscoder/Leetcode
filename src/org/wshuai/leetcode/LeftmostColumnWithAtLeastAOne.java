package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 05/02/2020.
 * #1428 https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
 */
public class LeftmostColumnWithAtLeastAOne {

    // time O(m*log(n))
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
	    List<Integer> dimension = binaryMatrix.dimensions();
	    int m = dimension.get(0), n = dimension.get(1), res = n;
	    for(int i = 0; i < m; i++){
		    if(binaryMatrix.get(i, n - 1) == 0){
			    continue;
		    }
		    res = Math.min(res, findLeftBound(binaryMatrix, i, n));
	    }
	    return res == n ? -1 : res;
    }

	private int findLeftBound(BinaryMatrix binaryMatrix, int m, int n){
		int left = 0, right = n;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(binaryMatrix.get(m, mid) == 0){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}

    // This is the BinaryMatrix's API interface.
    // You should not implement it, or speculate about its implementation
    private interface BinaryMatrix {

        public int get(int row, int col);

        public List<Integer> dimensions();
    }
}

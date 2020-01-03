package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 12/18/2019.
 * #995 https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/
 */
public class MinimumNumberOfKConsecutiveBitFlips {
	public int minKBitFlips(int[] A, int K) {
		int res = 0;
		LinkedList<Integer> queue = new LinkedList<>();
		for(int i = 0; i < A.length; i++){
			if(A[i] != (queue.size() % 2 == 1 ? 0 : 1)){
				res++;
				queue.offerLast(i + K - 1);
			}
			if(!queue.isEmpty() && queue.peekFirst() <= i){
				queue.pollFirst();
			}
		}
		return queue.isEmpty() ? res : -1;
	}
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 01/03/2020.
 * #0440 https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/
 */
public class KthSmallestInLexicographicalOrder {
	// https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/discuss/369094/Java-100-Solution-()
	public int findKthNumber(int n, int k) {
		long cur = 1;
		k--;
		while(k > 0){
			// gap is the number of nodes between cur and cur + 1
			long gap = findGap(cur, cur + 1, n);
			// if gap is less or equal than current k
			// we can skip all the nodes
			if(gap <= k){
				k -= gap;
				// move to the right
				cur = cur + 1;
			}else{
				k -= 1;
				// move to the left child node
				cur = cur * 10;
			}
		}
		return (int)cur;
	}

	private long findGap(long a, long b, int n){
		long gap = 0;
		// note that a <= n, check the case n = 100,
		// we still need to count the 1 element in the third level
		while(a <= n){
			/*
					1                   2
				10 ... 19        20 ... 29
			100 ...... 199  200 ...... 299
			say n = 198,
				a = 1, b = 2 -> 2 - 1 = 1
				a = 10, b = 20 -> 20 - 10 = 10
				n = 198, a = 100, b = 200 -> 199 - 100 = 99
				gap = 1 + 10 + 99 = 110
			 */
			gap += Math.min(n + 1, b) - a;
			a = a * 10;
			b = b * 10;
		}
		return gap;
	}
}

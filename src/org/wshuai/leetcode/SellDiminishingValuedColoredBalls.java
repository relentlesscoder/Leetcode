package org.wshuai.leetcode;

/**
 * Created by Wei on 02/04/2021.
 * #1682 https://leetcode.com/problems/longest-palindromic-subsequence-ii/
 */
public class SellDiminishingValuedColoredBalls {

	private static final long MOD = 1_000_000_007L;

	// time O(n*log(n))
	public int maxProfit(int[] inventory, int orders) {
		long res = 0L, left = 0L, right = 0L;
		for(int in : inventory){
			right = Math.max(right, in);
		}
		while(left < right){ // left is max value that creates orders less or equal to the orders
			long mid = left + (right - left) / 2;
			if(count(mid, inventory) <= orders){
				right = mid;
			}else{
				left = mid + 1;
			}
		}
		for(int in : inventory){
			if(in <= left){
				continue;
			}
			res = (res + (left + 1 + in) * (in - left) / 2) % MOD;
			orders -= (int)(in - left);
		}
		res = (res + orders * left) % MOD;
		return (int)res;
	}

	private long count(long mid, int[] inventory){
		long res = 0L;
		for(int in : inventory){
			res += Math.max(0, in - mid);
		}
		return res;
	}
}

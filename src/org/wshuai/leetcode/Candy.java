package org.wshuai.leetcode;

/**
 * Created by Wei on 06/07/2017.
 * #0135 https://leetcode.com/problems/candy/
 */
public class Candy {
	// time O(n), space O(n)
	// http://fisherlei.blogspot.com/2013/11/leetcode-candy-solution.html
	public int candy(int[] ratings) {
		int n = ratings.length;
		int[] left = new int[n], right = new int[n];
		left[0] = right[n - 1] = 1;
		for(int i = 1; i < n; i++){
			left[i] = ratings[i] > ratings[i - 1] ? left[i - 1] + 1 : 1;
		}
		for(int i = n - 2; i >= 0; i--){
			right[i] = ratings[i] > ratings[i + 1] ? right[i + 1] + 1 : 1;
		}
		int res = 0;
		for(int i = 0; i < n; i++){
			// number of candies at i is decided by the longer
			// increasing subsets end at i from left and right
			res += Math.max(left[i], right[i]);
		}
		return res;
	}
}

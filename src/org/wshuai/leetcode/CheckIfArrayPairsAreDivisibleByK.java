package org.wshuai.leetcode;

/**
 * Created by Wei on 07/25/2020.
 * #1497 https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/
 */
public class CheckIfArrayPairsAreDivisibleByK {

	// time O(n), space O(k)
	public boolean canArrange(int[] arr, int k) {
		if(k == 1){
			return true;
		}
		int[] freq = new int[k];
		for(int i = 0; i < arr.length; i++){
			int mod = arr[i] % k;
			mod += mod < 0 ? k : 0;
			freq[mod]++;
		}
		if(freq[0] % 2 != 0){
			return false;
		}
		for(int i = 1; i <= k/2; i++){
			if(freq[i] != freq[k - i]){
				return false;
			}
		}
		return true;
	}
}

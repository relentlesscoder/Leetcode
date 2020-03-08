package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/21/2016.
 * #0313 https://leetcode.com/problems/super-ugly-number/
 */
public class SuperUglyNumber {
	// O(n*k)
	// same idea as https://leetcode.com/problems/ugly-number-ii/
	public int nthSuperUglyNumber(int n, int[] primes) {
		int[] ugly = new int[n];
		int[] index = new int[primes.length], cache = new int[primes.length];
		Arrays.fill(cache, 1);
		int next = 1;
		for(int i = 0; i < n; i++){
			ugly[i] = next;
			next = Integer.MAX_VALUE;
			for(int j = 0; j < primes.length; j++){
				if(cache[j] == ugly[i]){
					cache[j] = ugly[index[j]++] * primes[j];
				}
				next = Math.min(next, cache[j]);
			}
		}
		return ugly[n - 1];
	}
}

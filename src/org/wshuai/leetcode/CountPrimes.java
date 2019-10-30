package org.wshuai.leetcode;

/**
 * Created by Wei on 10/24/2019.
 * #204 https://leetcode.com/problems/count-primes/
 */
public class CountPrimes {
	// a good explanation - https://www.youtube.com/watch?v=Kwo2jkHOyPY
	// https://www.geeksforgeeks.org/sieve-of-eratosthenes/
	public int countPrimes(int n) {
		if(n < 3){
			return 0;
		}
		boolean[] notPrime = new boolean[n];
		notPrime[0] = notPrime[1] = true;
		for(int i = 2; i < Math.sqrt(n); i++){
			if(notPrime[i]){
				continue;
			}
			for(int j = 2; j * i < n; j++){
				notPrime[j * i] = true;
			}
		}
		int res = 0;
		for(int i = 2; i < n; i++){
			if(!notPrime[i]){
				res++;
			}
		}
		return res;
	}
}

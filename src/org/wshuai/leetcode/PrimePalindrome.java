package org.wshuai.leetcode;

/**
 * Created by Wei on 10/24/2019.
 * #866 https://leetcode.com/problems/prime-palindrome/
 */
public class PrimePalindrome {
	// https://leetcode.com/problems/prime-palindrome/discuss/146798/All-Even-Digits-Palindrome-are-Divisible-by-11
	public int primePalindrome(int N) {
		if(N >= 8 && N <= 11){
			return 11;
		}
		for(int x = 1; x <= 100_000; x++){
			String s = Integer.toString(x);
			String r = new StringBuilder(s).reverse().toString();
			int y = Integer.parseInt(s + r.substring(1));
			if(y >= N && isPrime(y)){
				return y;
			}
		}
		return -1;
	}

	private boolean isPrime(int x){
		if(x < 2 || x % 2 == 0){
			return x == 2;
		}
		for(int i = 3; i*i <= x; i += 2){
			if(x % i == 0){
				return false;
			}
		}
		return true;
	}
}

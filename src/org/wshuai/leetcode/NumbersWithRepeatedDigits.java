package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/9/2019.
 * #1012 https://leetcode.com/problems/numbers-with-repeated-digits/
 */
public class NumbersWithRepeatedDigits {
	public int numDupDigitsAtMostN(int N) {
		int[] digits = toDigits(N);
		int digitNum = digits.length;

		// count of numbers without duplicate digits
		int count = 0;

		// count of (non-dup) numbers with i digits (i < digitNum)
		for(int i = 1; i < digitNum; i++){
			count += 9 * countPermuation(9, i - 1);
		}

		boolean[] used = new boolean[10];
		int idx = 0;

		// count of (non-dup) numbers with same length but lower most significant digit
		count += (digits[0] - 1) * countPermuation(9, digitNum - 1);

		for(; idx < digitNum - 1; idx++){
			// duplicate detected
			if(used[digits[idx]]){
				break;
			}
			// for each same prefix as original number N,
			// calculate number of (non-dup) numbers less
			// to N
			used[digits[idx]] = true;
			for(int k = 0; k < digits[idx + 1]; k++){
				if(!used[k]){
					count += countPermuation(10 - idx - 2, digitNum - idx - 2);
				}
			}
		}
		// lastly, check N itself to see if it's non-dup
		if(idx == digitNum - 1 && !used[digits[idx]]){
			count++;
		}
		// minus all non-dup we get the number with duplicate digits
		return N - count;
	}

	private int[] toDigits(int N){
		List<Integer> lst = new ArrayList<>();
		while(N > 0){
			lst.add(N % 10);
			N /= 10;
		}
		int[] res = new int[lst.size()];
		int j = 0;
		for(int i = res.length - 1; i >= 0; i--){
			res[j++] = lst.get(i);
		}
		return res;
	}

	// calculate n! / (n - m)!
	private int countPermuation(int n, int m){
		int res = 1;
		for(int i = n - m + 1; i <= n; i++){
			res *= i;
		}
		return res;
	}
}

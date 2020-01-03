package org.wshuai.leetcode;

/**
 * Created by Wei on 12/19/2019.
 * #902 https://leetcode.com/problems/numbers-at-most-n-given-digit-set/
 */
public class NumbersAtMostNGivenDigitSet {
	public int atMostNGivenDigitSet(String[] D, int N) {
		int[] digits = new int[D.length];
		for(int i = 0; i < D.length; i++){
			digits[i] = Integer.parseInt(D[i]);
		}
		char[] arr = Integer.toString(N).toCharArray();
		int totalCount = 0;
		int p = arr.length;
		while(p > 0){
			totalCount += (int)Math.pow(D.length, p);
			p--;
		}
		for(int i = 0; i < arr.length; i++){
			int digit = Character.getNumericValue(arr[i]);
			int leftBound = searchLeft(digits, digit);
			if(leftBound >= digits.length){
				break;
			}
			int count = digits.length - leftBound - (digits[leftBound] == digit ? 1 : 0);
			totalCount -= (count) * (int)Math.pow(D.length, arr.length - i - 1);
			if(digits[leftBound] != digit){
				break;
			}
		}
		return totalCount;
	}

	private int searchLeft(int[] arr, int t){
		int l = 0;
		int r = arr.length;
		while(l < r){
			int mid = l + (r - l) / 2;
			if(arr[mid] < t){
				l = mid + 1;
			}else{
				r = mid;
			}
		}
		return l;
	}
}



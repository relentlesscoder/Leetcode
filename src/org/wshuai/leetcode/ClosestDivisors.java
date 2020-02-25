package org.wshuai.leetcode;

/**
 * Created by Wei on 02/24/2020.
 * #1362 https://leetcode.com/problems/closest-divisors/
 */
public class ClosestDivisors {
	public int[] closestDivisors(int num) {
		int[] arr1 = closestDivisor(num + 1);
		int[] arr2 = closestDivisor(num + 2);
		return Math.abs(arr1[0] - arr1[1]) < Math.abs(arr2[0] - arr2[1]) ? arr1 : arr2;
	}

	private int[] closestDivisor(int num){
		int minDiff = Integer.MAX_VALUE;
		int[] res = new int[2];
		for(int i = 1; i * i <= num; i++){
			if(num % i == 0){
				int j = num / i, diff = Math.abs(i - j);
				if(diff < minDiff){
					minDiff = diff;
					res[0] = i;
					res[1] = j;
				}
			}
		}
		return res;
	}
}

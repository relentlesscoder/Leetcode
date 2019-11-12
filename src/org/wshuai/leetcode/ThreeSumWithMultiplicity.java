package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/6/2019.
 * #923 https://leetcode.com/problems/3sum-with-multiplicity/
 */
public class ThreeSumWithMultiplicity {
	public int threeSumMulti(int[] A, int target) {
		int MOD = 1_000_000_007;
		Arrays.sort(A);
		long res = 0;
		for(int i = 0; i < A.length - 2; i++){
			int diff = target - A[i];
			int j = i + 1;
			int k = A.length - 1;
			while(j < k){
				int sum = A[j] + A[k];
				if(sum == diff){
					if(A[j] != A[k]){
						int left = 1;
						while(j + 1 < k && A[j + 1] == A[j]){
							left++;
							j++;
						}
						int right = 1;
						while(k - 1 > j && A[k - 1] == A[k]){
							right++;
							k--;
						}
						res += left * right;
						res %= MOD;
						j++;
						k--;
					}else{
						res += (k - j + 1) * (k - j) / 2;
						res %= MOD;
						break;
					}
				}else if(sum < diff){
					j++;
				}else{
					k--;
				}
			}
		}
		return (int)res;
	}
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2019.
 * #0788 https://leetcode.com/problems/rotated-digits/
 */
public class RotatedDigits {
	// time O(n), space O(n)
	public int rotatedDigits(int N) {
		int[] dp = new int[N + 1];
		int count = 0;
		for(int i = 0; i <= N; i++){
			if(i < 10){
				if(i == 0 || i == 1 || i == 8){
					dp[i] = 1;
				}else if(i == 2 || i == 5 || i == 6 || i == 9){ // rotated to a different number
					dp[i] = 2;
					count++;
				}
			}else{
				// divide into two substring, [0 -> n-1], [n-1] for DP
				int a = dp[i / 10], b = dp[i % 10];
				if(a == 1 && b == 1){ // both two parts rotated to themselves
					dp[i] = 1;
				}else if(a >= 1 && b >= 1){ // at least one part can be rotated to a different number
					dp[i] = 2;
					count++;
				}
			}
		}
		return count;
	}
}

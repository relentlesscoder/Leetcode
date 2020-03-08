package org.wshuai.leetcode;

/**
 * Created by Wei on 02/24/2020.
 * #1363 https://leetcode.com/problems/largest-multiple-of-three/
 */
public class LargestMultipleOfThree {
	// time O(n)
	public String largestMultipleOfThree(int[] digits) {
		int[] count = new int[10];
		for(int d : digits){
			count[d]++;
		}
		int remainingOneCount = count[1] + count[4] + count[7];
		int remainingTwoCount = count[2] + count[5] + count[8];
		int remainingSum = (remainingOneCount + 2 * remainingTwoCount) % 3;
		if(remainingSum == 1){
			// two cases:
			// 1. 4 % 3 = 1
			// 2. (2 + 5) % 3 = (2 % 3 + 5 % 3) % 3 = 1
			if(remainingOneCount >= 1){
				remainingOneCount -= 1;
			}else{
				remainingTwoCount -= 2;
			}
		}else if(remainingSum == 2){
			if(remainingTwoCount >= 1){
				remainingTwoCount -= 1;
			}else{
				remainingOneCount -= 2;
			}
		}
		StringBuilder res = new StringBuilder();
		for(int d = 9; d >= 0; d--){
			if(d % 3 == 0){
				while(count[d]-- > 0){
					res.append(d);
				}
			}else if(d % 3 == 1){
				while(count[d]-- > 0 && remainingOneCount-- > 0){
					res.append(d);
				}
			}else{
				while(count[d]-- > 0 && remainingTwoCount-- > 0){
					res.append(d);
				}
			}
		}
		if(res.length() > 0 && res.charAt(0) == '0'){
			return "0";
		}
		return res.toString();
	}
}

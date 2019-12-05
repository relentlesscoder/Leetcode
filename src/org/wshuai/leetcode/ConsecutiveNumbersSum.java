package org.wshuai.leetcode;

/**
 * Created by Wei on 12/5/2019.
 * #829 https://leetcode.com/problems/consecutive-numbers-sum/
 */
public class ConsecutiveNumbersSum {
	// https://leetcode.com/problems/consecutive-numbers-sum/discuss/129015/5-lines-C%2B%2B-solution-with-detailed-mathematical-explanation.
	public int consecutiveNumbersSum(int N) {
		int count = 1;
		for(int k = 2; k < Math.sqrt(2 * N); k++){
			if(( N - ( k * ( k - 1 )/2) ) % k == 0){
				count++;
			}
		}
		return count;
	}
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2019.
 * #0754 https://leetcode.com/problems/reach-a-number/
 */
public class ReachANumber {
	// time O(n)
	// https://leetcode.com/problems/reach-a-number/discuss/112968/Short-JAVA-Solution-with-Explanation
	public int reachNumber(int target) {
		target = Math.abs(target);
		int step = 0, sum = 0;
		while(sum < target){
			step++;
			sum += step;
		}
		while((sum - target) % 2 != 0){
			step++;
			sum += step;
		}
		return step;
	}
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 11/7/19.
 * #754 https://leetcode.com/problems/reach-a-number/
 */
public class ReachANumber {
	// math :( https://leetcode.com/problems/reach-a-number/discuss/112968/Short-JAVA-Solution-with-Explanation
	public int reachNumber(int target) {
		target = Math.abs(target);
		int step = 0;
		int sum = 0;
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

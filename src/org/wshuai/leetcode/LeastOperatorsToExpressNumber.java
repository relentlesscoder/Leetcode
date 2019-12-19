package org.wshuai.leetcode;

/**
 * Created by Wei on 12/19/2019.
 * #964 https://leetcode.com/problems/least-operators-to-express-number/
 */
public class LeastOperatorsToExpressNumber {
	// https://leetcode.com/problems/least-operators-to-express-number/
	public int leastOpsExpressTarget(int x, int target) {
		// At this time, you can get target either by add target times x/x or subtract (x - target) times x/x to x
		// For example, x = 3, target = 2. Then, 3/3 + 3/3 or 3 - 3/3 is possible result
		if(x > target){
			return Math.min(target * 2 - 1, (x - target) * 2);
		}
		// just push x at the end
		if(x == target){
			return 0;
		}

		long sums = x;
		int times = 0;
		// this is greedy, put as much as possible 'x'
		while(sums < target){
			times++;
			sums *= x;
		}

		// one more 'x' you put, one more operator
		if(sums == target){
			return times;
		}

		// when you have remainder, you have two choices, one is add, the other is subtract
		// for example, x = 3, target = 5. Then, 5 = 3 + 2 or 5 = 9 - 4
		int l = Integer.MAX_VALUE, r = Integer.MAX_VALUE;
		if(sums - target < target){
			int diff = (int)(sums - target);
			// using subtract
			l = leastOpsExpressTarget(x, diff) + times;
		}
		// using add
		r = leastOpsExpressTarget(x, target - (int)(sums / x)) + times - 1;
		// No matter +/- used, one more operator is add
		return Math.min(l, r) + 1;
	}
}

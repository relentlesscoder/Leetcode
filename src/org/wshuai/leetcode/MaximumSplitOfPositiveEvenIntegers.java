package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/03/2023.
 * #2178 https://leetcode.com/problems/maximum-split-of-positive-even-integers/
 */
public class MaximumSplitOfPositiveEvenIntegers {

	// time O(sqrt(n)), space O(1)
	public List<Long> maximumEvenSplit(long finalSum) {
		List<Long> res = new ArrayList<>();
		if (finalSum % 2 != 0) {
			return res;
		}
		/**
		Take n =14
		i = 2 , current = 0 , list = [] (current + 2 <= 14 , so push it) , current + i = 2 , list = [2]
		i = 4 , current = 2 , list = [2] (current + 4 <= 14 , so push it) , current + i = 6 , list = [2,4]
		i = 6 , current = 6 , list = [2,4] (current + 6 <= 14 , so push it) , current + i = 12 , list = [2,4,6]
		i = 8 , current = 12 , list = [2,4,6] (current + 8 > 14 , so don't push it , break the loop)
		Now we have current = 12 , and we want 14 , so simply add difference (which is 14 - 12 = 2 ) in the last element of list
		so list = [2,4,6 + (14 - 12)] = [2,4,8]
		 */
		long curr = 2;
		while (finalSum > 0) {
			if (finalSum - curr < 0) {
				break;
			}
			res.add(curr);
			finalSum -= curr;
			curr += 2;
		}
		if (finalSum > 0) {
			long tail = res.get(res.size() - 1);
			res.set(res.size() - 1, tail + finalSum);
		}
		return res;
	}
}

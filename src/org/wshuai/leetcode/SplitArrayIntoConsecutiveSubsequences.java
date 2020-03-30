package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/15/2019.
 * #0659 https://leetcode.com/problems/split-array-into-consecutive-subsequences/
 */
public class SplitArrayIntoConsecutiveSubsequences {
	// time O(n), space O(1)
	// https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106495/Java-O(n)-time-and-O(1)-space-solution-greedily-extending-shorter-subsequence
	public boolean isPossible(int[] nums) {
		int pre = Integer.MIN_VALUE, p1 = 0, p2 = 0, p3 = 0;
		int cur = 0, cnt = 0, c1 = 0, c2 = 0, c3 = 0;

		for(int i = 0; i < nums.length; pre = cur, p1 = c1, p2 = c2, p3 = c3){
			for(cur = nums[i], cnt = 0; i < nums.length && cur == nums[i]; i++){
				cnt++;
			}
			if(cur != pre + 1){
				if(p1 != 0 || p2 != 0){
					return false;
				}
				c1 = cnt;
				c2 = c3 = 0;
			}else{
				if(cnt < p1 + p2){
					return false;
				}
				c1 = Math.max(0, cnt - (p1 + p2 + p3));
				c2 = p1;
				c3 = p2 + Math.min(p3, cnt - (p1 + p2));
			}
		}

		return (p1 == 0 && p2 == 0);
	}

	// time O(n), space O(n)
	// https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106496/Java-O(n)-Time-O(n)-Space
	public boolean isPossibleHashmap(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<>(), append = new HashMap<>();
		for(int i : nums){
			freq.put(i, freq.getOrDefault(i, 0) + 1);
		}
		for(int i : nums){
			if(freq.get(i) == 0){
				continue;
			}
			if(append.getOrDefault(i, 0) > 0){
				append.put(i, append.get(i) - 1);
				append.put(i + 1, append.getOrDefault(i + 1, 0) + 1);
			}else if(freq.getOrDefault(i + 1, 0) > 0 && freq.getOrDefault(i + 2, 0) > 0){
				freq.put(i + 1, freq.get(i + 1) - 1);
				freq.put(i + 2, freq.get(i + 2) - 1);
				append.put(i + 3, append.getOrDefault(i + 3, 0) + 1);
			}else{
				return false;
			}
			freq.put(i, freq.get(i) - 1);
		}
		return true;
	}
}

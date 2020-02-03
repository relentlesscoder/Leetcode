package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 07/23/2017.
 * #0315 https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class CountOfSmallerNumbersAfterSelf {
	private int[] bit;
	private int n;

	// time O(n*log(n)), space O(n)
	// http://community.topcoder.com/i/education/binaryIndexedTrees/BITimg.gif
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if(nums == null || nums.length == 0){
			return res;
		}
		Map<Integer, Integer> map = new HashMap<>();
		int[] copy = nums.clone();
		// O(n*log(n))
		Arrays.sort(copy);
		int rank = 0;
		for(int i = 0; i < copy.length; i++){
			if(i == 0 || copy[i] != copy[i - 1]){
				map.put(copy[i], rank++);
			}
		}
		bit = new int[map.size() + 1];
		n = map.size();
		// O(n*log(k)), k is the number of distinct element
		for(int i = nums.length - 1; i >= 0; i--){
			// use rank as the index of binary indexed tree
			rank = map.get(nums[i]);
			res.add(getSum(rank - 1));
			update(rank, 1);
		}
		Collections.reverse(res);
		return res;
	}

	private int getSum(int index){
		int res = 0;
		index++;
		while(index > 0){
			res += bit[index];
			index -= (index & -index);
		}
		return res;
	}

	private void update(int index, int delta){
		index++;
		while(index <= n){
			bit[index] += delta;
			index += (index & -index);
		}
	}
}

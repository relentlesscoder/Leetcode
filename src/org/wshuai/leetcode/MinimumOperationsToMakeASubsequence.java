package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 03/06/2021.
 * #1713 https://leetcode.com/problems/minimum-operations-to-make-a-subsequence/
 */
public class MinimumOperationsToMakeASubsequence {

	// time O(n*log(n)), space O(n)
	public int minOperations(int[] target, int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < target.length; i++){
			map.put(target[i], i);
		}
		int[] tails = new int[target.length]; // #0300 longest increasing subsequence
		int lis = 0;
		for(int a : arr){
			if(!map.containsKey(a)){
				continue;
			}
			int index = map.get(a), left = 0, right = lis;
			while(left < right){
				int mid = left + (right - left) / 2;
				if(tails[mid] < index){
					left = mid + 1;
				}else{
					right = mid;
				}
			}
			tails[left] = index;
			if(left == lis){
				lis++;
			}
		}
		return target.length - lis;
	}
}

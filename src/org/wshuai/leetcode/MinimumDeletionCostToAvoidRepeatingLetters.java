package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2020.
 * #1578 https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/
 */
public class MinimumDeletionCostToAvoidRepeatingLetters {

	// time O(n)
	public int minCost(String s, int[] cost) {
		int res = 0, count = 1, max = cost[0], sum = cost[0];
		char prev = s.charAt(0);
		for(int i = 1; i < s.length(); i++){
			char cur = s.charAt(i);
			if(cur != prev){
				if(count > 1){
					res += sum - max;
				}
				prev = cur;
				count = 1;
				max = sum = cost[i];
			}else{
				count++;
				max = Math.max(max, cost[i]);
				sum += cost[i];
			}
		}
		if(count > 1){
			res += sum - max;
		}
		return res;
	}
}

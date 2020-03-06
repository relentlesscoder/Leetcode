package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/2019.
 * #0517 https://leetcode.com/problems/super-washing-machines/
 */
public class SuperWashingMachines {
	// time O(n)
	// https://leetcode.com/problems/super-washing-machines/discuss/99185/Super-Short-and-Easy-Java-O(n)-Solution
	public int findMinMoves(int[] machines) {
		int N = machines.length;
		int sum = 0;
		for(int m : machines){
			sum += m;
		}
		if(sum % N != 0){
			return -1;
		}
		int res = 0, target = sum / N, curr = 0;
		for(int m : machines){
			int load = m - target;
			curr += load;
			res = Math.max(res, Math.max(Math.abs(curr), load));
		}
		return res;
	}
}

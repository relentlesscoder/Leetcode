package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/19.
 * #517 https://leetcode.com/problems/super-washing-machines/
 */
public class SuperWashingMachines {
	public int findMinMoves(int[] machines) {
		int N = machines.length;
		int sum = 0;
		for(int m : machines){
			sum += m;
		}
		if(sum % N != 0){
			return -1;
		}
		int res = 0;
		int target = sum / N;
		int curr = 0;
		for(int m : machines){
			int load = m - target;
			curr += load;
			res = Math.max(res, Math.max(Math.abs(curr), load));
		}
		return res;
	}
}

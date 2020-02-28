package org.wshuai.leetcode;

/**
 * Created by Wei on 10/12/2019.
 * #0464 https://leetcode.com/problems/can-i-win/
 */
public class CanIWin {
	// time O(2^n)
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		if((maxChoosableInteger * (maxChoosableInteger + 1) >> 1) < desiredTotal){
			return false;
		}
		if(desiredTotal <= 0){
			return true;
		}
		byte[] states = new byte[1 << maxChoosableInteger];
		return dfs(desiredTotal, 0, maxChoosableInteger, states);
	}

	private boolean dfs(int target, int cur, int range, byte[] states){
		if(target <= 0){
			return false;
		}
		if(states[cur] != 0){
			return states[cur] == 1;
		}
		for(int i = 0; i < range; i++){
			if(((cur >>> i) & 1) == 1){
				continue;
			}
			if(!dfs(target - (i + 1), cur | (1 << i), range, states)){
				states[cur] = 1;
				return true;
			}
		}
		states[cur] = -1;
		return false;
	}
}

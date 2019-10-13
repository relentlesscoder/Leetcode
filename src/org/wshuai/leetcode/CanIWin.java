package org.wshuai.leetcode;

/**
 * Created by Wei on 10/12/2019.
 * #464 https://leetcode.com/problems/can-i-win/
 */
public class CanIWin {
	private byte[] map;

	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
		if(sum < desiredTotal){
			return false;
		}
		if(desiredTotal <= 0){
			return true;
		}
		map = new byte[1 << maxChoosableInteger];
		return canIWin(maxChoosableInteger, desiredTotal, 0);
	}

	private boolean canIWin(int max, int target, int state){
		if(target <= 0){
			return false;
		}
		if(map[state] != 0){
			return map[state] == 1;
		}
		for(int i = 0; i < max; i++){
			if((state & (1 << i)) > 0){
				continue;
			}
			if(!canIWin(max, target - (i + 1), state | (1 << i))){
				map[state] = 1;
				return true;
			}
		}
		map[state] = -1;
		return false;
	}
}

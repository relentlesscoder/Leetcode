package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/19.
 * #717 https://leetcode.com/problems/1-bit-and-2-bit-characters/
 */
public class OneBitAndTwoBitCharacters {
	private int[] bits;

	public boolean isOneBitCharacter(int[] bits) {
		this.bits = bits;
		return dfs(0);
	}

	private boolean dfs(int s){
		if(s >= bits.length){
			return false;
		}
		if(s == bits.length - 1){
			return true;
		}
		if(bits[s] == 1){
			return dfs(s + 2);
		}else{
			return dfs(s + 1);
		}
	}
}

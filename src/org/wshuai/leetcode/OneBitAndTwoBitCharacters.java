package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2019.
 * #0717 https://leetcode.com/problems/1-bit-and-2-bit-characters/
 */
public class OneBitAndTwoBitCharacters {

	// time O(n)
	public boolean isOneBitCharacter(int[] bits) {
		return dfs(0, bits, bits.length);
	}

	private boolean dfs(int cur, int[] bits, int n){
		if(cur == n - 1){
			return true;
		}
		if(cur >= n){
			return false;
		}
		if(bits[cur] == 1){
			return dfs(cur + 2, bits, n);
		}
		return dfs(cur + 1, bits, n);
	}
}

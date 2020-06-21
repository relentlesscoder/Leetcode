package org.wshuai.leetcode;

/**
 * Created by Wei on 08/05/2019.
 * #0771 https://leetcode.com/problems/jewels-and-stones/
 */
public class JewelsAndStones {
	// time O(n)
	public int numJewelsInStones(String J, String S) {
		boolean[] jewels = new boolean[128];
		for(char j : J.toCharArray()){
			jewels[j] = true;
		}
		int res = 0;
		for(char s : S.toCharArray()){
			res += jewels[s] ? 1 : 0;
		}
		return res;
	}
}

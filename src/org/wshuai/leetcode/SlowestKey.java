package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2020.
 * #1629 https://leetcode.com/problems/slowest-key/
 */
public class SlowestKey {

	// time O(n)
	public char slowestKey(int[] releaseTimes, String keysPressed) {
		char res = keysPressed.charAt(0);
		int maxTime = releaseTimes[0];
		for(int i = 1; i < keysPressed.length(); i++){
			char c = keysPressed.charAt(i);
			int cur = releaseTimes[i] - releaseTimes[i - 1];
			if(cur > maxTime || (cur == maxTime && c > res)){
				maxTime = cur;
				res = c;
			}
		}
		return res;
	}
}

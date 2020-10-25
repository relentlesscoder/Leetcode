package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2020.
 * #1629 https://leetcode.com/problems/slowest-key/
 */
public class SlowestKey {

	// time O(n)
	public char slowestKey(int[] releaseTimes, String keysPressed) {
		int n = releaseTimes.length, longest = 0;
		char key = (char)0;
		for(int i = 0; i < n; i++){
			char cur = keysPressed.charAt(i);
			int time = releaseTimes[i] - (i == 0 ? 0 : releaseTimes[i - 1]);
			if(time > longest || (time == longest && cur > key)){
				longest = time;
				key = cur;
			}
		}
		return key;
	}
}

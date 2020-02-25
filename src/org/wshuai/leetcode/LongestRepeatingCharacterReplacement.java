package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #0424 https://leetcode.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharacterReplacement {
	// time O(n)
	// https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91271/Java-12-lines-O(n)-sliding-window-solution-with-explanation
	public int characterReplacement(String s, int k) {
		int n = s.length(), start = 0, maxCount = 0, maxLength = 0;
		int[] count = new int[26];
		for(int end = 0; end < n; end++){
			maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
			while(end - start + 1 - maxCount > k){
				count[s.charAt(start) - 'A']--;
				start++;
			}
			maxLength = Math.max(maxLength, end - start + 1);
		}
		return maxLength;
	}
}

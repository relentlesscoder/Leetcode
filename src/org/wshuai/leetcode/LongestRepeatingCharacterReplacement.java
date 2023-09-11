package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #0424 https://leetcode.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharacterReplacement {

	// time O(n), space O(1)
	// https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91271/Java-12-lines-O(n)-sliding-window-solution-with-explanation
	public int characterReplacement(String s, int k) {
		int res = 0;
		int[] count = new int[26];
		for(int i = 0, j = 0, maxCount = 0; j < s.length(); j++){
			maxCount = Math.max(maxCount, ++count[s.charAt(j) - 'A']);
			while(j - i + 1 - maxCount > k){
				count[s.charAt(i++) - 'A']--;
			}
			res = Math.max(res, j - i + 1);
		}
		return res;
	}

	/**
	Many people know the problem can be reiterated as :
			"longest substring where (length - max occurrence) <= k"
	The key point is we are focusing on "longest".
	So assume we initially found a valid substring, what do we do next?

	Still valid even appended by one more char——then we are happy, just expand the window
	It became invalid. But at this time, do we have to shrink the window?
			——No, we shift the whole window rightwards by one unit. So that the length is unchanged.
	The reason for that is , any index smaller than original "start", will never have the chance to lead a longer valid substring than
	 current length of our window.
	Do we need to update max in time?
			——No. Once again, we focus on "longest". The length of valid substring is determined by what?
	Max Occurrence + k
	We only need to update max occurrence when it becomes larger, because only that can we generate a longer valid substring.
	If we can't surpass the historic max occurrence, then we can not generate a longer valid substring from current "start", I mean the
	 new windows's left bound. To some extend, this becomes a game to find max occurrence.
			Or , a better understanding is:
			"A game to eliminate inferior 'left bounds'.
	 **/
}

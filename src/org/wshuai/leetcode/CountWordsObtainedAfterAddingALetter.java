package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/21/2023.
 * #2135 https://leetcode.com/problems/count-words-obtained-after-adding-a-letter/
 */
public class CountWordsObtainedAfterAddingALetter {

	// time O((n + m) * L), space O(n)
	public int wordCount(String[] startWords, String[] targetWords) {
		int res = 0;
		Set<Integer> set = new HashSet<>();
		for (String start : startWords) {
			int curr = 0;
			for (char c : start.toCharArray()) {
				curr |= (1 << (c - 'a')); // for each start words, add bit mask to hash set. for example "act" -> "00 0000 0100 0000 0000 0000 0101"
			}
			set.add(curr);
		}
		for (String target : targetWords) {
			boolean canConvert = false;
			int curr = 0, next = 0;
			// for each target word, remove one set bit and check if it is in the hash set. for example "tack" ("00 0000 0100 0000 0100 0000 0101"), by
			// removing 1 set bit, we can have "00 0000 0000 0000 0100 0000 0101", "00 0000 0100 0000 0000 0000 0101", "00 0000 0100 0000 0100 0000 0001"
			// and "00 0000 0100 0000 0100 0000 0100", we can find "00 0000 0100 0000 0000 0000 0101" ("act") in the hash set thus it is convertible.
			for (char c : target.toCharArray()) {
				curr |= (1 << (c - 'a'));
			}
			for (char c : target.toCharArray()) {
				next = curr - (1 << (c - 'a'));
				if (set.contains(next)) {
					canConvert = true;
					break;
				}
			}
			res += canConvert ? 1 : 0;
		}
		return res;
	}
}

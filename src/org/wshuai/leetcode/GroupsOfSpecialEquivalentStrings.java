package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 8/22/19.
 * #893 https://leetcode.com/problems/groups-of-special-equivalent-strings/
 */
public class GroupsOfSpecialEquivalentStrings {
	public int numSpecialEquivGroups(String[] A) {
		Set<String> set = new HashSet<>();
		for (String a : A) {
			int[] count = new int[52];
			for (int i = 0; i < a.length(); i++) {
				count[a.charAt(i) - 'a' + 26 * (i % 2)]++;
			}
			set.add(Arrays.toString(count));
		}
		return set.size();
	}
}

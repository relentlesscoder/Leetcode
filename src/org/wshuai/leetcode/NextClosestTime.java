package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by Wei on 11/09/2019.
 * #0681 https://leetcode.com/problems/next-closest-time/
 */
public class NextClosestTime {
	public String nextClosestTime(String time) {
		char[] result = time.toCharArray();
		Character[] digits = new Character[]{result[0], result[1], result[3], result[4]};
		TreeSet<Character> set = new TreeSet<Character>(Arrays.asList(digits));

		result[4] = findNext(result[4], '9', set);
		if(result[4] > time.charAt(4)){
			return String.valueOf(result);
		}

		result[3] = findNext(result[3], '5', set);
		if(result[3] > time.charAt(3)){
			return String.valueOf(result);
		}

		result[1] = findNext(result[1], result[0] == '2' ? '3' : '9', set);
		if(result[1] > time.charAt(1)){
			return String.valueOf(result);
		}

		result[0] = findNext(result[0], '2', set);
		return String.valueOf(result);
	}

	private char findNext(char cur, char limit, TreeSet<Character> set){
		// find the next valid larger digit, if not return the smallest digit
		// and try find the next valid larger digit in a higher position
		Character c = set.higher(cur);
		return c == null || c > limit ? set.first() : c;
	}
}

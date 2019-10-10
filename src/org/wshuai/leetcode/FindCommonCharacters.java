package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/9/19.
 * #1002 https://leetcode.com/problems/find-common-characters/
 */
public class FindCommonCharacters {
	public List<String> commonChars(String[] A) {
		List<String> res = new ArrayList<String>();
		int[] arr = new int[26];
		for (char c : A[0].toCharArray()) {
			arr[c - 'a']++;
		}
		for (int i = 1; i < A.length; i++) {
			int[] aux = new int[26];
			for (char c : A[i].toCharArray()) {
				if (arr[c - 'a'] > 0) {
					arr[c - 'a']--;
					aux[c - 'a']++;
				}
			}
			arr = aux;
		}
		for (int i = 0; i < 26; i++) {
			int cnt = arr[i];
			while (cnt > 0) {
				res.add("" + (char) (i + 'a'));
				cnt--;
			}
		}
		return res;
	}
}

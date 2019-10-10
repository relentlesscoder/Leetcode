package org.wshuai.leetcode;

/**
 * Created by Wei on 8/23/19.
 * #953 https://leetcode.com/problems/verifying-an-alien-dictionary/
 */
public class VerifyingAnAlienDictionary {
	public boolean isAlienSorted(String[] words, String order) {
		int[] arr = new int[26];
		char[] chars = order.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			arr[chars[i] - 'a'] = i;
		}
		for (int k = 1; k < words.length; k++) {
			char[] w1 = words[k].toCharArray();
			char[] w2 = words[k - 1].toCharArray();
			int i = 0, j = 0;
			while (i < w1.length || j < w2.length) {
				int v1 = i < w1.length ? arr[w1[i] - 'a'] : -1;
				int v2 = j < w2.length ? arr[w2[j] - 'a'] : -1;
				if (v1 == v2) {
					i++;
					j++;
					continue;
				} else if (v1 > v2) {
					break;
				} else {
					return false;
				}
			}
		}
		return true;
	}
}

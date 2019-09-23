package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 8/31/2019.
 * #1170 https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character/
 */
public class CompareStringsByFrequencyOfTheSmallestCharacter {
	public int[] numSmallerByFrequency(String[] queries, String[] words) {
		int[] qc = new int[queries.length];
		int[] wc = new int[words.length];
		int k = 0;
		for (String q : queries) {
			qc[k++] = frequency(q);
		}
		k = 0;
		for (String w : words) {
			wc[k++] = frequency(w);
		}
		int[] res = new int[qc.length];
		for (int i = 0; i < qc.length; i++) {
			int count = 0;
			for (int j = 0; j < wc.length; j++) {
				if (wc[j] > qc[i]) {
					count++;
				}
			}
			res[i] = count;
		}
		return res;
	}

	private int frequency(String S) {
		int[] arr = new int[26];
		char min = 'z';
		for (char c : S.toCharArray()) {
			arr[c - 'a']++;
			min = c < min ? c : min;
		}
		return arr[min - 'a'];
	}
}

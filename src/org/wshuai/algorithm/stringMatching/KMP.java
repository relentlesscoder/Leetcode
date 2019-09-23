package org.wshuai.algorithm.stringMatching;

/**
 * Created by Wei on 1/3/2017.
 * http://blog.csdn.net/v_july_v/article/details/7041827
 */
public class KMP {
	public int kmpMatch(String str, String pattern) {
		int i = 0;
		int j = 0;
		int sLen = str.length();
		int pLen = pattern.length();
		int[] table = new int[pLen];
		getTable(pattern, table);
		while (i < sLen && j < pLen) {
			if (j == -1 || str.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			} else {
				j = table[j];
			}
		}
		return (j == pLen) ? i - j : -1;
	}

	private void getTable(String pattern, int[] table) {
		int pLen = pattern.length();
		table[0] = -1;
		int k = -1;
		int j = 0;
		while (j < pLen - 1) {
			if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
				k++;
				j++;
				table[j] = k;
			} else {
				k = table[k];
			}
		}
	}
}

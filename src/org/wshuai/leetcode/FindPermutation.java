package org.wshuai.leetcode;

/**
 * Created by Wei on 1/24/2017.
 * #484 https://leetcode.com/problems/find-permutation/
 */
public class FindPermutation {
	public int[] findPermutation(String s) {
		int len = s.length();
		int nLen = len + 1;
		int[] res = new int[nLen];
		for (int i = 0; i < nLen; i++) {
			res[i] = i + 1;
		}
		int cLen = 0;
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c == 'D') {
				int j = i;
				while (j < len && s.charAt(j) == 'D') {
					cLen++;
					j++;
				}
				reverse(res, i, i + cLen);
				i = j;
			}
			cLen = 0;
		}
		return res;
	}

	private void reverse(int[] res, int i, int j) {
		while (j > i) {
			int temp = res[i];
			res[i] = res[j];
			res[j] = temp;
			i++;
			j--;
		}
	}
}

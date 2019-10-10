package org.wshuai.leetcode;

/**
 * Created by Wei on 8/9/19.
 * #824 https://leetcode.com/problems/goat-latin/
 */
public class GoatLatin {
	public String toGoatLatin(String S) {
		StringBuilder res = new StringBuilder();
		String[] arr = S.split(" ");
		for (int i = 0; i < arr.length; i++) {
			StringBuilder sb = new StringBuilder();
			char first = arr[i].charAt(0);
			if (first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u'
					|| first == 'A' || first == 'E' || first == 'I' || first == 'O' || first == 'U') {
				sb.append(arr[i]);
			} else {
				sb.append(arr[i].substring(1));
				sb.append("" + arr[i].charAt(0));
			}
			sb.append("ma");
			int j = i + 1;
			while (j > 0) {
				sb.append("a");
				j--;
			}
			res.append(sb.toString() + " ");
		}
		String s = res.toString();
		return s.substring(0, s.length() - 1);
	}
}

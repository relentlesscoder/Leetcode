package org.wshuai.leetcode;

/**
 * Created by Wei on 1/24/17.
 * #481 https://leetcode.com/problems/magical-string/
 */
public class MagicalString {
	public int magicalString(int n) {
		StringBuilder sb = new StringBuilder("1221121221221121122");
		int pt1 = 12;
		int pt2 = sb.length();
		int count = 0;
		while (sb.length() < n) {
			if (sb.charAt(pt1) == '1') {
				if (sb.charAt(pt2 - 1) == '1') {
					sb.append("2");
				} else {
					sb.append("1");
				}
				pt2++;
			} else {
				if (sb.charAt(pt2 - 1) == '1') {
					sb.append("22");
				} else {
					sb.append("11");
				}
				pt2 += 2;
			}
			pt1++;
		}
		for (int i = 0; i < n; i++) {
			if (sb.charAt(i) == '1') {
				count++;
			}
		}
		return count;
	}
}

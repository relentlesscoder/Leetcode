package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #0423 https://leetcode.com/problems/reconstruct-original-digits-from-english/
 */
public class ReconstructOriginalDigitsFromEnglish {
	// time O(n)
	public String originalDigits(String s) {
		int[] res = new int[10];
		int[] count = new int[26];
		for (char c : s.toCharArray()) {
			count[c - 'a']++;
		}
		/*
		count digits by the order below to avoid conflicts
		z 0 zero
		x 6 six
		u 4 four
		w 2 two
		g 8 eight
		f 5 five
		v 7 seven
		i 9 nine
		t 3 three
		o 1 one

		count array index to character mapping
		0-a 1-b 2-c 3-d 4-e 5-f 6-g 7-h 8-i 9-j 10-k 11-l 12-m 13-n 14-o 15-p 16-q 17-r 18-s 19-t 20-u 21-v 22-w 23-x 24-y 25-z
		*/
		while (count[25]-- > 0) {
			res[0]++;
			count[4]--;
			count[14]--;
			count[17]--;
		}
		while (count[23]-- > 0) {
			res[6]++;
			count[8]--;
			count[18]--;
		}
		while (count[20]-- > 0) {
			res[4]++;
			count[5]--;
			count[14]--;
			count[17]--;
		}
		while (count[22]-- > 0) {
			res[2]++;
			count[14]--;
			count[19]--;
		}
		while (count[6]-- > 0) {
			res[8]++;
			count[4]--;
			count[8]--;
			count[7]--;
			count[19]--;
		}
		while (count[5]-- > 0) {
			res[5]++;
			count[4]--;
			count[8]--;
			count[21]--;
		}
		while (count[21]-- > 0) {
			res[7]++;
			count[4] -= 2;
			count[13]--;
			count[18]--;
		}
		while (count[8]-- > 0) {
			res[9]++;
			count[4]--;
			count[13] -= 2;
		}
		while (count[19]-- > 0) {
			res[3]++;
			count[4] -= 2;
			count[7]--;
			count[17]--;
		}
		while (count[14]-- > 0) {
			res[1]++;
			count[4]--;
			count[13]--;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			while (res[i]-- > 0) {
				sb.append(i);
			}
		}
		return sb.toString();
	}
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/16.
 * #423 https://leetcode.com/problems/reconstruct-original-digits-from-english/
 */
public class ReconstructOriginalDigitsFromEnglish {
	public String originalDigits(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}
		int[] arr = new int[10];
		int[] map = new int[26];
		int len = s.length();
		for (int i = 0; i < len; i++) {
			char val = s.charAt(i);
			map[val - 'a']++;
		}
		//z - zero
		if (map[25] > 0) {
			int cnt = map[25];
			map[25] -= cnt;
			arr[0] = cnt;
			map[4] -= cnt;
			map[17] -= cnt;
			map[14] -= cnt;
		}
		//w - two
		if (map[22] > 0) {
			int cnt = map[22];
			arr[2] = cnt;
			map[22] -= cnt;
			map[19] -= cnt;
			map[14] -= cnt;
		}
		//x - six
		if (map[23] > 0) {
			int cnt = map[23];
			map[23] -= cnt;
			arr[6] = cnt;
			map[18] -= cnt;
			map[8] -= cnt;
		}
		//g - eight
		if (map[6] > 0) {
			int cnt = map[6];
			map[6] -= cnt;
			arr[8] = cnt;
			map[4] -= cnt;
			map[8] -= cnt;
			map[7] -= cnt;
			map[19] -= cnt;
		}
		//u - four
		if (map[20] > 0) {
			int cnt = map[20];
			map[20] -= cnt;
			arr[4] = cnt;
			map[5] -= cnt;
			map[14] -= cnt;
			map[17] -= cnt;
		}
		//o - one
		if (map[14] > 0) {
			int cnt = map[14];
			map[14] -= cnt;
			arr[1] = cnt;
			map[13] -= cnt;
			map[4] -= cnt;
		}
		//f - five
		if (map[5] > 0) {
			int cnt = map[5];
			map[5] -= cnt;
			arr[5] = cnt;
			map[8] -= cnt;
			map[21] -= cnt;
			map[4] -= cnt;
		}
		//t - three
		if (map[19] > 0) {
			int cnt = map[19];
			map[19] -= cnt;
			arr[3] = cnt;
			map[7] -= cnt;
			map[17] -= cnt;
			map[4] -= 2 * cnt;
		}
		//v - seven
		if (map[21] > 0) {
			int cnt = map[21];
			map[21] -= cnt;
			arr[7] = cnt;
			map[18] -= cnt;
			map[13] -= cnt;
			map[4] -= 2 * cnt;
		}
		//i - nine
		if (map[8] > 0) {
			arr[9] = map[8];
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int cnt = arr[i];
			while (cnt > 0) {
				sb.append(Integer.toString(i));
				cnt--;
			}
		}
		return sb.toString();
	}
}

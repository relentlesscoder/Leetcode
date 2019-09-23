package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 2/25/17.
 * #500 https://leetcode.com/problems/keyboard-row/
 */
public class KeyboardRow {
	public String[] findWords(String[] words) {
		if (words == null || words.length == 0) {
			return new String[0];
		}
		List<String> res = new ArrayList<String>();
		int[] aux = new int[26];
		aux[0] = 2; //a
		aux[1] = 3; //b
		aux[2] = 3; //c
		aux[3] = 2; //d
		aux[4] = 1; //e
		aux[5] = 2; //f
		aux[6] = 2; //g
		aux[7] = 2; //h
		aux[8] = 1; //i
		aux[9] = 2; //j
		aux[10] = 2; //k
		aux[11] = 2; //l
		aux[12] = 3; //m
		aux[13] = 3; //n
		aux[14] = 1; //o
		aux[15] = 1; //p
		aux[16] = 1; //q
		aux[17] = 1; //r
		aux[18] = 2; //s
		aux[19] = 1; //t
		aux[20] = 1; //u
		aux[21] = 3; //v
		aux[22] = 1; //w
		aux[23] = 3; //x
		aux[24] = 1; //y
		aux[25] = 3; //z
		for (String word : words) {
			String str = word.toLowerCase();
			int len = str.length();
			int val = aux[str.charAt(0) - 'a'];
			boolean valid = true;
			int j = 1;
			while (j < len) {
				if (aux[str.charAt(j) - 'a'] == val) {
					j++;
				} else {
					valid = false;
					break;
				}
			}
			if (valid) {
				res.add(word);
			}
		}
		return res.toArray(new String[0]);
	}
}

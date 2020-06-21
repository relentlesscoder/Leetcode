package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 12/16/2019.
 * #0761 https://leetcode.com/problems/special-binary-string/
 */
public class SpecialBinaryString {
	// time O(n^2)
	// https://www.cnblogs.com/grandyang/p/8606024.html
	// https://leetcode.com/problems/special-binary-string/discuss/155621/Logical-Thinking-with-Clear-Code
	// https://leetcode.com/problems/special-binary-string/discuss/113211/JavaC%2B%2BPython-Easy-and-Concise-Recursion
	public String makeLargestSpecial(String S) {
		int count = 0;
		int i = 0;
		List<String> res = new ArrayList<>();
		for(int j = 0; j < S.length(); j++){
			if(S.charAt(j) == '1'){
				count++;
			}else{
				count--;
			}
			if(count == 0){
				res.add('1' + makeLargestSpecial(S.substring(i + 1, j)) + '0');
				i = j + 1;
			}
		}
		Collections.sort(res, (a, b) -> b.compareTo(a));
		return String.join("", res);
	}
}

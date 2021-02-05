package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 08/05/2019.
 * #0937 https://leetcode.com/problems/reorder-log-files/
 */
public class ReorderLogFiles {

	// time O(d*n*log(n)), space O(n)
	public String[] reorderLogFiles(String[] logs) {
		int n = logs.length;
		String[] res = new String[n];
		List<String> letters = new ArrayList<>();
		for(int i = n - 1, j = n - 1; i >= 0; i--){
			String cur = logs[i];
			if(Character.isDigit(cur.charAt(cur.length() - 1))){
				res[j--] = cur;
			}else{
				letters.add(cur);
			}
		}
		Collections.sort(letters, (String a, String b) -> {
			String[] arr1 = a.split(" ", 2);
			String[] arr2 = b.split(" ", 2);
			return arr1[1].equals(arr2[1]) ?
					arr1[0].compareTo(arr2[0]) : arr1[1].compareTo(arr2[1]);
		});
		for(int i = 0, j = 0; i < letters.size(); i++){
			res[j++] = letters.get(i);
		}
		return res;
	}
}

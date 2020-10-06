package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Wei on 08/05/2019.
 * #0937 https://leetcode.com/problems/reorder-log-files/
 */
public class ReorderLogFiles {

	// time O(d*n*log(n)), space O(n)
	public String[] reorderLogFiles(String[] logs) {
		if(logs == null || logs.length == 0){
			return new String[0];
		}
		int n = logs.length;
		String[] res = new String[n];
		List<String> letterLogs = new ArrayList<>(),
			digitLogs = new ArrayList<>();
		for(String log : logs){
			if(Character.isDigit(log.charAt(log.length() - 1))){
				digitLogs.add(log);
			}else{
				letterLogs.add(log);
			}
		}
		Collections.sort(letterLogs, (String a, String b) -> {
			String[] arr1 = a.split(" ", 2),
				arr2 = b.split(" ", 2);
			if(arr1[1].equals(arr2[1])){
				return arr1[0].compareTo(arr2[0]);
			}else{
				return arr1[1].compareTo(arr2[1]);
			}
		});
		for(int i = 0; i < n; i++){
			if(i < letterLogs.size()){
				res[i] = letterLogs.get(i);
			}else{
				res[i] = digitLogs.get(i - letterLogs.size());
			}
		}
		return res;
	}
}

package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Wei on 8/5/19.
 * #937 https://leetcode.com/problems/reorder-log-files/
 */
public class ReorderLogFiles {
	public String[] reorderLogFiles(String[] logs) {
		if (logs == null || logs.length == 0) {
			return null;
		}
		int len = logs.length;
		List<String> dLogs = new ArrayList<String>();
		List<String> lLogs = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			String str = logs[i];
			char last = str.charAt(str.length() - 1);
			if (last >= '0' && last <= '9') {
				dLogs.add(logs[i]);
			} else {
				lLogs.add(logs[i]);
			}
		}
		Collections.sort(lLogs, new LogComparator());

		// using Lambda
        /*Collections.sort(lLogs, (log1, log2) -> {
            String[] arr1 = log1.split(" ", 2);
            String[] arr2 = log2.split(" ", 2);
            if(arr1[1].compareTo(arr2[1]) == 0){
                return arr1[0].compareTo(arr2[0]);
            }else{
                return arr1[1].compareTo(arr2[1]);
            }
        });*/

		for (int i = 0; i < len; i++) {
			if (i < lLogs.size()) {
				logs[i] = lLogs.get(i);
			} else {
				logs[i] = dLogs.get(i - lLogs.size());
			}
		}
		return logs;
	}
}

class LogComparator implements Comparator<String> {
	@Override
	public int compare(String log1, String log2) {
		String[] arr1 = log1.split(" ", 2);
		String[] arr2 = log2.split(" ", 2);
		if (arr1[1].compareTo(arr2[1]) == 0) {
			return arr1[0].compareTo(arr2[0]);
		} else {
			return arr1[1].compareTo(arr2[1]);
		}
	}
}

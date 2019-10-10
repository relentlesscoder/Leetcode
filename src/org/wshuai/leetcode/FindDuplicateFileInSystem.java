package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 8/6/19.
 * #609 https://leetcode.com/problems/find-duplicate-file-in-system/
 */
public class FindDuplicateFileInSystem {
	public List<List<String>> findDuplicate(String[] paths) {
		if (paths == null || paths.length == 0) {
			return null;
		}
		List<List<String>> res = new ArrayList<List<String>>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (int i = 0; i < paths.length; i++) {
			String[] arr = paths[i].split("\\s");
			String root = arr[0];
			int j = 1;
			while (j < arr.length) {
				int s = arr[j].indexOf("(");
				String filename = root + "/" + arr[j].substring(0, s);
				String content = arr[j].substring(s, arr[j].length() - 1);
				if (map.containsKey(content)) {
					map.get(content).add(filename);
				} else {
					List<String> lst = new ArrayList<String>();
					lst.add(filename);
					map.put(content, lst);
				}
				j++;
			}
		}
		for (List<String> val : map.values()) {
			if (val.size() > 1) {
				res.add(val);
			}
		}
		return res;
	}
}

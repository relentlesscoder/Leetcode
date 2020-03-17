package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 08/06/2019.
 * #0609 https://leetcode.com/problems/find-duplicate-file-in-system/
 */
public class FindDuplicateFileInSystem {
	// time O(n), space O(n)
	public List<List<String>> findDuplicate(String[] paths) {
		List<List<String>> res = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String path : paths) {
			String[] strs = path.split("\\s");
			String directory = strs[0];
			for (int i = 1; i < strs.length; i++) {
				String file = strs[i];
				int j = 0;
				while (j < file.length() && file.charAt(j) != '(') {
					j++;
				}
				String fileName = file.substring(0, j), content = file.substring(j + 1, file.length() - 1);
				map.putIfAbsent(content, new ArrayList<>());
				map.get(content).add(directory + "/" + fileName);
			}
		}
		for (String key : map.keySet()) {
			List<String> files = map.get(key);
			if (files.size() > 1) {
				res.add(files);
			}
		}
		return res;
	}
}

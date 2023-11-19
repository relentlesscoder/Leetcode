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

	// time O(n*d), space O(n*d)
	// followup answers https://leetcode.com/problems/find-duplicate-file-in-system/discuss/104123/C%2B%2B-clean-solution-answers-to-follow-up
	public List<List<String>> findDuplicate(String[] paths) {
		List<List<String>> res = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for(String p : paths){
			String[] vals = p.split("\\s");
			String path = vals[0];
			for(int i = 1; i < vals.length; i++){
				int index = vals[i].indexOf("(");
				String cur = vals[i], filename = cur.substring(0, index),
						content = cur.substring(index + 1, cur.length() - 1);
				map.putIfAbsent(content, new ArrayList<>());
				map.get(content).add(path + "/" + filename);
			}
		}
		for(List<String> files : map.values()){
			if(files.size() > 1){
				res.add(files);
			}
		}
		return res;
	}
}

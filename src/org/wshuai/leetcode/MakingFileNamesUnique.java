package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 06/21/2020.
 * #1487 https://leetcode.com/problems/making-file-names-unique/
 */
public class MakingFileNamesUnique {
	public String[] getFolderNames(String[] names) {
		int n = names.length;
		Map<String, Integer> map = new HashMap<>();
		String[] res = new String[n];
		for(int i = 0; i < n; i++){
			res[i] = getName(names[i], map);
		}
		return res;
	}

	private String getName(String name, Map<String, Integer> map){
		if(map.containsKey(name)){
			int n = map.get(name) + 1;
			while(map.containsKey(name + "(" + n + ")")){
				n++;
			}
			map.put(name, n);
			return getName(name + "(" + n + ")", map);
		}else{
			map.put(name, 0);
		}
		return name;
	}
}

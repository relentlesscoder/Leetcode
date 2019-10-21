package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/20/2019.
 * #1233 https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
 */
public class RemoveSubFoldersFromTheFilesystem {

	// sorting
	public List<String> removeSubfolders(String[] folder) {
		List<String> res = new ArrayList<>();
		Arrays.sort(folder);
		String root = folder[0];
		res.add(root);
		for(int i = 1; i < folder.length; i++){
			if(folder[i].startsWith(root + "/")){
				continue;
			}else{
				root = folder[i];
				res.add(root);
			}
		}
		return res;
	}

	private Map<String, String> map;

	//TLE
	public List<String> removeSubfoldersUnionFind(String[] folder) {
		map = new HashMap<>();
		for(int i = 0; i < folder.length; i++){
			map.put(folder[i], folder[i]);
		}
		for(int i = 0; i < folder.length; i++){
			for(int j = i + 1; j < folder.length; j++){
				String r1 = findRoot(folder[i]);
				String r2 = findRoot(folder[j]);
				if(folder[i].startsWith(folder[j] + "/")){
					map.put(folder[i], r2);
				}else if(folder[j].startsWith(folder[i] + "/")){
					map.put(folder[j], r1);
				}
			}
		}
		List<String> res = new ArrayList<>();
		for(Map.Entry<String, String> entry: map.entrySet()){
			String key = entry.getKey();
			if(key.equals(entry.getValue())){
				res.add(key);
			}
		}
		return res;
	}

	private String findRoot(String f){
		String r = f;
		if(map.get(f) != f){
			r = findRoot(map.get(f));
			map.put(f, r);
		}
		return r;
	}
}

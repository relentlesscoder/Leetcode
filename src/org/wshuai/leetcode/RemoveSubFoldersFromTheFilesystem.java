package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/20/2019.
 * #1233 https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
 */
public class RemoveSubFoldersFromTheFilesystem {

	// time O(n*d), d = average length of folder paths
	public List<String> removeSubfolders(String[] folder) {
		List<String> res = new ArrayList<>();
		Arrays.sort(folder);
		String root = "";
		for(int i = 0; i < folder.length; i++){
			if(!root.equals("") && folder[i].startsWith(root + "/")){
				continue;
			}else{
				root = folder[i];
				res.add(root);
			}
		}
		return res;
	}
}

package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/16/2019.
 * #527 https://leetcode.com/problems/word-abbreviation/
 */
public class WordAbbreviation {
	public List<String> wordsAbbreviation(List<String> dict) {
		if(dict == null || dict.size() == 0){
			return new ArrayList<String>();
		}
		String[] res = new String[dict.size()];
		Map<String, List<Integer>> map = new HashMap<>();
		for(int i = 0; i < dict.size(); i++){
			String s = dict.get(i);
			String abbre = s.length() <= 3 ? s : toAbbreviation(s);
			if(s.length() > 3){
				map.putIfAbsent(abbre, new ArrayList<>());
				map.get(abbre).add(i);
			}
			res[i] = abbre;
		}
		for(Map.Entry<String, List<Integer>> entry : map.entrySet()){
			List<Integer> lst = entry.getValue();
			if(lst.size() == 1){
				continue;
			}
			resolveConflict(1, dict, lst, res);
		}
		return Arrays.asList(res);
	}

	private String toAbbreviation(String s){
		return "" + s.charAt(0) + (s.length() - 2) + s.charAt(s.length() - 1);
	}

	private void resolveConflict(int len, List<String> dict, List<Integer> lst, String[] res){
		if(lst.size() == 1){
			int index = lst.get(0);
			String s = dict.get(index);
			int w = s.length() - len - 1;
			if(w <= 1){
				res[index] = s;
			}else{
				String cand = s.substring(0, len) + w + s.charAt(s.length() - 1);
				res[index] = cand;
			}
			return;
		}
		boolean check = true;
		if(len >= dict.get(lst.get(0)).length() - 2){
			check = false;
		}
		Map<String, List<Integer>> map = new HashMap<>();
		for(int i = 0; i < lst.size(); i++){
			int j = lst.get(i);
			String s = dict.get(j);
			if(!check){
				res[j] = s;
				continue;
			}
			String prefix = s.substring(0, len + 1);
			map.putIfAbsent(prefix, new ArrayList<>());
			map.get(prefix).add(j);
		}
		for(Map.Entry<String, List<Integer>> entry : map.entrySet()){
			List<Integer> conf = entry.getValue();
			resolveConflict(len + 1, dict, conf, res);
		}
	}
}

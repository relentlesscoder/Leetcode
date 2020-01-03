package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 11/25/19.
 * #1268 https://leetcode.com/problems/search-suggestions-system/
 */
public class SearchSuggestionsSystem {
	// Trie solution - https://leetcode.com/problems/search-suggestions-system/discuss/436151/JavaPython-3-Simple-Trie-and-Binary-Search-codes-w-comment-and-brief-analysis.
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		Map<String, List<String>> prefix = new HashMap<>();
		for(String s : products){
			int n = s.length();
			for(int i = 1; i <= n; i++){
				String p = s.substring(0, i);
				prefix.putIfAbsent(p, new ArrayList<>());
				addToQueue(prefix.get(p), s);
			}
		}
		List<List<String>> res = new ArrayList<>();
		int l = searchWord.length();
		for(int i = 1; i <= l; i++){
			String p = searchWord.substring(0, i);
			List<String> lst = new ArrayList<>();
			if(prefix.containsKey(p)){
				lst.addAll(prefix.get(p));
			}
			res.add(lst);
		}
		return res;
	}

	private void addToQueue(List<String> list, String s){
		if(list.size() == 0){
			list.add(s);
			return;
		}
		int i = 0;
		for(; i < list.size(); i++){
			if(s.compareTo(list.get(i)) <= 0){
				break;
			}
		}
		list.add(i, s);
		if(list.size() <= 3){
			return;
		}else{
			while(list.size() > 3){
				list.remove(list.size() - 1);
			}
		}
	}
}

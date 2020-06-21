package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 05/17/2020.
 * #1452 https://leetcode.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list/
 */
public class PeopleWhoseListOfFavoriteCompaniesIsNotASubsetOfAnotherList {
	public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
		List<Integer> res = new ArrayList<>();
		int n = favoriteCompanies.size();
		Integer[] index = new Integer[n];
		for(int i = 0; i < n; i++){
			index[i] = i;
		}
		Arrays.sort(index, (a, b) -> favoriteCompanies.get(a).size() - favoriteCompanies.get(b).size());
		int max = favoriteCompanies.get(index[n - 1]).size();
		Map<String, Set<Integer>> map = new HashMap<>();
		for(int i = n - 1; i >= 0; i--){
			List<String> cur = favoriteCompanies.get(index[i]);
			if(cur.size() == max || !findSubset(cur, map)){
				res.add(index[i]);
			}
			for(String s : cur){
				map.putIfAbsent(s, new HashSet<>());
				map.get(s).add(index[i]);
			}
		}
		Collections.sort(res);
		return res;
	}

	private boolean findSubset(List<String> list, Map<String, Set<Integer>> map){
		if(!map.containsKey(list.get(0))){
			return false;
		}
		Set<Integer> cur = map.get(list.get(0));
		for(int i = 1; i < list.size(); i++){
			Set<Integer> next = new HashSet<>();
			for(int j : map.getOrDefault(list.get(i), new HashSet<>())){
				if(cur.contains(j)){
					next.add(j);
				}
			}
			cur = next;
		}
		return cur.size() > 0;
	}
}

package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/27/2020.
 * #1600 https://leetcode.com/problems/throne-inheritance/
 */
public class ThroneInheritance {

	private Map<String, List<String>> birthMap;

	private Set<String> deathSet;

	private String kingName;

	public ThroneInheritance(String kingName) {
		this.kingName = kingName;
		birthMap = new HashMap<>();
		deathSet = new HashSet<>();
		birthMap.put(kingName, new ArrayList<>());
	}

	public void birth(String parentName, String childName) {
		birthMap.putIfAbsent(parentName, new ArrayList<>());
		birthMap.get(parentName).add(childName);
	}

	public void death(String name) {
		deathSet.add(name);
	}

	public List<String> getInheritanceOrder() {
		List<String> order = new ArrayList<>();
		dfs(kingName, order);
		return order;
	}

	private void dfs(String cur, List<String> order){
		if(!deathSet.contains(cur)){
			order.add(cur);
		}
		if(!birthMap.containsKey(cur)){
			return;
		}
		for(String child : birthMap.get(cur)){
			dfs(child, order);
		}
	}
}

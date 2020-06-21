package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 08/06/2019.
 * #0811 https://leetcode.com/problems/subdomain-visit-count/
 */
public class SubdomainVisitCount {
	// time O(n*d^2)
	public List<String> subdomainVisits(String[] cpdomains) {
		Map<String, Integer> map = new HashMap<>();
		for(String cp : cpdomains){
			String[] strs = cp.split("\\s");
			int count = Integer.parseInt(strs[0]);
			String domain = strs[1], parent = "";
			map.put(domain, map.getOrDefault(domain, 0) + count);
			for(int i = 0; i < domain.length(); i++){
				if(domain.charAt(i) == '.'){
					parent = domain.substring(i + 1);
					map.put(parent, map.getOrDefault(parent, 0) + count);
				}
			}
		}
		List<String> res = new ArrayList<>();
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			res.add(entry.getValue() + " " + entry.getKey());
		}
		return res;
	}
}

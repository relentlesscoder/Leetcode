package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/21/2016.
 * #0399 https://leetcode.com/problems/evaluate-division/
 */
public class EvaluateDivision {
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		double[] res = new double[queries.size()];
		Map<String, String> root = new HashMap<>();
		Map<String, Double> dist = new HashMap<>();
		for(int i = 0; i < values.length; i++){
			String n1 = equations.get(i).get(0), n2 = equations.get(i).get(1);
			String r1 = find(root, dist, n1);
			String r2 = find(root, dist, n2);
			root.put(r1, r2);
			dist.put(r1, dist.get(n2) * values[i] / dist.get(n1));
		}
		for(int i = 0; i < queries.size(); i++){
			String n1 = queries.get(i).get(0), n2 = queries.get(i).get(1);
			if(!root.containsKey(n1) || !root.containsKey(n2)){
				res[i] = -1.0;
				continue;
			}
			String r1 = find(root, dist, n1);
			String r2 = find(root, dist, n2);
			if(!r1.equals(r2)){
				res[i] = -1.0;
				continue;
			}
			res[i] = (double) dist.get(n1) / dist.get(n2);
		}
		return res;
	}

	private String find(Map<String, String> root, Map<String, Double> dist, String s){
		if(!root.containsKey(s)){
			root.put(s, s);
			dist.put(s, 1.0);
			return s;
		}
		if(root.get(s).equals(s)){
			return s;
		}
		String p = root.get(s);
		String r = find(root, dist, p);
		root.put(s, r);
		dist.put(s, dist.get(s) * dist.get(p));
		return r;
	}
}

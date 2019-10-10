package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/21/2016.
 * #399 https://leetcode.com/problems/evaluate-division/
 */
public class EvaluateDivision {
	//O(m*n), graph & DFS
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		if (equations == null || values == null || queries == null) {
			return new double[0];
		}
		int qLen = queries.length;
		double[] res = new double[qLen];
		Map<String, List<KeyValObj>> map = new HashMap<String, List<KeyValObj>>();
		int len = equations.length;
		for (int i = 0; i < len; i++) {
			String[] equ = equations[i];
			double val = values[i];
			if (map.containsKey(equ[0])) {
				List<KeyValObj> kv = map.get(equ[0]);
				kv.add(new KeyValObj(equ[1], val));
			} else {
				List<KeyValObj> kv = new ArrayList<KeyValObj>();
				kv.add(new KeyValObj(equ[1], val));
				map.put(equ[0], kv);
			}

			if (map.containsKey(equ[1])) {
				List<KeyValObj> kv = map.get(equ[1]);
				kv.add(new KeyValObj(equ[0], 1.0 / val));
			} else {
				List<KeyValObj> kv = new ArrayList<KeyValObj>();
				kv.add(new KeyValObj(equ[0], 1.0 / val));
				map.put(equ[1], kv);
			}
		}

		for (int i = 0; i < qLen; i++) {
			String[] qu = queries[i];
			if (!map.containsKey(qu[0]) || !map.containsKey(qu[1])) {
				res[i] = -1.0;
				continue;
			}
			if (qu[0].equals(qu[1])) {
				res[i] = 1.0;
				continue;
			}
			Set<String> visited = new HashSet<String>();
			List<KeyValObj> exps = new ArrayList<KeyValObj>();
			double[] temp = new double[1];
			temp[0] = -1.0;
			calcEquationDFS(qu[0], qu[1], map, visited, exps, temp);
			res[i] = temp[0];
		}
		return res;
	}

	private void calcEquationDFS(String q, String t, Map<String, List<KeyValObj>> map,
	                             Set<String> visited, List<KeyValObj> exps, double[] temp) {
		if (q.equals(t)) {
			double res = 1.0;
			for (KeyValObj kvb : exps) {
				res *= kvb.val;
			}
			temp[0] = res;
			return;
		} else {
			List<KeyValObj> lst = map.get(q);
			for (KeyValObj kvb : lst) {
				if (!visited.contains(kvb.exp)) {
					visited.add(kvb.exp);
					exps.add(kvb);
					calcEquationDFS(kvb.exp, t, map, visited, exps, temp);
					exps.remove(exps.size() - 1);
					visited.remove(kvb.exp);
				}
			}
		}
	}
}

class KeyValObj {
	String exp;
	double val;

	public KeyValObj(String exp, double val) {
		this.exp = exp;
		this.val = val;
	}
}

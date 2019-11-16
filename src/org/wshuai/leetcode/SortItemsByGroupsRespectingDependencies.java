package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/16/2019.
 * #1203 https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/
 */
public class SortItemsByGroupsRespectingDependencies {
	// double topological sort
	public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		int M = m;
		for(int i = 0; i < n; i++){
			if(group[i] == -1){
				group[i] = M++;
			}
			map.putIfAbsent(group[i], new ArrayList<>());
			map.get(group[i]).add(i);
		}
		Map<Integer, List<Integer>> gs = new HashMap<>();
		Map<Integer, List<Integer>> is = new HashMap<>();
		int[] gd = new int[M];
		int[] id = new int[n];
		boolean[] gv = new boolean[M];
		boolean[] iv = new boolean[n];
		for(int v = 0; v < beforeItems.size(); v++){
			List<Integer> lst = beforeItems.get(v);
			if(lst.size() == 0){
				continue;
			}
			for(int u : lst){
				if(group[u] == group[v]){
					is.putIfAbsent(u, new ArrayList<>());
					is.get(u).add(v);
					id[v]++;
				}else{
					gs.putIfAbsent(group[u], new ArrayList<>());
					gs.get(group[u]).add(group[v]);
					gd[group[v]]++;
				}
			}
		}
		LinkedList<Integer> q1 = new LinkedList<>();
		LinkedList<Integer> q2 = new LinkedList<>();
		for(int i = 0; i < M; i++){
			if(gd[i] == 0 && !gv[i]){
				gv[i] = true;
				q1.offerLast(i);
			}
		}
		int[] res = new int[n];
		Arrays.fill(res, - 1);
		int c = 0;
		while(!q1.isEmpty()){
			int s1 = q1.size();
			while(s1-- > 0){
				int curr = q1.pollFirst();
				List<Integer> nodes = map.get(curr);
				if(nodes == null){
					continue;
				}
				for(int j : nodes){
					if(id[j] == 0 && !iv[j]){
						iv[j] = true;
						q2.offerLast(j);
					}
				}
				while(!q2.isEmpty()){
					int s2 = q2.size();
					while(s2-- > 0){
						int currNode = q2.pollFirst();
						res[c++] = currNode;
						List<Integer> nextNodes = is.get(currNode);
						if(nextNodes == null){
							continue;
						}
						for(int k = 0; k < nextNodes.size(); k++){
							id[nextNodes.get(k)]--;
						}
					}
					if(q2.isEmpty()){
						for(int x : nodes){
							if(id[x] == 0 && !iv[x]){
								iv[x] = true;
								q2.offerLast(x);
							}
						}
					}
				}
				List<Integer> next = gs.get(curr);
				if(next == null){
					continue;
				}
				for(int i = 0; i < next.size(); i++){
					gd[next.get(i)]--;
				}
			}
			if(q1.isEmpty()){
				for(int i = 0; i < M; i++){
					if(gd[i] == 0 && !gv[i]){
						gv[i] = true;
						q1.offerLast(i);
					}
				}
			}
		}
		return res[n - 1] == -1 ? new int[0] : res;
	}
}

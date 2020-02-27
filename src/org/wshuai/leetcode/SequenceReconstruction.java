package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/11/2019.
 * #0444 https://leetcode.com/problems/sequence-reconstruction/
 */
public class SequenceReconstruction {

/*	This question can be simplified to three conditions:
	TopSort order exists
	Whether the TopSort order is the only one (Uniqueness of Topological sort, Hamilton path, see https://en.wikipedia.org/wiki/Topological_sorting#Uniqueness).如果不是，那么说明有些pair只有偏序关系，没有全序关系，这样不能完全确定元素之间的顺序
			the only top sort order constructed should be equal to the org.
			index == org.length (check condition 3) && index == map.size() (check all the vertex in the graph has been visited, so the top sort order exists, check condition 1)
	How to check only one order? queue.size() should always be one, then only one element at a time has in-degree to be 0, so you only have one choice (check condition 2)*/
	public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		Map<Integer, Integer> indegree = new HashMap<>();

		for(List<Integer> seq: seqs) {
			if(seq.size() == 1) {
				if(!map.containsKey(seq.get(0))) {
					map.put(seq.get(0), new HashSet<>());
					indegree.put(seq.get(0), 0);
				}
			} else {
				for(int i = 0; i < seq.size() - 1; i++) {
					if(!map.containsKey(seq.get(i))) {
						map.put(seq.get(i), new HashSet<>());
						indegree.put(seq.get(i), 0);
					}

					if(!map.containsKey(seq.get(i + 1))) {
						map.put(seq.get(i + 1), new HashSet<>());
						indegree.put(seq.get(i + 1), 0);
					}

					if(map.get(seq.get(i)).add(seq.get(i + 1))) {
						indegree.put(seq.get(i + 1), indegree.get(seq.get(i + 1)) + 1);
					}
				}
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for(Map.Entry<Integer, Integer> entry: indegree.entrySet()) {
			if(entry.getValue() == 0) queue.offer(entry.getKey());
		}

		int index = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			if(size > 1) return false;
			int curr = queue.poll();
			if(index == org.length || curr != org[index++]) return false;
			for(int next: map.get(curr)) {
				indegree.put(next, indegree.get(next) - 1);
				if(indegree.get(next) == 0) queue.offer(next);
			}
		}
		return index == org.length && index == map.size();
	}
}

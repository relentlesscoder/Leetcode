package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 09/13/2019.
 * #0433 https://leetcode.com/problems/minimum-genetic-mutation/
 */
public class MinimumGeneticMutation {
	// time O(8*4*8*n)
	public int minMutation(String start, String end, String[] bank) {
		int level = 0;
		Set<String> set = new HashSet<>(), visited = new HashSet<>();
		for(String g: bank){
			set.add(g);
		}
		char[] arr = new char[]{'A', 'C', 'G', 'T'};
		LinkedList<String> queue = new LinkedList<>();
		queue.offer(start);
		visited.add(start);
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				String val = queue.poll();
				if(val.equals(end)){
					return level;
				}
				char[] curr = val.toCharArray();
				for(int i = 0; i < curr.length; i++){
					char c = curr[i];
					for(int j = 0; j < 4; j++){
						curr[i] = arr[j];
						String per = new String(curr);
						if(set.contains(per) && !visited.contains(per)){
							visited.add(per);
							queue.offer(per);
						}
						curr[i] = c;
					}
				}
			}
			level++;
		}
		return -1;
	}

	// time O(8*4*8*n)
	public int minMutationBidirectionalBFSBitMask(String start, String end, String[] bank) {
		int step = 0, mask = 3;
		Set<Integer> map = new HashSet<>(), head = new HashSet<>(), tail = new HashSet<>(), visited = new HashSet<>();
		for(String b : bank){
			if(b.equals(start)){
				continue;
			}
			map.add(toGeneCode(b));
		}
		int endCode = toGeneCode(end);
		if(!map.contains(endCode)){
			return -1;
		}
		map.remove(endCode);
		tail.add(endCode);
		head.add(toGeneCode(start));
		while(head.size() > 0 && tail.size() > 0){
			Set<Integer> newHead = new HashSet<>();
			if(tail.size() < head.size()){
				Set<Integer> temp = head;
				head = tail;
				tail = temp;
			}
			for(int cur : head){
				visited.add(cur);
				for(int i = 0; i < 16; i += 2){
					int val = (cur >> i) & mask;
					for(int j = 0; j < 4; j++){
						if(val == j){
							continue;
						}
						int next = cur - (val << i) + (j << i);
						if(tail.contains(next)){
							return step + 1;
						}
						if(visited.contains(next) || !map.contains(next)){
							continue;
						}
						newHead.add(next);
					}
				}
			}
			head = newHead;
			step++;
		}
		return -1;
	}

	private int toGeneCode(String gene){
		int res = 0;
		for(int i = 0; i < 8; i++){
			char c = gene.charAt(i);
			res = (res << 2) + mapToInt(c);
		}
		return res;
	}

	private int mapToInt(char c){
		if(c == 'C'){
			return 1;
		}
		if(c == 'G'){
			return 2;
		}
		if(c == 'T'){
			return 3;
		}
		return 0;
	}
}

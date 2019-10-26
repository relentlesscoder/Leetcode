package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 10/25/19.
 * #752 https://leetcode.com/problems/open-the-lock/
 */
public class OpenTheLock {
	public int openLock(String[] deadends, String target) {
		Set<String> dd = new HashSet<>();
		Set<String> visited = new HashSet<>();
		for(String s: deadends){
			dd.add(s);
		}
		LinkedList<String> queue = new LinkedList<>();
		queue.offer("0000");
		visited.add("0000");
		int res = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size > 0){
				String curr = queue.poll();
				if(dd.contains(curr)){
					size--;
					continue;
				}
				if(curr.equals(target)){
					return res;
				}
				for(int i = 0; i < 4; i++){
					char c = curr.charAt(i);
					String s1 = curr.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + curr.substring(i + 1);
					if(!dd.contains(s1) && !visited.contains(s1)){
						queue.offer(s1);
						visited.add(s1);
					}
					String s2 = curr.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + curr.substring(i + 1);
					if(!dd.contains(s2) && !visited.contains(s2)){
						queue.offer(s2);
						visited.add(s2);
					}
				}
				size--;
			}
			res++;
		}
		return -1;
	}
}

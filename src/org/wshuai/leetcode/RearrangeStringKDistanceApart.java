package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/26/2019.
 * #0358 https://leetcode.com/problems/rearrange-string-k-distance-apart/
 */
public class RearrangeStringKDistanceApart {

	// time O(n*log(d)), space O(d), d <= 26 is count of unique characters in s
	public String rearrangeString(String s, int k) {
		if(s == null || s.length() == 0){
			return "";
		}
		if(k == 0){
			return s;
		}
		StringBuilder sb = new StringBuilder();
		int[] count = new int[26];
		for(char c : s.toCharArray()){
			count[c - 'a']++;
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ?
				a[0] - b[0] : b[1] - a[1]);
		for(int i = 0; i < 26; i++){
			if(count[i] == 0){
				continue;
			}
			pq.offer(new int[]{i, count[i]});
		}
		LinkedList<int[]> queue = new LinkedList<>();
		while(!pq.isEmpty()){
			int[] cur = pq.poll();
			sb.append((char)(cur[0] + 'a'));
			cur[1]--;
			queue.offerLast(cur);
			if(queue.size() >= k){
				int[] front = queue.pollFirst();
				if(front[1] > 0){
					pq.offer(front);
				}
			}
		}
		return sb.length() == s.length() ? sb.toString() : "";
	}
}

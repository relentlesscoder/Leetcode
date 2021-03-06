package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/26/2019.
 * #0358 https://leetcode.com/problems/rearrange-string-k-distance-apart/
 */
public class RearrangeStringKDistanceApart {
	// time O(n/k*log(d)), space O(d), d is count of unique characters in s
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
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);
		for(int i = 0; i < 26; i++){
			if(count[i] == 0){
				continue;
			}
			pq.offer(new int[]{i, count[i]});
		}
		while(pq.size() >= k){
			List<int[]> next = new ArrayList<>();
			for(int i = 0; i < k; i++){
				next.add(pq.poll());
			}
			for(int[] cur : next){
				sb.append((char)('a' + cur[0]));
				cur[1]--;
				if(cur[1] > 0){
					pq.offer(cur);
				}
			}
		}
		while(!pq.isEmpty()){
			int[] cur = pq.poll();
			if(cur[1] > 1){
				return "";
			}
			sb.append((char)('a' + cur[0]));
		}
		return sb.toString();
	}
}

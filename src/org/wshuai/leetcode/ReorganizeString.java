package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/22/2019.
 * #0767 https://leetcode.com/problems/reorganize-string/
 */
public class ReorganizeString {

	// time O(n*log(d)), space O(d), d <= 26 is count of unique characters in s
	public String reorganizeString(String S) {
		if(S == null || S.length() == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int[] count = new int[26];
		for(char c : S.toCharArray()){
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
			if(queue.size() >= 2){
				int[] front = queue.pollFirst();
				if(front[1] > 0){
					pq.offer(front);
				}
			}
		}
		return sb.length() == S.length() ? sb.toString() : "";
	}
}

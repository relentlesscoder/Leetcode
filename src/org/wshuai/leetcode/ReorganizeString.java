package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/22/2019.
 * #0767 https://leetcode.com/problems/reorganize-string/
 */
public class ReorganizeString {

	// time O(n*log(26))
	public String reorganizeString(String S) {
		int[] count = new int[26];
		for(char c : S.toCharArray()){
			count[c - 'a']++;
		}
		StringBuilder res = new StringBuilder();
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
		for(int i = 0; i < 26; i++){
			if(count[i] == 0){
				continue;
			}
			pq.offer(new int[]{count[i], i});
		}
		while(pq.size() >= 2){
			int[] last = pq.poll(), prev = pq.poll();
			res.append((char)(last[1] + 'a'));
			res.append((char)(prev[1] + 'a'));
			if(--last[0] > 0){
				pq.offer(last);
			}
			if(--prev[0] > 0){
				pq.offer(prev);
			}
		}
		if(!pq.isEmpty()){
			if(pq.peek()[0] > 1){
				return "";
			}else{
				res.append((char)(pq.peek()[1] + 'a'));
			}
		}
		return res.toString();
	}
}

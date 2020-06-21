package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/22/2019.
 * #0767 https://leetcode.com/problems/reorganize-string/
 */
public class ReorganizeString {
	// time O(n*log(26))
	public String reorganizeString(String S) {
		StringBuilder res = new StringBuilder();
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		int[] count = new int[26];
		for(char c : S.toCharArray()){
			count[c - 'a']++;
		}
		for(int i = 0; i < 26; i++){
			if(count[i] > 0){
				pq.offer(new int[]{i, count[i]});
			}
		}
		while(pq.size() > 1){
			int[] arr1 = pq.poll(), arr2 = pq.poll();
			res.append((char)('a' + arr1[0]));
			res.append((char)('a' + arr2[0]));
			if(--arr1[1] > 0){
				pq.offer(arr1);
			}
			if(--arr2[1] > 0){
				pq.offer(arr2);
			}
		}
		if(pq.size() > 0){
			if(pq.peek()[1] > 1){
				return "";
			}
			res.append((char)('a' + pq.peek()[0]));
		}
		return res.toString();
	}
}

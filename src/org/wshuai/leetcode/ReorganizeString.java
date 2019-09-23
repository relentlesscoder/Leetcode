package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 9/22/19.
 * #767 https://leetcode.com/problems/reorganize-string/
 */
public class ReorganizeString {
	public String reorganizeString(String S) {
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		int[] count = new int[26];
		for(char c: S.toCharArray()){
			count[c-'a']++;
		}
		for(int i = 0; i < 26; i++){
			if(count[i] > 0){
				queue.offer(new int[]{i, count[i]});
			}
		}
		StringBuilder sb = new StringBuilder();
		while(queue.size() > 1){
			int[] first = queue.poll();
			int[] second = queue.poll();
			sb.append("" + (char)(first[0]+'a'));
			sb.append("" + (char)(second[0]+'a'));
			if(first[1] > 1){
				first[1]--;
				queue.offer(first);
			}
			if(second[1] > 1){
				second[1]--;
				queue.offer(second);
			}
		}
		if(queue.size() > 0){
			if(queue.peek()[1] > 1){
				return "";
			}else{
				sb.append("" + (char)(queue.peek()[0]+'a'));
			}
		}
		return sb.toString();
	}
}

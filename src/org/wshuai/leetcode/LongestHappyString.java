package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 04/07/2020.
 * #1405 https://leetcode.com/problems/longest-happy-string/
 */
public class LongestHappyString {
	public String longestDiverseString(int a, int b, int c) {
		StringBuilder sb = new StringBuilder();
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[1] - x[1]);
		pq.offer(new int[]{0, a});
		pq.offer(new int[]{1, b});
		pq.offer(new int[]{2, c});
		while(true){
			int[] most = pq.poll();
			if(most[1] == 0){
				break;
			}
			int n = sb.length();
			char cur = (char)(most[0] + 'a');
			// if the last two character does not equal the current most, append it.
			// otherwise try appending the second most.
			if(n < 2 || sb.charAt(n - 2) != cur || sb.charAt(n - 1) != cur){
				sb.append(cur);
				most[1]--;
			}else{
				if(pq.peek()[1] == 0){
					break;
				}
				int[] second = pq.poll();
				cur = (char)(second[0] + 'a');
				sb.append(cur);
				second[1]--;
				pq.offer(second);
			}
			pq.offer(most);
		}
		return sb.toString();
	}
}

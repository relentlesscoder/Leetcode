package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 12/30/2019.
 * #1306 https://leetcode.com/problems/jump-game-iii/
 */
public class JumpGameIII {
	public boolean canReach(int[] arr, int start) {
		boolean[] visited = new boolean[arr.length];
		visited[start] = true;
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offerLast(start);
		while(!queue.isEmpty()){
			int cur = queue.pollFirst();
			if(arr[cur] == 0){
				return true;
			}
			int left = cur - arr[cur];
			int right = cur + arr[cur];
			if(left >= 0 && !visited[left]){
				queue.offerLast(left);
				visited[left] = true;
			}
			if(right < arr.length && !visited[right]){
				queue.offerLast(right);
				visited[right] = true;
			}
		}
		return false;
	}
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2020.
 * #1601 https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests/
 */
public class MaximumNumberOfAchievableTransferRequests {

	// time O((r + n)*2^r), space O(n*2^r)
	public int maximumRequests(int n, int[][] requests) {
		int selfConnected = 0, size = requests.length, i = 0;
		for(int[] req : requests){
			if(req[0] == req[1]){
				selfConnected++;
				size--;
			}
		}
		int[][] filtered = new int[size][2];
		for(int[] req : requests){
			if(req[0] != req[1]){
				filtered[i++] = req;
			}
		}
		return selfConnected + maxRequests(n, filtered);
	}

	private int maxRequests(int n, int[][] requests){
		int max = 0, mask = (1 << requests.length) - 1;
		for(int i = mask; i > 0; i--){
			if(Integer.bitCount(i) <= max){
				continue;
			}
			int[] degree = new int[n];
			for(int j = 0; j < requests.length; j++){
				if(((i >> j) & 1) == 1){
					degree[requests[j][0]]--;
					degree[requests[j][1]]++;
				}
			}
			boolean valid = true;
			for(int k = 0; k < n; k++){
				if(degree[k] != 0){
					valid = false;
					break;
				}
			}
			if(valid){
				max = Math.max(max, Integer.bitCount(i));
			}
		}
		return max;
	}
}

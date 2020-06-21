package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/06/2019.
 * #0826 https://leetcode.com/problems/most-profit-assigning-work/
 */
public class MostProfitAssigningWork {

	// time O(d*log(d)), d = Math.max(m, n), m = profit.length, n = worker.length
	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		int res = 0, d = difficulty.length;
		Arrays.sort(worker);
		Integer[] index = new Integer[d];
		for(int i = 0; i < d; i++){
			index[i] = i;
		}
		Arrays.sort(index, (a, b) -> difficulty[a] - difficulty[b]);
		// for each worker[i], we need to assign the max possible profit of job that
		// has a difficulty level less or equal to worker[i]
		for(int i = 0, j = 0, max = 0; i < worker.length;){
			if(j < d && worker[i] >= difficulty[index[j]]){
				max = Math.max(max, profit[index[j++]]);
			}else{
				res += max;
				i++;
			}
		}
		return res;
	}
}

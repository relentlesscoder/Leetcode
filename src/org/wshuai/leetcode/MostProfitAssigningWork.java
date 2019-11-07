package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/6/2019.
 * #826 https://leetcode.com/problems/most-profit-assigning-work/
 */
public class MostProfitAssigningWork {
	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		int res = 0;
		Arrays.sort(worker);
		Integer[] index = new Integer[difficulty.length];
		for(int i = 0; i < index.length; i++){
			index[i] = i;
		}
		Arrays.sort(index, (a, b) -> difficulty[a] - difficulty[b]);
		int i = 0;
		int j = 0;
		int max = 0;
		while(j < worker.length){
			if(i < index.length && difficulty[index[i]] <= worker[j]){
				// save the max profit thus far
				// it is possible that a more difficult
				// job has lower profit
				max = Math.max(profit[index[i]], max);
				i++;
			}else{
				if(i > 0){
					res += max;
				}
				j++;
			}
		}
		return res;
	}
}

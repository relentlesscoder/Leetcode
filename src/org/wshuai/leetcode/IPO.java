package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 12/25/2019.
 * #502 https://leetcode.com/problems/ipo/
 */
public class IPO {
	// greedy
	public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
		PriorityQueue<int[]> pqCapital = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		PriorityQueue<int[]> pqProfit = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		for(int i = 0; i < Profits.length; i++){
			pqCapital.offer(new int[]{Capital[i], Profits[i]});
		}
		for(int i = 0; i < k; i++){
			// W will only increase so all projects that are already
			// in the capital queue will be eligible for future query
			while(!pqCapital.isEmpty() && pqCapital.peek()[0] <= W){
				pqProfit.offer(pqCapital.poll());
			}
			// no more project can be selected
			if(pqProfit.isEmpty()){
				break;
			}
			W += pqProfit.poll()[1];
		}
		return W;
	}
}

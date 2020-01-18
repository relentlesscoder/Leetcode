package org.wshuai.leetcode;

/**
 * Created by Wei on 10/06/2016.
 * #0134 https://leetcode.com/problems/gas-station/solution/
 */
public class GasStation {
	// time O(n)
	public int canCompleteCircuit(int[] gas, int[] cost) {
		if (gas == null || cost == null || gas.length != cost.length) {
			return -1;
		}
		int len = gas.length, sum = 0, total = 0, start = 0;
		for (int i = 0; i < len; i++) {
			total += (gas[i] - cost[i]);
			sum += (gas[i] - cost[i]);
			if (sum < 0) {
				sum = 0;
				start = i + 1;
			}
		}
		return total < 0 ? -1 : start;
	}
}

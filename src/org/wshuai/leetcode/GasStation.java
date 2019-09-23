package org.wshuai.leetcode;

/**
 * Created by Wei on 10/6/16.
 */
public class GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		if (gas == null || cost == null || gas.length != cost.length) {
			return -1;
		}
		int len = gas.length;
		int sum = 0;
		int total = 0;
		int start = 0;
		for (int i = 0; i < len; i++) {
			total += (gas[i] - cost[i]);
			sum += (gas[i] - cost[i]);
			if (sum < 0) {
				sum = 0;
				start = i + 1;
			}
		}

		if (total < 0) {
			return -1;
		}
		return start;
	}
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 09/29/2023.
 * #2073 https://leetcode.com/problems/time-needed-to-buy-tickets/
 */
public class TimeNeededToBuyTickets {

	// time O(n), space O(1)
	public int timeRequiredToBuy(int[] tickets, int k) {
		int res = 0, n = tickets.length;
		int ticketsKthPersonLikeToBuy = tickets[k];
		for (int i = 0; i < n; i++) {
			// For example, k = 3 for the below array
			// 2 3 5 4 6 2
			// 1 2 4 3 5 1
			// 0 1 3 2 4 0
			// 0 0 2 1 3 0
			// 0 0 1 0 2 0
			// So for index less than or equal to k, each person will take min(tickets[i], tickets[k]) seconds,
			// for index greater than k, each person will take min(tickets[i], tickets[k] - 1) seconds since
			// for the last round the index after k will not be processed when k is done
			res += (i <= k ? Math.min(tickets[i], ticketsKthPersonLikeToBuy) : Math.min(tickets[i], ticketsKthPersonLikeToBuy - 1));
		}
		return res;
	}
}

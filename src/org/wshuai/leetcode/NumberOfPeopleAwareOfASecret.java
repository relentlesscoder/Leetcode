package org.wshuai.leetcode;

/**
 * Created by Wei on 09/23/2023.
 * #2327 https://leetcode.com/problems/number-of-people-aware-of-a-secret/
 */
public class NumberOfPeopleAwareOfASecret {

	// time O(n), space O(n)
	public int peopleAwareOfSecret(int n, int delay, int forget) {
		long mod = (long) (1e9 + 7), peopleKnowsSecretAtDayN = 0, peopleSharingSecret = 0;
		long[] peopleStartToKnowTheSecret = new long[n + 1];
		peopleStartToKnowTheSecret[1] = 1L;
		for (int i = 2; i <= n; i++) {
			peopleSharingSecret = (peopleSharingSecret + peopleStartToKnowTheSecret[Math.max(0, i - delay)] - peopleStartToKnowTheSecret[Math.max(0, i - forget)] + mod) % mod;
			peopleStartToKnowTheSecret[i] = peopleSharingSecret;
		}
		for (int i = n - forget + 1; i <= n; i++) {
			peopleKnowsSecretAtDayN = (peopleKnowsSecretAtDayN + peopleStartToKnowTheSecret[i]) % mod;
		}
		return (int) peopleKnowsSecretAtDayN;
	}
}

package org.wshuai.leetcode;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by Wei on 10/9/2019.
 * #1175 https://leetcode.com/problems/prime-arrangements/
 */
public class PrimeArrangements {

	private int MOD = 1_000_000_007;

	// https://leetcode.com/problems/prime-arrangements/discuss/371884/Simple-Java-With-comment-sieve_of_eratosthenes
	public int numPrimeArrangements(int n) {
		int noOfPrime = countPrimes(n);
		BigInteger x = factorial(noOfPrime);
		BigInteger y = factorial(n - noOfPrime);
		return x.multiply(y).mod(BigInteger.valueOf(MOD)).intValue();
	}

	private int countPrimes(int n) {
		boolean[] prime = new boolean[n + 1];
		Arrays.fill(prime, 2, n + 1, true);
		for (int i = 2; i * i <= n; i++)
			if (prime[i])
				for (int j = i * i; j <= n; j += i)
					prime[j] = false;
		int cnt = 0;
		for (int i = 0; i < prime.length; i++)
			if (prime[i])
				cnt++;

		return cnt;
	}

	private BigInteger factorial(int n) {
		BigInteger fac = BigInteger.ONE;
		for (int i = 2; i <= n; i++) {
			fac = fac.multiply(BigInteger.valueOf(i));
		}
		return fac.mod(BigInteger.valueOf(MOD));
	}
}

package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 01/13/2020.
 * #1316 https://leetcode.com/problems/distinct-echo-substrings/
 */
public class DistinctEchoSubstrings {
	private static final long base = 26L, mod = 1_000_000_007;

	// time O(n^2), space O(n)
	public int distinctEchoSubstrings(String text) {
		Set<Long> res = new HashSet<>();
		int n = text.length();
		long[] hash = new long[n + 1], pow = new long[n + 1];
		pow[0] = 1L;
		for(int i = 1; i <= n; i++){
			hash[i] = (hash[i - 1] * base + text.charAt(i - 1)) % mod;
			pow[i] = pow[i - 1] * base % mod;
		}
		for(int i = 0; i < n; i++){
			for(int len = 2; i + len - 1 < n; len += 2){
				int mid = i + len / 2;
				long h1 = getHash(i, mid, hash, pow);
				long h2 = getHash(mid, mid + len / 2, hash, pow);
				if(h1 == h2){
					res.add(h1);
				}
			}
		}
		return res.size();
	}

	/*
			pow	hash
		0	1	    0
		1	B^1	c0
		2	B^2	c0*B + c1
		3	B^3	c0*B^2 + c1*B^1 + c2
		4	B^4	c0*B^3 + c1*B^2 + c2*B^1 + c3
		5	B^5	c0*B^4 + c1*B^3 + c2*B^2 + c3*B^1 + c4

		hash(substring(2, 5)) = h5 - h2*pow(5-2)
	 */
	private long getHash(int i, int j, long[] hash, long[] pow){
		return (hash[j] - hash[i] * pow[j - i] % mod + mod) % mod;
	}
}

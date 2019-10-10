package org.wshuai.leetcode;

/**
 * Created by Wei on 7/25/2017.
 * #372 https://leetcode.com/problems/super-pow/
 */
public class SuperPow {
	static final int BASE = 1337;

	//Meaningless math problem, a good explanation at https://discuss.leetcode.com/topic/50489/c-clean-and-short-solution
	public int superPow(int a, int[] b) {
		if (b == null || b.length == 0) {
			return 1;
		}
		int len = b.length;
		return superPowUtil(a, b, len - 1);
	}

	private int superPowUtil(int a, int[] b, int i) {
		if (i < 0) {
			return 1;
		}
		return powmod(superPowUtil(a, b, i - 1), 10) * powmod(a, b[i]) % BASE;
	}

	private int powmod(int a, int k) {
		a %= BASE;
		int result = 1;
		for (int i = 0; i < k; i++) {
			result = (result * a) % BASE;
		}
		return result;
	}
}

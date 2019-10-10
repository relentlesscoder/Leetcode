package org.wshuai.leetcode;

import java.math.BigInteger;

/**
 * Created by Wei on 3/19/17.
 * #306 https://leetcode.com/problems/additive-number/
 */
public class AdditiveNumber {
	public boolean isAdditiveNumber(String num) {
		if (num == null || num.length() < 3) {
			return false;
		}
		BigInteger fst = null;
		BigInteger sec = null;
		return isAdditiveNumberUtil(num, 0, fst, sec, num.length());
	}

	private boolean isAdditiveNumberUtil(String num, int idx, BigInteger fst, BigInteger sec, int len) {
		if (idx >= len) {
			return true;
		}
		for (int i = idx + 1; i <= len; i++) {
			String str = num.substring(idx, i);
			if (str.startsWith("0") && !str.equals("0")) {
				return false;
			}
			BigInteger val = new BigInteger(str);
			BigInteger sub = fst != null ? val.subtract(fst) : null;
			if (fst == null || sec == null || sec.equals(sub)) {
				BigInteger nfst = fst;
				BigInteger nsec = sec;
				if (fst == null) {
					nfst = val;
					if (i >= len) {
						return false;
					}
				} else if (sec == null) {
					nsec = val;
					if (i >= len) {
						return false;
					}
				} else {
					nfst = sec;
					nsec = val;
				}
				if (isAdditiveNumberUtil(num, i, nfst, nsec, len)) {
					return true;
				}
			}
		}
		return false;
	}
}

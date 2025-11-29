package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/18/2019.
 * #1016 https://leetcode.com/problems/binary-string-with-substrings-representing-1-to-n/
 */
public class BinaryStringWithSubstringsRepresentingOneToN {

	// time O(m), space O(n)
	public boolean queryStringSlidingWindow(String s, int n) {
		// https://leetcode.cn/problems/binary-string-with-substrings-representing-1-to-n/solutions/2265097/san-chong-suan-fa-cong-bao-li-dao-you-hu-nmtq/
		if (n == 1) {
			return s.contains("1");
		}
		int k = 31 - Integer.numberOfLeadingZeros(n);
		if (s.length() < Math.max(n - (1 << k) + k + 1, (1 << (k - 1)) + k - 1)) {
			return false;
		}
		return check(s, k, n / 2 + 1, (1 << k) - 1) && check(s, k + 1, 1 << k, n);
	}

	private boolean check(String s, int k, int lower, int upper) {
		if (lower > upper) {
			return true;
		}
		Set<Integer> seen = new HashSet<>();
		int mask = (1 << (k - 1)) - 1;
		int x = Integer.parseInt(s.substring(0, k - 1), 2);
		for (int i = k - 1, m = s.length(); i < m; i++) {
			x = ((x & mask) << 1) | (s.charAt(i) - '0');
			if (lower <= x && x <= upper) {
				seen.add(x);
			}
		}
		return seen.size() == upper - lower + 1;
	}

    // time O(m * log(n)), space O(n)
    public boolean queryString(String s, int n) {
        int m = s.length();
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int x = s.charAt(i) - '0';
            if (x == 0) {
                continue;
            }
            for (int j = i + 1; x <= n; j++) {
                nums.add(x);
                if (j == m) {
                    break;
                }
                int d = s.charAt(j) - '0';
                x = (x << 1) + d;
            }
        }
        return nums.size() == n;
    }
}

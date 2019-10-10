package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/10/2016.
 * #93 https://leetcode.com/problems/restore-ip-addresses/
 */
public class RestoreIPAddresses {
	public List<String> restoreIpAddresses(String s) {
		List<String> lst = new ArrayList<String>();
		if (s == null || s.isEmpty()) {
			return lst;
		}
		int len = s.length();
		List<Integer> pos = new ArrayList<Integer>();
		restoreIpAddressesUtil(s, pos, lst, len);
		return lst;
	}

	private void restoreIpAddressesUtil(String s, List<Integer> pos, List<String> lst, int len) {
		int size = pos.size();
		if (size == 4) {
			if (pos.get(3) == len - 1) {
				int last = 0;
				String x = "";
				for (int idx : pos) {
					x += s.substring(last, idx + 1) + ".";
					last = idx + 1;
				}
				lst.add(x.substring(0, x.length() - 1));
			}
		} else {
			int idx = pos.size() > 0 ? pos.get(pos.size() - 1) : -1;
			for (int i = 1; i <= 3; i++) {
				if (idx + i < len && isValid(s.substring(idx + 1, idx + i + 1))) {
					pos.add(idx + i);
					restoreIpAddressesUtil(s, pos, lst, len);
					pos.remove(pos.size() - 1);
				}
			}
		}
	}

	private boolean isValid(String input) {
		int len = input.length();
		if ((len == 2 || len == 3) && input.charAt(0) == '0') {
			return false;
		}
		int val = Integer.parseInt(input);
		return val >= 0 && val <= 255;
	}
}

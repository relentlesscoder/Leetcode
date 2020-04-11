package org.wshuai.leetcode;

/**
 * Created by Wei on 12/16/2019.
 * #0660 https://leetcode.com/problems/remove-9/
 */
public class RemoveNine {
	// time O(1)
	// https://leetcode.com/problems/remove-9/discuss/106573/Alternative-solution-applicable-to-the-general-case
	public int newInteger(int n) {
		return Integer.parseInt(Integer.toString(n, 9));
	}
}

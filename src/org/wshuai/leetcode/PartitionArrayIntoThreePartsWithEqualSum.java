package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 8/23/19.
 * #1013 https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/
 */
public class PartitionArrayIntoThreePartsWithEqualSum {

	// https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/discuss/260895/Java-9-line-O(n)-time-O(1)-space-code-w-comment
	public boolean canThreePartsEqualSum(int[] A) {
		int sum = Arrays.stream(A).sum();
		int part = 0, cnt = 0;

		if (sum % 3 != 0) {
			return false;
		}
		for (int a : A) {
			part += a;
			if (part != sum / 3) {
				continue;
			}
			if (++cnt == 3) {
				return true;
			}
			part = 0;
		}
		return false;
	}

}

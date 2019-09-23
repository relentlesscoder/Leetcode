package org.wshuai.leetcode;

/**
 * Created by Wei on 3/14/17.
 * #275 https://leetcode.com/problems/h-index-ii/
 */
public class HIndexII {
	public int hIndex(int[] citations) {
		if (citations == null || citations.length == 0) {
			return 0;
		}
		int len = citations.length;
		int i = 0;
		int j = len;
		while (i < j) {
			int mid = i + (j - i) / 2;
			if (citations[mid] == len - mid) {
				return (len - mid);
			} else if (citations[mid] < len - mid) {
				i = mid + 1;
			} else {
				j = mid;
			}
		}
		return len - i;
	}
}

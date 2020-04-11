package org.wshuai.leetcode;

/**
 * Created by Wei on 09/29/2019.
 * #0702 https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/
 */
public class SearchInASortedArrayOfUnknownSize {
	// time O(log(n))
	public int search(ArrayReader reader, int target) {
		int low = 0, high = 1;
		while (reader.get(high) < target) {
			low = high;
			high <<= 1;
		}
		while (low < high) {
			int mid = low + ((high - low) >> 1);
			if (reader.get(mid) < target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return reader.get(low) == target ? low : -1;
	}

	private class ArrayReader {
		public int get(int index) {
			return -1;
		}
	}
}

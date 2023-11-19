package org.wshuai.leetcode;

/**
 * Created by Wei on 08/02/2020.
 * #1533 https://leetcode.com/problems/find-the-index-of-the-large-integer/
 */
public class FindTheIndexOfTheLargeInteger {

	// time O(log(n))
	public int getIndex(ArrayReader reader) {
		return helper(reader, 0, reader.length() - 1, reader.length());
	}

	private int helper(ArrayReader reader, int start, int end, int len) {
		if (start == end) {
			return start;
		}
		int mid = start + (end - start) / 2;
		if (len % 2 == 0) {
			return reader.compareSub(start, mid, mid + 1, end) == 1 ? helper(reader, start, mid, len / 2) : helper(reader, mid + 1, end, len / 2);
		} else {
			int res = reader.compareSub(start, mid - 1, mid + 1, end);
			if (res == 0) {
				return mid;
			}
			return res == 1 ? helper(reader, start, mid - 1, (len - 1) / 2) : helper(reader, mid + 1, end, (len - 1) / 2);
		}
	}


	// This is ArrayReader's API interface.
	// You should not implement it, or speculate about its implementation
	interface ArrayReader {
		// Compares the sum of arr[l..r] with the sum of arr[x..y]
		// return 1 if sum(arr[l..r]) > sum(arr[x..y])
		// return 0 if sum(arr[l..r]) == sum(arr[x..y])
		// return -1 if sum(arr[l..r]) < sum(arr[x..y])
		public int compareSub(int l, int r, int x, int y);

		// Returns the length of the array
		public int length();
	}
}

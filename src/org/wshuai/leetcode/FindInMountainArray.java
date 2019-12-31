package org.wshuai.leetcode;

/**
 * Created by Wei on 12/31/2019.
 * #1095 https://leetcode.com/problems/find-in-mountain-array/
 */
public class FindInMountainArray {
	public int findInMountainArray(int target, MountainArray mountainArr) {
		int n = mountainArr.length(), l, r, m, peak = 0;
		// find index of peak
		l  = 0;
		r = n - 1;
		while (l < r) {
			m = (l + r) / 2;
			if (mountainArr.get(m) < mountainArr.get(m + 1))
				l = peak = m + 1;
			else
				r = m;
		}
		// find target in the left of peak
		l = 0;
		r = peak;
		while (l <= r) {
			m = (l + r) / 2;
			if (mountainArr.get(m) < target)
				l = m + 1;
			else if (mountainArr.get(m) > target)
				r = m - 1;
			else
				return m;
		}
		// find target in the right of peak
		l = peak;
		r = n - 1;
		while (l <= r) {
			m = (l + r) / 2;
			if (mountainArr.get(m) > target)
				l = m + 1;
			else if (mountainArr.get(m) < target)
				r = m - 1;
			else
				return m;
		}
		return -1;
	}
}


// This is MountainArray's API interface.
// You should not implement it, or speculate about its implementation
interface MountainArray {
	public int get(int index);
	public int length();
 }

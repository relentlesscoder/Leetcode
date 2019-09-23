package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 9/27/2016.
 * #281 https://leetcode.com/problems/zigzag-iterator/
 */
public class ZigzagIterator {
	int idx1;
	int idx2;
	int size1;
	int size2;
	private List<Integer> v1;
	private List<Integer> v2;

	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		if (v1 == null || v2 == null) {
			throw new IllegalArgumentException("Invalid input.");
		}
		idx1 = 0;
		idx2 = 0;
		this.v1 = v1;
		this.v2 = v2;
		size1 = v1.size();
		size2 = v2.size();
	}

	public int next() {
		if (idx1 >= size1) {
			int result = v2.get(idx2);
			idx2++;
			return result;
		}
		if (idx2 >= size2) {
			int result = v1.get(idx1);
			idx1++;
			return result;
		}
		if (idx1 == idx2) {
			int result = v1.get(idx1);
			idx1++;
			return result;
		} else {
			int result = v2.get(idx2);
			idx2++;
			return result;
		}
	}

	public boolean hasNext() {
		if (idx1 >= size1 && idx2 >= size2) {
			return false;
		}
		return true;
	}
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

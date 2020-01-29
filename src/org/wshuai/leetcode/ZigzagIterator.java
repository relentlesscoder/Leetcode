package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 09/27/2016.
 * #0281 https://leetcode.com/problems/zigzag-iterator/
 */
public class ZigzagIterator {
	private List<Integer> v1;
	private List<Integer> v2;
	private int i, j;

	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		this.v1 = v1;
		this.v2 = v2;
		i = 0;
		j = 0;
	}

	public int next() {
		if(i >= v1.size()){
			return v2.get(j++);
		}
		if(j >= v2.size()){
			return v1.get(i++);
		}
		return i == j ? v1.get(i++) : v2.get(j++);
	}

	public boolean hasNext() {
		return i < v1.size() || j < v2.size();
	}
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

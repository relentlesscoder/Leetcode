package org.wshuai.leetcode;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Wei on 11/6/16.
 * #251 https://leetcode.com/problems/flatten-2d-vector/
 */
public class Flatten2DVector implements Iterator<Integer> {
	private List<List<Integer>> data;
	private int x;
	private int y;

	public Flatten2DVector(List<List<Integer>> vec2d) {
		data = vec2d;
		x = 0;
		y = 0;
	}

	@Override
	public Integer next() {
		List<Integer> lst = data.get(x);
		int res = lst.get(y);
		if (y < lst.size() - 1) {
			y++;
		} else {
			x++;
			y = 0;
		}
		return res;
	}

	@Override
	public boolean hasNext() {
		if (data == null || data.size() == 0) {
			return false;
		}
		int len1 = data.size();
		if (x < len1) {
			List<Integer> lst = data.get(x);
			while (lst == null || lst.size() == 0) {
				x++;
				if (x == len1) {
					break;
				}
				lst = data.get(x);
			}
			if (x == len1) {
				return false;
			}
			int len2 = lst.size();
			if (y >= len2) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void remove() {
		//fake implementation
	}
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */

package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/11/2019.
 * #1104 https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/
 */
public class PathInZigzagLabelledBinaryTree {
	public List<Integer> pathInZigZagTree(int label) {
		int level = 0;
		int val = label;
		while (val > 0) {
			level++;
			val /= 2;
		}
		// calculate the "real" value based on the level
		if (level % 2 == 0) {
			label = pow(level - 1) + pow(level) - 1 - label;
		}
		List<Integer> res = new ArrayList<>();
		while (label != 0) {
			// use the real value to calculate the parent node value but add the "label" value if the current level is even.
			if (level % 2 == 0) {
				res.add(0, pow(level - 1) + pow(level) - 1 - label);
			} else {
				res.add(0, label);
			}
			label /= 2;
			level--;
		}
		return res;
	}

	private int pow(int p) {
		int x = 1;
		while (p > 0) {
			x *= 2;
			p--;
		}
		return x;
	}
}

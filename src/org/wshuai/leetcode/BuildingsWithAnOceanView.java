package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 02/25/2021.
 * #1762 https://leetcode.com/problems/buildings-with-an-ocean-view/
 */
public class BuildingsWithAnOceanView {

	// time O(n), space O(1)
	public int[] findBuildings(int[] heights) {
		int n = heights.length, maxHeight = -1;
		List<Integer> buildings = new ArrayList<>();
		for (int i = n - 1; i >= 0; i--) {
			if (maxHeight < heights[i]) {
				maxHeight = heights[i];
				buildings.add(i);
			}
		}
		int[] res = new int[buildings.size()];
		for (int i = 0; i < buildings.size(); i++) {
			res[i] = buildings.get(buildings.size() - 1 - i);
		}
		return res;
	}

	// time O(n), space O(n)
	public int[] findBuildingsMonotonicStack(int[] heights) {
		int n = heights.length;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
				stack.pop();
			}
			stack.push(i);
		}
		int[] res = new int[stack.size()];
		for (int i = stack.size() - 1; i >= 0; i--) {
			res[i] = stack.pop();
		}
		return res;
	}

	// time O(n), space O(n)
	public int[] findBuildingsMonotonicStackReverse(int[] heights) {
		int n = heights.length;
		List<Integer> buildings = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				buildings.add(i);
			}
			stack.push(i);
		}
		int[] res = new int[buildings.size()];
		for (int i = 0; i < buildings.size(); i++) {
			res[i] = buildings.get(buildings.size() - 1 - i);
		}
		return res;
	}
}

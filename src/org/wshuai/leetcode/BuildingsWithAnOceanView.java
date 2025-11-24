package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 02/25/2021.
 * #1762 https://leetcode.com/problems/buildings-with-an-ocean-view/
 */
public class BuildingsWithAnOceanView {

    // time O(n), space O(1)
    public int[] findBuildings(int[] heights) {
        int n = heights.length, maxHeights = 0;
        List<Integer> buildings = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (heights[i] > maxHeights) {
                buildings.add(i);
                maxHeights = heights[i];
            }
        }
        int[] res = new int[buildings.size()];
        for (int i = buildings.size() - 1, j = 0; i >= 0; i--, j++) {
            res[j] = buildings.get(i);
        }
        return res;
    }

    // time O(n), space O(n)
    public int[] findBuildingsMonotonicStack(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
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
        Deque<Integer> stack = new ArrayDeque<>();
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
        for (int i = buildings.size() - 1, j = 0; i >= 0; i--, j++) {
            res[j] = buildings.get(i);
        }
        return res;
    }
}

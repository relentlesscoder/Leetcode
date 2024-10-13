package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 02/25/2021.
 * #1762 https://leetcode.com/problems/buildings-with-an-ocean-view/
 */
public class BuildingsWithAnOceanView {

    // time O(n), space O(1)
    public int[] findBuildings(int[] heights) {
        int n = heights.length, maxHeightFromRight = 0;
        List<Integer> buildingsWithOceanView = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (maxHeightFromRight < heights[i]) {
                buildingsWithOceanView.add(i);
                maxHeightFromRight = heights[i];
            }
        }
        int[] res = new int[buildingsWithOceanView.size()];
        for (int i = 0, j = res.length - 1; i < res.length; i++, j--) {
            res[i] = buildingsWithOceanView.get(j);
        }
        return res;
    }

    // time O(n), space O(n)
    public int[] findBuildingsMonotonicStack(int[] heights) {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            while (!queue.isEmpty() && heights[queue.peekLast()] <= heights[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        int[] res = new int[queue.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = queue.pollFirst();
        }
        return res;
    }

    // time O(n), space O(n)
    public int[] findBuildingsMonotonicStackReverse(int[] heights) {
        int n = heights.length;
        List<Integer> buildingsWithOceanViews = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!queue.isEmpty() && heights[queue.peekLast()] < heights[i]) {
                queue.pollLast();
            }
            if (queue.isEmpty()) {
                buildingsWithOceanViews.add(i);
            }
            queue.offerLast(i);
        }
        int[] res = new int[buildingsWithOceanViews.size()];
        for (int i = 0, j = res.length - 1; i < res.length; i++, j--) {
            res[i] = buildingsWithOceanViews.get(j);
        }
        return res;
    }
}

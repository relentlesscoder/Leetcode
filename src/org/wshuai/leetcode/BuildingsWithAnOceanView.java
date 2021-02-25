package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 02/25/2021.
 * #1762 https://leetcode.com/problems/buildings-with-an-ocean-view/
 */
public class BuildingsWithAnOceanView {

    // time O(n), space O(n)
    public int[] findBuildings(int[] heights) {
        Stack<Integer> stack = new Stack<>(); // maintain a monotonic queue
        for(int i = 0; i < heights.length; i++){
            while(!stack.isEmpty() && heights[i] >= heights[stack.peek()]){
                stack.pop();
            }
            stack.push(i);
        }
        int[] res = new int[stack.size()];
        int k = 0;
        for(int b : stack){
            res[k++] = b;
        }
        return res;
    }
}

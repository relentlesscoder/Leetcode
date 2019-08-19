package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 8/19/19.
 * #783 https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
public class MinimumDistanceBetweenBSTNodes {
    public int minDiffInBST(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()){
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < res.size(); i++){
            int diff = res.get(i) - res.get(i-1);
            min = diff < min ? diff : min;
        }
        return min;
    }
}

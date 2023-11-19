package org.wshuai.leetcode;

/**
 * Created by Wei on 09/03/2023.
 * #2236 https://leetcode.com/problems/root-equals-sum-of-children/description/
 */
public class RootEqualsSumOfChildren {

    // time O(1), space O(1)
    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }
}

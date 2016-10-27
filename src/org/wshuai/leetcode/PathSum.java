package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/21/2016.
 * #112 https://leetcode.com/problems/path-sum/
 */
public class PathSum {
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
    {
      return false;
    }

    Stack<TreeNode> stk = new Stack<TreeNode>();
    stk.push(root);
    TreeNode curr = root;
    int path = root.val;
    while (!stk.empty())
    {
      if (curr != null && curr.left != null)
      {
        path += curr.left.val;
        stk.push(curr.left);
        curr = curr.left;
      }
      else
      {
        TreeNode n = stk.peek();
        if (n.right != null)
        {
          path += n.right.val;
          stk.push(n.right);
          curr = n.right;
          n.right = null;
        }
        else
        {
          if (curr != null && path == sum)
          {
            return true;
          }
          stk.pop();
          path -= n.val;
          curr = null;
        }
      }
    }
    return false;
  }
}

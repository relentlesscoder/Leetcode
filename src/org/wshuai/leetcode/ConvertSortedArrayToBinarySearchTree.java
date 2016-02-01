package org.wshuai.leetcode;

/**
 * Created by Wei on 1/31/16.
 * #108 https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class ConvertSortedArrayToBinarySearchTree {
  public static TreeNode sortedArrayToBST(int[] nums) {
    TreeNode root = null;

    if(nums == null || nums.length == 0){
      return root;
    }

    root = GetRootNode(nums, 0, nums.length - 1);

    return root;
  }

  private static TreeNode GetRootNode(int[] input, int p, int q){
    TreeNode root;
    if(p == q){
      root = new TreeNode(input[p]);
      root.left = null;
      root.right = null;
    }
    else if(p > q){
      root = null;
    }
    else{
      int index = (p + q) % 2 == 0 ? (p + q) / 2 : (p + q) / 2 + 1;
      root = new TreeNode(input[index]);
      root.left = GetRootNode(input, p, index - 1);
      root.right = GetRootNode(input, index + 1, q);
    }

    return root;
  }
}

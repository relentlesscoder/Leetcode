package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 1/26/16.
 * #173 https://leetcode.com/problems/binary-search-tree-iterator/
 */
public class BSTIterator {

  public BSTIterator(TreeNode root) {
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return true;
  }

  /** @return the next smallest number */
  public int next() {
    return 0;
  }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

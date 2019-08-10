package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.SumOfRootToLeafBinaryNumbers;
import org.wshuai.leetcode.TreeNode;

public class SumOfRootToLeafBinaryNumbersTest {
    @Test
    public void testcase(){
        SumOfRootToLeafBinaryNumbers sr = new SumOfRootToLeafBinaryNumbers();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);
        int sum = sr.sumRootToLeaf(root);
    }
}

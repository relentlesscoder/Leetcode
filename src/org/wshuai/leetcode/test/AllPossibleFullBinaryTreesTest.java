package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.AllPossibleFullBinaryTrees;
import org.wshuai.leetcode.TreeNode;

import java.util.List;

public class AllPossibleFullBinaryTreesTest {
    @Test
    public void testcase(){
        AllPossibleFullBinaryTrees ap = new AllPossibleFullBinaryTrees();
        List<TreeNode> nodes = ap.allPossibleFBT(3);
    }
}

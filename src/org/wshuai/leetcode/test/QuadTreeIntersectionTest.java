package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.QuadTreeIntersection;
import org.wshuai.leetcode.QuadTreeNode;

public class QuadTreeIntersectionTest {
	@Test
	public void testcase() {
		QuadTreeNode root1 = new QuadTreeNode();
		root1.isLeaf = false;
		root1.val = false;
		root1.topLeft = new QuadTreeNode(true, true, null, null, null, null);
		root1.topRight = new QuadTreeNode(true, true, null, null, null, null);
		root1.bottomLeft = new QuadTreeNode(true, true, null, null, null, null);
		root1.bottomRight = new QuadTreeNode(true, true, null, null, null, null);

		QuadTreeNode root2 = new QuadTreeNode();
		root2.topLeft = new QuadTreeNode(true, true, null, null, null, null);
		root2.topRight = new QuadTreeNode(true, true, null, null, null, null);
		root2.bottomLeft = new QuadTreeNode(true, true, null, null, null, null);
		root2.bottomRight = new QuadTreeNode(true, true, null, null, null, null);
		root2.isLeaf = false;
		root2.val = false;

		QuadTreeIntersection ti = new QuadTreeIntersection();
		QuadTreeNode root = ti.intersect(root1, root2);
	}
}

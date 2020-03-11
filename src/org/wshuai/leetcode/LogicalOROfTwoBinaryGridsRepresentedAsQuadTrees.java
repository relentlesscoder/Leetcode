package org.wshuai.leetcode;

/**
 * Created by Wei on 03/11/2020.
 * #0558 https://leetcode.com/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/
 */
public class LogicalOROfTwoBinaryGridsRepresentedAsQuadTrees {
	// time O(n)
	public QuadTreeNode intersect(QuadTreeNode quadTree1, QuadTreeNode quadTree2) {

		if (quadTree1.isLeaf) {
			return quadTree1.val ? quadTree1 : quadTree2;
		}
		if (quadTree2.isLeaf) {
			return quadTree2.val ? quadTree2 : quadTree1;
		}

		QuadTreeNode topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
		QuadTreeNode topRight = intersect(quadTree1.topRight, quadTree2.topRight);
		QuadTreeNode bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
		QuadTreeNode bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

		if (topLeft.isLeaf
				&& topRight.isLeaf
				&& bottomLeft.isLeaf
				&& bottomRight.isLeaf
				&& topLeft.val == topRight.val
				&& topLeft.val == bottomLeft.val
				&& topLeft.val == bottomRight.val) {
			return new QuadTreeNode(topLeft.val, true, null, null, null, null);
		}

		return new QuadTreeNode(false, false, topLeft, topRight, bottomLeft, bottomRight);
	}
}

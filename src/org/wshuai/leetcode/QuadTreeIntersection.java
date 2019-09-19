package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/19.
 * #558 https://leetcode.com/problems/quad-tree-intersection/
 */
public class QuadTreeIntersection {
	// see https://leetcode.com/problems/quad-tree-intersection/discuss/157532/Java-concise-code-beat-100
	public QuadTreeNode intersect(QuadTreeNode quadTree1, QuadTreeNode quadTree2) {
		if(quadTree1.isLeaf){
			return quadTree1.val ? quadTree1 : quadTree2;
		}
		if(quadTree2.isLeaf){
			return quadTree2.val ? quadTree2 : quadTree1;
		}

		quadTree1.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
		quadTree1.topRight = intersect(quadTree1.topRight, quadTree2.topRight);
		quadTree1.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
		quadTree1.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

		if(quadTree1.topLeft.isLeaf
				&& quadTree1.topRight.isLeaf
				&& quadTree1.bottomLeft.isLeaf
				&& quadTree1.bottomRight.isLeaf
				&& quadTree1.topLeft.val == quadTree1.topRight.val
				&& quadTree1.topLeft.val == quadTree1.bottomLeft.val
				&& quadTree1.topLeft.val == quadTree1.bottomRight.val){
			quadTree1.isLeaf = true;
			quadTree1.val = quadTree1.topLeft.val;
		}
		return quadTree1;
	}
}

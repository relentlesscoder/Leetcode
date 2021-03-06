package org.wshuai.leetcode;

public class QuadTreeNode {
	public boolean val;
	public boolean isLeaf;
	public QuadTreeNode topLeft;
	public QuadTreeNode topRight;
	public QuadTreeNode bottomLeft;
	public QuadTreeNode bottomRight;

	public QuadTreeNode() {
	}

	public QuadTreeNode(boolean _val, boolean _isLeaf) {
		val = _val;
		isLeaf = _isLeaf;
	}

	public QuadTreeNode(boolean _val, boolean _isLeaf, QuadTreeNode _topLeft, QuadTreeNode _topRight, QuadTreeNode _bottomLeft, QuadTreeNode _bottomRight) {
		val = _val;
		isLeaf = _isLeaf;
		topLeft = _topLeft;
		topRight = _topRight;
		bottomLeft = _bottomLeft;
		bottomRight = _bottomRight;
	}
}

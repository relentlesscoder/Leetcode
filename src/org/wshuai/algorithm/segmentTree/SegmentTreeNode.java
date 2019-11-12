package org.wshuai.algorithm.segmentTree;

/**
 * Created by Wei on 9/25/2016.
 */
public class SegmentTreeNode {
	public int start;
	public int end;
	public int sum;
	public SegmentTreeNode left;
	public SegmentTreeNode right;

	public SegmentTreeNode(int start, int end, int sum, SegmentTreeNode left, SegmentTreeNode right) {
		this.start = start;
		this.end = end;
		this.sum = sum;
		this.left = left;
		this.right = right;
	}

	public SegmentTreeNode(int start, int end) {
		this.start = start;
		this.end = end;
		this.sum = 0;
	}
}

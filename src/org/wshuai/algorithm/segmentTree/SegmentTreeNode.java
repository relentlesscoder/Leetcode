package org.wshuai.algorithm.segmentTree;

/**
 * Created by Wei on 9/25/2016.
 */
public class SegmentTreeNode {
  int start;
  int end;
  int sum;
  SegmentTreeNode left;
  SegmentTreeNode right;

  public SegmentTreeNode(int start, int end, int sum){
    this.start = start;
    this.end = end;
    this.sum = sum;
  }

  public SegmentTreeNode(int start, int end){
    this.start = start;
    this.end = end;
    this.sum = 0;
  }
}

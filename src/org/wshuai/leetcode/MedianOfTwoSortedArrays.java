package org.wshuai.leetcode;

/**
 * Created by Wei on 8/9/2015.
 * #4 https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays {
  //Use the idea of finding kth elements in two sorted array
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    int k = (m+n)/2;
    if((m+n)%2==0){
      return (findKth(nums1, nums2, 0, 0, m, n, k)+findKth(nums1, nums2, 0, 0, m, n, k+1))/2;
    }else{
      return findKth(nums1, nums2, 0, 0, m, n, k+1);
    }
  }

  private double findKth(int[] arr1, int[] arr2, int s1, int s2, int len1, int len2, int k){
    //Make sure arr1 is the shorter array
    if(len1 > len2){
      return findKth(arr2, arr1, s2, s1, len2, len1, k);
    }
    //Base case 1: arr1 is empty
    if(len1==0){
      return arr2[s2+k-1];
    }
    //Base case2: k become 1
    if(k==1){
      return Math.min(arr1[s1], arr2[s2]);
    }
    int p1 = Math.min(k/2, len1);
    int p2 = k-p1;
    if(arr1[s1+p1-1] < arr2[s2+p2-1]){
      return findKth(arr1, arr2, s1+p1, s2, len1-p1, len2, k-p1);
    }else if(arr1[s1+p1-1] > arr2[s2+p2-1]){
      return findKth(arr1, arr2, s1, s2+p2, len1, len2-p2, k-p2);
    }else{
      return arr1[s1+p1-1];
    }
  }
}

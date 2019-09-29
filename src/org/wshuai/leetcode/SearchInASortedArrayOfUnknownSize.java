package org.wshuai.leetcode;

/**
 * Created by Wei on 9/29/2019.
 * #702 https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/
 */
public class SearchInASortedArrayOfUnknownSize {
  public int search(ArrayReader reader, int target) {
    int left = 0, right = 1;
    while(reader.get(right) < target){
      left = right;
      right <<= 1;
    }
    while(left <= right){
      int mid = left + ((right - left) >> 1);
      int val = reader.get(mid);
      if(val == target){
        return mid;
      }
      if(val > target){
        right = mid -1;
      }else{
        left = mid + 1;
      }
    }
    return -1;
  }
}

class ArrayReader{
  // fake implementation
  public int get(int index){
    return -1;
  }
}

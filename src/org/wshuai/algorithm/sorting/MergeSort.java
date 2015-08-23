package org.wshuai.algorithm.sorting;

import org.wshuai.utility.ArrayUtil;

/**
 * Created by Wei on 8/23/2015.
 */
public class MergeSort {
  /**
   * Global auxiliary array for merging two sorted sub-arrays
   */
  private static Comparable[] auxiliary;

  /**
   * Merge sort the input array.
   * @param array the input array
   */
  public static void sort(Comparable[] array){
    if(array == null || array.length <= 1){
      return;
    }

    auxiliary = new Comparable[array.length];
    sort(array, 0, array.length - 1);
  }

  /**
   * Divide the input into two sub-arrays, sort both sub-arrays and then merge back
   * into one sorted array.
   * @param array the input array
   * @param low the lower index
   * @param high the higher index
   */
  private static void sort(Comparable[] array, int low, int high){
    if(high <= low){
      return;
    }

    int middle = low + (high - low)/2;
    sort(array, low, middle);
    sort(array, middle+1, high);
    merge(array, low, middle, high);
  }

  /**
   * Merge two sorted sub-arrays back into one sorted array.
   * @param array the input array
   * @param low the lower index
   * @param middle the middle index
   * @param high the higher index
   */
  private static void merge(Comparable[] array, int low, int middle, int high){
    for(int i = low; i <= high; i++){
      auxiliary[i] = array[i];
    }

    int left = low;
    int right = middle + 1;

    for(int i = low; i <= high; i++){
      if(left > middle){
        array[i] = auxiliary[right++];
      }
      else if(right > high){
        array[i] = auxiliary[left++];
      }
      else if(ArrayUtil.less(auxiliary[left], auxiliary[right])){
        array[i] = auxiliary[left++];
      }
      else{
        array[i] = auxiliary[right++];
      }
    }
  }
}

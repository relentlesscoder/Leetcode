package org.wshuai.leetcode;

/**
 * Created by Wei on 9/2/2016.
 */
public class CompareVersionNumbers {
  public static int compareVersion(String version1, String version2) {
    String[] arr1 = version1.split("\\.");
    String[] arr2 = version2.split("\\.");

    int result = 0;
    int idx = 0;
    int len1 = arr1.length;
    int len2 = arr2.length;
    while(idx < len1 || idx < len2){
      int n1 = idx < len1 ? Integer.parseInt(arr1[idx]) : 0;
      int n2 = idx < len2 ? Integer.parseInt(arr2[idx]) : 0;
      if(n1 > n2){
        return 1;
      }
      if(n1 < n2){
        return -1;
      }

      idx++;
    }

    return result;
  }
}

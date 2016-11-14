package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/16.
 * #96 https://leetcode.com/problems/unique-binary-search-trees/
 */
public class UniqueBinarySearchTrees {
  public int numTrees(int n) {
    int[] aux = new int[n+1];
    aux[0] = 1;
    aux[1] = 1;
    for(int i = 2; i <= n; i++){
      for(int j = 0; j < i; j++){
        aux[i] += aux[j]*aux[i-j-1];
      }
    }
    return aux[n];
  }
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 9/2/2016.
 */
public class EditDistance {
  public static int minDistance(String word1, String word2) {
    int len1 = word1 == null ? 0 : word1.length();
    int len2 = word2 == null ? 0 : word2.length();

    if(len1 == 0){
      return len2;
    }
    if(len2 ==0){
      return len1;
    }

    char[] arr1 = word1.toCharArray();
    char[] arr2 = word2.toCharArray();
    int[][] mtx = new int[len1 + 1][len2 + 1];
    for(int i = 0; i < len1 + 1; i++){
      mtx[i][0] = i;
    }
    for(int i = 0; i < len2 + 1; i++){
      mtx[0][i] = i;
    }

    for(int i = 1; i < len1 + 1; i++){
      for(int j = 1; j < len2 + 1; j++){
        int a = mtx[i - 1][j] + 1;
        int b = mtx[i][j - 1] + 1;
        int c = mtx[i - 1][j - 1] + (arr1[i - 1] == arr2[j - 1] ? 0 : 1);
        mtx[i][j] = Math.min(a, Math.min(b, c));
      }
    }

    return mtx[len1][len2];
  }
}

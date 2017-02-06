package org.wshuai.leetcode;

/**
 * Created by Wei on 2/5/17.
 * #97 https://leetcode.com/problems/interleaving-string/
 */
public class InterleavingString {

  //DP
  public boolean isInterleave(String s1, String s2, String s3) {
    if(s1 == null || s2 == null || s3 == null){
      return false;
    }
    if(s1.equals("") && s2.equals("") && s3.equals("")){
      return true;
    }
    if(s1.equals("")){
      return s2.equals(s3);
    }
    if(s2.equals("")){
      return s1.equals(s3);
    }
    int len1 = s1.length();
    int len2 = s2.length();
    int len3 = s3.length();
    if(len3 != len1+len2){
      return false;
    }
    boolean[] aux = new boolean[len2+1];
    aux[0] = false;
    for(int i = 1; i <= len2; i++){
      aux[i] = i == 1 ? s2.charAt(i-1) == s3.charAt(i-1)
        : (s2.charAt(i-1) == s3.charAt(i-1) && aux[i-1]);
    }
    for(int i = 1; i <= len1; i++){
      aux[0] = i == 1 ? s1.charAt(i-1) == s3.charAt(i-1)
        : (s1.charAt(i-1) == s3.charAt(i-1) && aux[0]);
      for(int j = 1; j<= len2; j++){
        aux[j] = (s1.charAt(i-1) == s3.charAt(i+j-1) && aux[j])
          || (s2.charAt(j-1) == s3.charAt(i+j-1) && aux[j-1]);
      }
    }
    return aux[len2];
  }

  //2D DP
  public boolean isInterleaveDP2D(String s1, String s2, String s3) {
    if(s1 == null || s2 == null || s3 == null){
      return false;
    }
    if(s1.equals("") && s2.equals("") && s3.equals("")){
      return true;
    }
    if(s1.equals("")){
      return s2.equals(s3);
    }
    if(s2.equals("")){
      return s1.equals(s3);
    }
    int len1 = s1.length();
    int len2 = s2.length();
    int len3 = s3.length();
    if(len3 != len1+len2){
      return false;
    }
    boolean[][] aux = new boolean[len1+1][len2+1];
    aux[0][0] = false;
    aux[0][1] = (s2.charAt(0) == s3.charAt(0));
    aux[1][0] = (s1.charAt(0) == s3.charAt(0));
    for(int i = 2; i <= len1; i++){
      aux[i][0] = aux[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
    }
    for(int i = 2; i <= len2; i++){
      aux[0][i] = aux[0][i-1] && s2.charAt(i-1) == s3.charAt(i-1);
    }
    for(int i = 1; i <= len1; i++){
      for(int j = 1; j <= len2; j++){
        aux[i][j] = (s1.charAt(i-1) == s3.charAt(i+j-1) && aux[i-1][j])
          || (s2.charAt(j-1) == s3.charAt(i+j-1) && aux[i][j-1]);
      }
    }
    return aux[len1][len2];
  }

  //Recursive, TLE
  public boolean isInterleaveRecursive(String s1, String s2, String s3) {
    if(s1 == null || s1.equals("")){
      return (s2.equals(s3));
    }
    if(s2 == null || s2.equals("")){
      return (s1.equals(s3));
    }
    int len1 = s1.length();
    int len2 = s2.length();
    for(int i = 1; i <= len1; i++){
      String ss1 = s1.substring(0, i);
      for(int j = 1; j <= len2; j++){
        String ss2 = s2.substring(0, j);
        if(!s3.startsWith(ss1) && !s3.startsWith(ss2)){
          continue;
        }
        if(s3.startsWith(ss1) && s3.startsWith(ss2, i)){
          if(isInterleaveRecursive(s1.substring(i), s2.substring(j), s3.substring(i+j))){
            return true;
          }
        }
        if(s3.startsWith(ss2) && s3.startsWith(ss1, j)){
          if(isInterleaveRecursive(s1.substring(i), s2.substring(j), s3.substring(i+j))){
            return true;
          }
        }
      }
    }
    return false;
  }
}

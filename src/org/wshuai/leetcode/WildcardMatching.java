package org.wshuai.leetcode;

import java.util.regex.Pattern;

/**
 * Created by Wei on 8/30/2016.
 */
public class WildcardMatching {

  public static boolean isMatchDP(String s, String p) {
    if(s == null || p == null){
      return false;
    }
    while(p.contains("**")){
      p = p.replaceAll("\\*\\*", "*");
    }
    if(s.isEmpty() && p.isEmpty()){
      return true;
    }
    if(s.isEmpty() && p.equals("*")){
      return true;
    }
    if(p.isEmpty() && !s.isEmpty()){
      return false;
    }
    char[] sArr = s.toCharArray();
    char[] pArr = p.toCharArray();
    int sLen = s.length();
    int pLen = p.length();

    int[][] mtx = new int[sLen + 1][pLen + 1];
    mtx[0][0] = 1;
    if(pArr[0] == '*'){
      mtx[0][1] = 1;
    }

    for(int i = 1; i <= sLen; i++){
      for(int j = 1; j <= pLen; j++){
        if(sArr[i - 1] == pArr[j - 1] || pArr[j - 1] == '?'){
          mtx[i][j] = mtx[i - 1][j - 1];
        }else if(pArr[j - 1] == '*'){
          mtx[i][j] = (mtx[i - 1][j] == 1 || mtx[i][j - 1] == 1) ? 1 : 0;
        }
      }
    }

    return (mtx[sLen][pLen] == 1);
  }

  public static boolean isMatchDP2(String s, String p) {
    if(s == null || p == null){
      return false;
    }
    while(p.contains("**")){
      p = p.replaceAll("\\*\\*", "*");
    }
    if(s.isEmpty() && p.isEmpty()){
      return true;
    }
    if(s.isEmpty() && p.equals("*")){
      return true;
    }
    if(p.isEmpty() && !s.isEmpty()){
      return false;
    }
    char[] sArr = s.toCharArray();
    char[] pArr = p.toCharArray();
    int sLen = s.length();
    int pLen = p.length();

    int[] mtx = new int[pLen + 1];
    mtx[0] = 1;
    if(pArr[0] == '*'){
      mtx[1] = 1;
    }

    for(int i = 1; i <= sLen; i++){
      int[] mtx1 = new int[pLen + 1];
      for(int j = 1; j <= pLen; j++){
        if(sArr[i - 1] == pArr[j - 1] || pArr[j - 1] == '?'){
          mtx1[j] = mtx[j - 1];
        }else if(pArr[j - 1] == '*'){
          mtx1[j] = (mtx[j] == 1 || mtx1[j - 1] == 1) ? 1 : 0;
        }
      }
      mtx = mtx1;
    }

    return (mtx[pLen] == 1);
  }

  public static boolean isMatchRecursive(String s, String p) {
    if(s == null || p == null){
      return false;
    }
    while(p.contains("**")){
      p = p.replaceAll("\\*\\*", "*");
    }
    if(s.isEmpty() && p.isEmpty()){
      return true;
    }
    if(s.isEmpty() && p.equals("*")){
      return true;
    }
    if(p.isEmpty() && !s.isEmpty()){
      return false;
    }
    char[] sArr = s.toCharArray();
    char[] pArr = p.toCharArray();
    return matchRecursive(sArr, pArr, 0, 0, sArr.length, pArr.length);
  }

  private static boolean matchRecursive(char[] s, char[] p, int i, int j, int sLen, int pLen){
    if(i == sLen && (j == pLen || (j == pLen - 1 && p[j] == '*'))){
      return true;
    }else if(i == sLen || j == pLen){
      return false;
    }

    char pChr = p[j];
    if(pChr == '?' || pChr == s[i]){
      return matchRecursive(s, p, i + 1, j + 1, sLen, pLen);
    }else if(pChr == '*'){
      for(int x = i; x <= sLen; x++){
        if(matchRecursive(s, p, x, j + 1, sLen, pLen)){
          return true;
        }
      }
      return false;
    }else{
      return false;
    }
  }
}

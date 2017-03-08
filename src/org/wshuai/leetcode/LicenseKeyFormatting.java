package org.wshuai.leetcode;

/**
 * Created by Wei on 2/26/17.
 * #482 https://leetcode.com/problems/license-key-formatting/
 */
public class LicenseKeyFormatting {
  public String licenseKeyFormatting(String S, int K) {
    StringBuilder sb = new StringBuilder();
    int len = S.length();
    for(int i = len-1; i >= 0; i--){
      if(S.charAt(i) != '-'){
        sb.append(sb.length()%(K+1) == K ? "-":"").append(S.charAt(i));
      }
    }
    return sb.reverse().toString().toUpperCase();
  }
}

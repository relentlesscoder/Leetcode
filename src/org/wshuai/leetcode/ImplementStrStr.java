package org.wshuai.leetcode;

/**
 * Created by Wei on 8/16/2016.
 * #28 https://leetcode.com/problems/implement-strstr/
 */
public class ImplementStrStr {

  public int strStrKMP(String haystack, String needle) {
    if(haystack == null || needle == null){
      return -1;
    }
    if(needle.isEmpty()){
      return 0;
    }
    int pLen = needle.length();
    int vLen = haystack.length();
    char[] val = haystack.toCharArray();
    char[] pattern = needle.toCharArray();
    int[] lsp = new int[pLen];
    buildLSP(pattern, lsp);

    int vp = 0;
    int pp = 0;
    while(vp < vLen){
      if(val[vp] == pattern[pp]){
        if(pp == pLen - 1){
          return vp - pLen + 1;
        }
        vp++;
        pp++;
      }else if(pp == 0){
        vp++;
      }else{
        int prefix = lsp[pp - 1];
        if(prefix == 0){
          pp = 0;
        }else{
          pp = prefix;
        }
      }
    }

    return -1;
  }

  public void buildLSP(char[] pattern, int[] lsp){
    int len = pattern.length;
    lsp[0] = 0;
    int prefix = 0;
    for(int i = 1; i < len; i++){
      while (prefix > 0 && pattern[prefix] != pattern[i]){
        prefix = lsp[prefix - 1];
      }

      if(pattern[prefix] == pattern[i]){
        prefix++;
      }

      lsp[i] = prefix;
    }
  }

}

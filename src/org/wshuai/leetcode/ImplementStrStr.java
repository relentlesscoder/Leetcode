package org.wshuai.leetcode;

/**
 * Created by Wei on 8/16/2016.
 */
public class ImplementStrStr {

  public static int strStrKmp(String pattern, String text) {
    int[] lsp = computeLspTable(pattern);

    int j = 0;  // Number of chars matched in pattern
    for (int i = 0; i < text.length(); i++) {
      while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
        // Fall back in the pattern
        j = lsp[j - 1];  // Strictly decreasing
      }
      if (text.charAt(i) == pattern.charAt(j)) {
        // Next char matched, increment position
        j++;
        if (j == pattern.length())
          return i - (j - 1);
      }
    }

    return -1;  // Not found
  }

  public static int[] computeLspTable(String pattern) {
    int[] lsp = new int[pattern.length()];
    lsp[0] = 0;  // Base case
    for (int i = 1; i < pattern.length(); i++) {
      // Start by assuming we're extending the previous LSP
      int j = lsp[i - 1];
      while (j > 0 && pattern.charAt(i) != pattern.charAt(j)){
        j = lsp[j - 1];
      }
      if (pattern.charAt(i) == pattern.charAt(j)){
        j++;
      }
      lsp[i] = j;
    }
    return lsp;
  }

  public static int[] buildKMPTable(String word){
    int wordLen = word.length();
    int[] table = new int[wordLen];
    int len = 0;
    int i = 1;
    table[0] = 0;
    while(i < wordLen){
      if(word.charAt(len) == word.charAt(i)){
        len++;
        table[i] = len;
        i++;
      }else{
        if(len != 0){
          len = table[len - 1];
        }else{
          table[i] = 0;
          i++;
        }
      }
    }
    return table;
  }
}

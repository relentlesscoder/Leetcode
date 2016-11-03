package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/2016.
 * #318 https://leetcode.com/problems/maximum-product-of-word-lengths/
 */
public class MaximumProductOfWordLengths {
  public int maxProduct(String[] words) {
    if(words == null || words.length == 0){
      return 0;
    }
    int len = words.length;
    int[] aux = new int[len];
    for(int i = 0; i < len; i++){
      String word = words[i];
      int wLen = word.length();
      for(int j = 0; j < wLen; j++){
        //Since total number of lowercase characters is 26,
        //integer can be used to save the word
        aux[i] |= 1 << (word.charAt(j)-'a');
      }
    }

    int max = 0;
    for(int i = 0; i < len-1; i++){
      int s = aux[i];
      for(int j = i+1; j < len; j++){
        int t = aux[j];
        if((s&t) == 0){
          int prod = words[i].length()*words[j].length();
          max = prod > max ? prod : max;
        }
      }
    }
    return max;
  }
}

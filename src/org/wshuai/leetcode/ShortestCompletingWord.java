package org.wshuai.leetcode;

/**
 * Created by Wei on 8/24/19.
 * #748 https://leetcode.com/problems/shortest-completing-word/
 */
public class ShortestCompletingWord {
  public String shortestCompletingWord(String licensePlate, String[] words) {
    int[] arr = count(licensePlate);
    String res = "";
    for(String word: words){
      if(res != "" && word.length() >= res.length()){
        continue;
      }
      int[] curr = count(word);
      boolean good = true;
      for(int i = 0; i < 26; i++){
        if(curr[i] < arr[i]){
          good = false;
          break;
        }
      }
      if(good){
        res = word;
      }
    }
    return res;
  }

  public int[] count(String word){
    int[] arr = new int[26];
    for(char c: word.toCharArray()){
      if(Character.isLetter(c)){
        arr[Character.toLowerCase(c) - 'a']++;
      }
    }
    return arr;
  }
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 10/2/16.
 * #244 https://leetcode.com/problems/shortest-word-distance-iii/
 */
public class ShortestWordDistanceIII {
  public int shortestWordDistance(String[] words, String word1, String word2) {
    int min = Integer.MAX_VALUE;
    int w1 = -1;
    int w2 = -1;
    int len = words.length;
    boolean last = false;
    boolean same = word1.equals(word2);
    for(int i = 0; i < len; i++){
      String val = words[i];
      if(val.equals(word1) && (!same || !last)){
        w1 = i;
        if(w2 != -1){
          int dis = w1 - w2;
          min = dis < min ? dis : min;
        }
        last = true;
      }
      else if(val.equals(word2) && (!same || last)){
        w2 = i;
        if(w1 != -1){
          int dis = w2 - w1;
          min = dis < min ? dis : min;
        }
        last = false;
      }
    }
    return min;
  }
}

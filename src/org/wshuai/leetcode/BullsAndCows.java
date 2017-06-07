package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 9/21/2016.
 * #299 https://leetcode.com/problems/bulls-and-cows/
 */
public class BullsAndCows {

  // Use sorting
  public String getHintSorting(String secret, String guess) {
    if(secret == null || guess == null){
      throw new IllegalArgumentException("Invalid input");
    }
    char[] sArr = secret.toCharArray();
    char[] gArr = guess.toCharArray();
    int len = sArr.length;
    int bulls = 0;
    int cows = 0;
    int total = 0;

    for(int i = 0; i < len; i++){
      char sc = sArr[i];
      char gc = gArr[i];
      if(sc == gc){
        bulls++;
      }
    }

    Arrays.sort(sArr);
    Arrays.sort(gArr);
    int l = 0;
    int r = 0;

    while(l < len && r < len){
      char sc = sArr[l];
      char gc = gArr[r];
      if(sc == gc){
        total++;
        l++;
        r++;
      }else if(sc < gc){
        l++;
      }else{
        r++;
      }
    }
    cows = total - bulls;

    return bulls + "A" + cows + "B";
  }

  // Use hash table, exceed time limit
  public String getHintHT(String secret, String guess) {
    if(secret == null || guess == null){
      throw new IllegalArgumentException("Invalid input");
    }
    char[] sArr = secret.toCharArray();
    char[] gArr = guess.toCharArray();
    int len = sArr.length;
    int bulls = 0;
    int cows = 0;
    Set<Integer> same = new HashSet<Integer>();
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    for(int i = 0; i < len; i++){
      char sc = sArr[i];
      char gc = gArr[i];
      if(sc == gc){
        bulls++;
        same.add(i);
      }else{
        if(map.containsKey(sc)){
          int c = map.get(sc);
          map.put(sc, c + 1);
        }else{
          map.put(sc, 1);
        }
      }
    }
    for(int i = 0; i < len; i++){
      if(same.contains(i)){
        continue;
      }
      char gc = gArr[i];
      if(map.containsKey(gc)){
        int c = map.get(gc);
        if(c > 0){
          cows++;
          map.put(gc, c - 1);
        }

      }
    }
    return bulls + "A" + cows + "B";
  }
}

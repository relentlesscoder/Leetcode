package org.wshuai.leetcode;

/**
 * Created by Wei on 11/7/16.
 * #418 https://leetcode.com/problems/sentence-screen-fitting/
 */
public class SentenceScreenFitting {

  //TLE
  public int wordsTyping(String[] sentence, int rows, int cols) {
    if(sentence == null || sentence.length == 0 || rows <= 0 || cols <= 0){
      return 0;
    }
    int total = 0;
    int len = sentence.length;
    int i = 0;
    int j = 0;
    int[] aux = new int[len];
    for(int x = 0; x < len; x++){
      aux[x] = sentence[x].length();
      if(aux[x] > cols){
        return 0;
      }
    }
    while(i < rows){
      int count = 0;
      int wLen = 0;
      while(j < len){
        int sLen = aux[j];
        int nLen = wLen+sLen+count;
        if(nLen > cols){
          total += count;
          break;
        }else{
          wLen += sLen;
          count++;
          j++;
          if(j == len){
            j = 0;
          }
        }
      }
      i++;
    }
    return total/len;
  }
}

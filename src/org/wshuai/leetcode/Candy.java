package org.wshuai.leetcode;

/**
 * Created by Wei on 6/7/2017.
 * #135 https://leetcode.com/problems/candy/
 */
public class Candy {
  //Greedy, see http://fisherlei.blogspot.com/2013/11/leetcode-candy-solution.html
  public int candy(int[] ratings) {
    if(ratings == null){
      return 0;
    }
    if(ratings.length <= 1){
      return ratings.length;
    }
    int len = ratings.length;
    int[] candies = new int[len];
    candies[0] = 1;
    for(int i = 1; i < len; i++){
      if(ratings[i] > ratings[i-1]){
        candies[i] = candies[i-1]+1;
      }else{
        candies[i] = 1;
      }
    }
    int sum = candies[len-1];
    for(int i = len-2; i >= 0; i--){
      if(ratings[i] > ratings[i+1]){
        candies[i] = Math.max(candies[i], candies[i+1]+1);
      }
      sum+=candies[i];
    }
    return sum;
  }
}

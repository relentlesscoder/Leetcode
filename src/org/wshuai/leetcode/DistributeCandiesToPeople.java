package org.wshuai.leetcode;

/**
 * Created by Wei on 8/21/19.
 * #1103 https://leetcode.com/problems/distribute-candies-to-people/
 */
public class DistributeCandiesToPeople {
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int i = 0;
        int n = 0;
        while(candies > 0){
            n++;
            if(i == num_people){
                i = 0;
            }
            int c = candies >= n ? n : candies;
            res[i++] += c;
            candies -= c;
        }
        return res;
    }
}

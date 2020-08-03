package org.wshuai.leetcode;

/**
 * Created by Wei on 08/03/2020.
 * #1535 https://leetcode.com/problems/find-the-winner-of-an-array-game/
 */
public class FindTheWinnerOfAnArrayGame {

    // time O(n)
    public int getWinner(int[] arr, int k) {
        int max = arr[0], count = 0, n = arr.length;
        // it needs at most n - 1 comparisons to shift the max to the head
        // of the array so if we could not find a local max that can beats
        // k numbers (1 from its left and k - 1 from its right) consecutively
        // then simply return the max of the array
        for(int c = 1; c < n; c++){
            if(arr[c] > max){
                max = arr[c];
                count = 1;
            }else{
                count++;
            }
            if(count == k){
                return max;
            }
        }
        return max;
    }
}

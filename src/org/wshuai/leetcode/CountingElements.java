package org.wshuai.leetcode;

/**
 * Created by Wei on 05/01/2020.
 * #1426 https://leetcode.com/problems/counting-elements/
 */
public class CountingElements {
    // time O(n)
    public int countElements(int[] arr) {
        int res = 0;
        int[] count = new int[1_001];
        for(int a : arr){
            count[a]++;
        }
        for(int i = 0; i < 1_000; i++){
            if(count[i + 1] > 0){
                res += count[i];
            }
        }
        return res;
    }
}

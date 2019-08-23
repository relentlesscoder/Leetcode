package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 8/23/19.
 * #888 https://leetcode.com/problems/fair-candy-swap/
 */
public class FairCandySwap {
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] exchange = new int[2];
        Set<Integer> set = new HashSet<>();
        int sum1 = 0;
        int sum2 = 0;
        for(int a: A){
            sum1 += a;
            set.add(a);
        }
        for(int b: B){
            sum2 += b;
        }
        if(sum1 == sum2){
            return exchange;
        }
        for(int b: B){
            if(sum1 > sum2){
                int diff = sum1-sum2;
                if(set.contains(diff/2 + b)){
                    exchange[0] = diff/2 + b;
                    exchange[1] = b;
                }
            }else{
                int diff = sum2-sum1;
                if(set.contains(b - diff/2)){
                    exchange[0] = b - diff/2;
                    exchange[1] = b;
                }
            }
        }
        return exchange;
    }
}

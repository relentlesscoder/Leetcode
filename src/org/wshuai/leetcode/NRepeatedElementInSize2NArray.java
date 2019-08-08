package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 8/8/19.
 * #961 https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
 */
public class NRepeatedElementInSize2NArray {
    public int repeatedNTimes(int[] A) {
        Set<Integer> set = new HashSet<Integer>();
        for(int a: A){
            if(!set.add(a)){
                return a;
            }
        }
        return -1;
    }
}

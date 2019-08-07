package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/7/19.
 * #1134 https://leetcode.com/problems/armstrong-number/
 */
public class ArmstrongNumber {
    public boolean isArmstrong(int N) {
        List<Integer> lst = new ArrayList<Integer>();
        int val = N;
        while(val > 0){
            lst.add(val%10);
            val = val/10;
        }
        int pwr = lst.size();
        long res = 0;
        for(int i: lst){
            res += (int)Math.pow(i, pwr);
        }
        return res == N;
    }
}

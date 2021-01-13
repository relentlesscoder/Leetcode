package org.wshuai.leetcode;

/**
 * Created by Wei on 01/13/2021.
 * #1716 https://leetcode.com/problems/calculate-money-in-leetcode-bank/
 */
public class CalculateMoneyInLeetcodeBank {

    public int totalMoney(int n) {
        int res = 0, m = n / 7, sum = 28;
        n %= 7;
        if(m > 0){
            res += sum * m;
            res += 7 * ((m - 1) * m / 2);
        }
        for(int i = 0; i < n; i++){
            res += m + i + 1;
        }
        return res;
    }
}

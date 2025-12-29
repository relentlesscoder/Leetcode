package org.wshuai.leetcode;

/**
 * Created by Wei on 07/25/2020.
 * #1523 https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/
 */
public class CountOddNumbersInAnIntervalRange {

    // time O(1), space O(1)
    public int countOdds(int low, int high) {
        // 四种情况：
        //   1. 总共有偶数个数且以奇数结尾：[0,1,2,3] -> 奇数个数 n / 2
        //   2. 总共有偶数个数且以偶数结尾：[1,2,3,4] -> 奇数个数 n / 2
        //   3. 总共有奇数个数且以奇数结尾：[1,2,3,4,5] -> 奇数个数 n / 2 + 1
        //   4. 总共有奇数个数且以偶数结尾：[0,1,2,3,4] -> 奇数个数 n / 2
        int count = high - low + 1;
        if (count % 2 == 0) {
            return count / 2;
        }
        return count / 2 + (high & 1);
    }
}

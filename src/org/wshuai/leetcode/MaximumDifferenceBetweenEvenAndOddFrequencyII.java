package org.wshuai.leetcode;

/**
 * Created by Wei on 10/09/2025.
 * #3445 https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-ii/
 */
public class MaximumDifferenceBetweenEvenAndOddFrequencyII {

    // time O(n), space O(1)
    public int maxDifference(String s, int k) {
        int inf = Integer.MAX_VALUE / 2;
        char[] arr = s.toCharArray();
        int res = -inf;
        // check all combinations
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (y == x) {
                    continue;
                }
                int[] curS = new int[5];
                int[] preS = new int[5];
                // [0,0] even, even
                // [0,1] even, odd
                // [1,0] odd, even
                // [1,1] odd, odd
                int[][] minS = {{inf, inf}, {inf, inf}};
                int left = 0;
                for (int i = 0; i < arr.length; i++) {
                    curS[arr[i] - '0']++;
                    while (i - left + 1 >= k && curS[x] > preS[x] && curS[y] > preS[y]) {
                        int p = preS[x] & 1; // check even or add for x
                        int q = preS[y] & 1; // check even or add for y
                        minS[p][q] = Math.min(minS[p][q], preS[x] - preS[y]); // maintain the min value
                        preS[arr[left] - '0']++;
                        left++;
                    }
                    // curS[x] & 1 ^ 1 to get reverse even/odd as curS[x]
                    // curS[y] & 1 to get the same even/odd as curS[y]
                    res = Math.max(res, curS[x] - curS[y] - minS[curS[x] & 1 ^ 1][curS[y] & 1]);
                }
            }
        }
        return res;
    }
}

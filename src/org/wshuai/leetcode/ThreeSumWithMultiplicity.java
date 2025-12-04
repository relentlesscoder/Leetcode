package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/06/2019.
 * #0923 https://leetcode.com/problems/3sum-with-multiplicity/
 */
public class ThreeSumWithMultiplicity {
    private static final int MOD = (int) 1e9 + 7;

    // time O(n^2), space O(log(n))
    public int threeSumMulti(int[] arr, int target) {
        long res = 0;
        Arrays.sort(arr);
        int n = arr.length;
        for (int k = 0; k < n - 2; k++) {
            if (arr[k] + arr[k + 1] + arr[k + 2] > target) {
                break;
            }
            if (arr[k] + arr[n - 2] + arr[n - 1] < target) {
                continue;
            }
            for (int i = k + 1, j = n - 1; i < j; ) {
                int sum = arr[i] + arr[j] + arr[k];
                if (sum == target) {
                    if (arr[i] == arr[j]) {
                        res += (long) (j - i) * (j - i + 1) / 2;
                        break;
                    } else {
                        int c1 = 0, c2 = 0, v1 = arr[i], v2 = arr[j];
                        while (arr[i] == v1) {
                            i++;
                            c1++;
                        }
                        while (arr[j] == v2) {
                            j--;
                            c2++;
                        }
                        res += (long) c1 * c2;
                    }
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return (int) (res % MOD);
    }
}

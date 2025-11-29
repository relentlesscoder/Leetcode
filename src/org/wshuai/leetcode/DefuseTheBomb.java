package org.wshuai.leetcode;

/**
 * Created by Wei on 12/07/2020.
 * #1652 https://leetcode.com/problems/defuse-the-bomb/
 */
public class DefuseTheBomb {

    // time O(n), space O(1)
    public int[] decrypt(int[] code, int k) {
        int n = code.length, sum = 0;
        int[] res = new int[n];
        if (k == 0) {
            return res;
        }
        boolean flag = k < 0;
        k = k < 0 ? -k : k;
        for (int i = 0; i < n + k; i++) {
            sum += code[i % n];
            if (i - k + 1 < 0) {
                continue;
            }
            int idx = flag ? (i + 1) % n : ((i - k) % n + n) % n;
            res[idx] = sum;
            sum -= code[(i - k + 1) % n];
        }
        return res;
    }
}

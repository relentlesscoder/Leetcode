package org.wshuai.leetcode;

/**
 * Created by Wei on 08/10/2020.
 * #1545 https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/
 */
public class FindKthBitInNthBinaryString {

    // time O(log(n))
    public char findKthBit(int n, int k) {
        return dfs((1 << n) - 1, k);
    }

    private char dfs(int t, int k){
        if(k == 1){
            return '0';
        }
        int m = (t >> 1) + 1;
        // k at the middle, simply return 1
        if(k == m){
            return '1';
        }
        // k on the left
        if(k < m){
            return dfs(t >> 1, k);
        }
        // k on the right, return the reverse of its mirror
        // bit on the left
        return dfs(t >> 1, m - (k - m)) == '0' ? '1' : '0';
    }
}

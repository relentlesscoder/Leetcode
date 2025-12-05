package org.wshuai.leetcode;

/**
 * Created by Wei on 11/29/2025.
 * #2156 https://leetcode.com/problems/find-substring-with-given-hash-value/
 */
public class FindSubstringWithGivenHashValue {

    // time O(n), space O(1)
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        long curr = 0, pk = 1; // pk = p^(k - 1)
        int res = 0, n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            curr = (curr * power + s.charAt(i) - 'a' + 1) % modulo;
            if (i + k >= n) {
                pk = pk * power % modulo; // calculate p^(k - 1)
            } else {
                curr = (curr - (s.charAt(i + k) - 'a' + 1) * pk % modulo + modulo) % modulo;
            }
            if (i <= n - k && curr == hashValue) {
                res = i;
            }

        }
        return s.substring(res, res + k);
    }
}

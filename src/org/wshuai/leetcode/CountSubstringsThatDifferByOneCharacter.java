package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2020.
 * #1638 https://leetcode.com/problems/count-substrings-that-differ-by-one-character/
 */
public class CountSubstringsThatDifferByOneCharacter {

    // time O(m*n), space O(m*n)
    public int countSubstrings(String s, String t) {
        int res = 0, m = s.length(), n = t.length();
        int[][] dpl = new int[m][n], dpr = new int[m][n];
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dpl[i][j] = 1 + dpl[i - 1][j - 1]; // dpl[i][j] denotes longest equal substring left to s[i], t[j]
                }
            }
        }
        for(int i = m - 2; i >= 0; i--){
            for(int j = n - 2; j >= 0; j--){
                if(s.charAt(i + 1) == t.charAt(j + 1)){
                    dpr[i][j] = 1 + dpr[i + 1][j + 1]; // dpr[i][j] denotes longest equal substring right to s[i], t[j]
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(s.charAt(i) == t.charAt(j)){
                    continue;
                }
                res += (dpl[i][j] + 1) * (dpr[i][j] + 1); // + 1 since left(right) can have length from 0 (empty) to dpl[i][j]
            }
        }
        return res;
    }
}

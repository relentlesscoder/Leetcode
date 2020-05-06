package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/20/2019.
 * #0828 https://leetcode.com/problems/unique-letter-string/
 */
public class CountUniqueCharactersOfAllSubstringsOfAGivenString {
    // time O(n), space O(n)
    // https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/discuss/128952/C%2B%2BJavaPython-One-pass-O(N)
    public int uniqueLetterString(String S) {
        /*Let's think about how a character can be found as a unique character.

        Think about string "XAXAXXAX" and focus on making the second "A" a unique character.
        We can take "XA(XAXX)AX" and between "()" is our substring.
        We can see here, to make the second "A" counted as a uniq character, we need to:
            insert "(" somewhere between the first and second A
            insert ")" somewhere between the second and third A
        For step 1 we have "A(XA" and "AX(A", 2 possibility.
        For step 2 we have "A)XXA", "AX)XA" and "AXX)A", 3 possibilities.
        So there are in total 2 * 3 = 6 ways to make the second A a unique character in a substring.
        In other words, there are only 6 substring, in which this A contribute 1 point as unique string.*/
        int[][] index = new int[26][2];
        for(int i = 0; i < 26; i++){
            Arrays.fill(index[i], -1);
        }
        int res = 0;
        int N = S.length();
        int mod = 1_000_000_007;
        for(int i = 0; i < N; i++){
            int c = S.charAt(i) - 'A';
            res = (res + (i - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
            index[c] = new int[]{index[c][1], i};
        }
        for(int c = 0; c < 26; c++){
            res = (res + (N - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
        }
        return res;
    }
}

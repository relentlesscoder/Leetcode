package org.wshuai.leetcode;

/**
 * Created by Wei on 08/24/2025.
 * #3304 https://leetcode.com/problems/find-the-k-th-character-in-string-game-i/
 */
public class FindTheKThCharacterInStringGameI {

    // time O(1), space O(1)
    public char kthCharacter(int k) {
        // https://leetcode.com/problems/find-the-k-th-character-in-string-game-i/solutions/6912907/simple-explanation-for-why-o-1-1-liner-solution-works
        return (char)('a' + Integer.bitCount(k - 1));
    }

    // time O(k), space O(k)
    public char kthCharacterSimulation(int k) {
        StringBuilder sb = new StringBuilder("a");
        while (sb.length() < k) {
            int size = sb.length();
            for (int i = 0; i < size; i++) {
                sb.append((char) ('a' + ((sb.charAt(i) - 'a') + 1) % 26));
            }
        }
        return sb.charAt(k - 1);
    }
}

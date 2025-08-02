package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/02/2025.
 * #2434 https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/
 */
public class UsingARobotToPrintTheLexicographicallySmallestString {

    // time O(n), space O(n)
    public String robotWithString(String s) {
        // "edbaceda" -> "aadecbde"
        // "edbacedaczzcz" -> "aacczzdecbdez"
        // "edbacedacbcd" -> "aabccddecbde"
        StringBuilder res = new StringBuilder();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        char min = 'a';
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            stack.push(c);
            freq[c - 'a']--;
            while (min != 'z' && freq[min - 'a'] == 0) {
                min++;
            }
            // for each c if it is less or equals to the min in remaining characters
            // then write it to make the string lexicographically smaller
            while (!stack.isEmpty() && stack.peek() <= min) {
                res.append(stack.pop());
            }
        }
        return res.toString();
    }
}

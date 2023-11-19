package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/19/2023.
 * #2284 https://leetcode.com/problems/sender-with-largest-word-count/
 */
public class SenderWithLargestWordCount {

    // time O(m * n), space O(m)
    public String largestWordCount(String[] messages, String[] senders) {
        int largestCount = 0;
        String largestSender = "";
        Map<String, Integer> counter = new HashMap<>();
        for (int i = 0; i < messages.length; i++) {
            int wordCount = countWords(messages[i].toCharArray());
            counter.put(senders[i], counter.getOrDefault(senders[i], 0) + wordCount);
            int count = counter.get(senders[i]);
            if (count > largestCount || (count == largestCount && senders[i].compareTo(largestSender) > 0)) {
                largestCount = count;
                largestSender = senders[i];
            }
        }
        return largestSender;
    }

    private int countWords(char[] arr) {
        int res = 0;
        for (char c : arr) {
            res += c == ' ' ? 1 : 0;
        }
        return res + 1;
    }
}

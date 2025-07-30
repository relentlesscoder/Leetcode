package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 07/29/2025.
 * #3527 https://leetcode.com/problems/find-the-most-common-response/
 */
public class FindTheMostCommonResponse {

    // time O(n), space O(n)
    public String findCommonResponse(List<List<String>> responses) {
        int maxFreq = 0;
        Map<String, Integer> freq = new HashMap<>();
        for (List<String> response : responses) {
            Set<String> uniqueWords = new HashSet<>();
            for (String word : response) {
                if (uniqueWords.add(word)) {
                    freq.put(word, freq.getOrDefault(word, 0) + 1);
                    maxFreq = Math.max(maxFreq, freq.get(word));
                }
            }
        }
        String res = "|";
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            int count = entry.getValue();
            String word = entry.getKey();
            if (count == maxFreq && word.compareTo(res) < 0) {
                res = word;
            }
        }
        return res;
    }
}

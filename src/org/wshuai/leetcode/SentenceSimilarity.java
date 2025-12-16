package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 08/30/2019.
 * #0734 https://leetcode.com/problems/sentence-similarity/
 */
public class SentenceSimilarity {

    // time O(n + m * L), space O(m)
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2,
                                       List<List<String>> similarPairs) {
        int n1 = sentence1.length, n2 = sentence2.length;
        if (n1 != n2) {
            return false;
        }
        Map<String, Set<String>> dict = new HashMap<>();
        for (List<String> pair : similarPairs) { // O(m)
            dict.computeIfAbsent(pair.get(0), key -> new HashSet<>()).add(pair.get(1)); // O(L)
            dict.computeIfAbsent(pair.get(1), key -> new HashSet<>()).add(pair.get(0));
        }
        for (int i = 0; i < n1; i++) { // O(n)
            if (sentence1[i].equals(sentence2[i])) {
                continue;
            }
            if (!dict.containsKey(sentence1[i])
                    || !dict.containsKey(sentence2[i])
                    || !dict.get(sentence1[i]).contains(sentence2[i])) {
                return false;
            }
        }
        return true;
    }
}

package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 10/07/2025.
 * #1813 https://leetcode.com/problems/sentence-similarity-iii/
 */
public class SentenceSimilarityIII {

    // time O(m + n), space O(m + n)
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] words1 = sentence1.split(" "), words2 = sentence2.split(" ");
        if (words1.length > words2.length) {
            return areSentencesSimilar(sentence2, sentence1);
        }
        int start = 0, end1 = words1.length - 1, end2 = words2.length - 1;
        while (start < words1.length && words1[start].equals(words2[start])) {
            start++;
        }
        while (end1 >= 0 && words1[end1].equals(words2[end2])) {
            end1--;
            end2--;
        }
        return end1 < start;
    }

    // time O(m + n), space O(m + n)
    public boolean areSentencesSimilarQueue(String sentence1, String sentence2) {
        Deque<String> queue1 = new ArrayDeque<>(Arrays.asList(sentence1.split(" "))),
                queue2 = new ArrayDeque<>(Arrays.asList(sentence2.split(" ")));
        while (!queue1.isEmpty()
                && !queue2.isEmpty()
                && queue1.peekFirst().equals(queue2.peekFirst())
        ) {
            queue1.pollFirst();
            queue2.pollFirst();
        }
        while (!queue1.isEmpty()
                && !queue2.isEmpty()
                && queue1.peekLast().equals(queue2.peekLast())
        ) {
            queue1.pollLast();
            queue2.pollLast();
        }
        return queue1.isEmpty() || queue2.isEmpty();
    }
}

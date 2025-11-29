package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 12/03/2016.
 * #0030 https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 */
public class SubstringWithConcatenationOfAllWords {

    // time O((m + n) * WL), space O(m * WL)
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), m = words.length, wordLength = words[0].length(), k = wordLength * m;
        Map<String, Integer> target = new HashMap<>();
        for (String w : words) { // O(m)
            target.merge(w, 1, Integer::sum);
        }
        // Note that we only need to consider start index in [0, WL), indexes from [WL, n-1] are all
        // covered in one of the sliding window in [0, WL)
        // 	e.g. s = abcdefghidefabcjkl words = ["abc","def"]
        // Sliding window starts at index 3 will be covered in window starts with index 0
        for (int start = 0; start < wordLength; start++) { // O(WL)
            Map<String, Integer> freq = new HashMap<>();
            int overloaded = 0;
            for (int end = start + wordLength; end <= n; end += wordLength) { // O(n / WL)
                String tail = s.substring(end - wordLength, end); // O(WL)
                int count = freq.merge(tail, 1, Integer::sum);
                if (count == target.getOrDefault(tail, 0) + 1) {
                    overloaded++;
                }
                int left = end - k;
                if (left < start) {
                    continue;
                }
                if (overloaded == 0) {
                    res.add(left);
                }
                String head = s.substring(left, left + wordLength); // O(WL)
                count = freq.merge(head, -1, Integer::sum);
                if (count == target.getOrDefault(head, 0)) {
                    overloaded--;
                }
            }
        }
        return res;
    }
}

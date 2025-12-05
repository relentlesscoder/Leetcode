package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/23/2019.
 * #1297 https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/
 */
public class MaximumNumberOfOccurrencesOfASubstring {

    // time O(n * minSize), space O(n * minSize)
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        // https://leetcode.cn/problems/maximum-number-of-occurrences-of-a-substring/solutions/3747384/nao-jin-ji-zhuan-wan-ding-chang-hua-dong-0hol/
        int res = 0, n = s.length(), count = 0;
        Map<String, Integer> map = new HashMap<>();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            if (freq[s.charAt(i) - 'a']++ == 0) {
                count++;
            }
            if (i - minSize + 1 < 0) {
                continue;
            }
            if (count <= maxLetters) {
                String curr = s.substring(i - minSize + 1, i + 1);
                int cnt = map.merge(curr, 1, Integer::sum);
                res = Math.max(res, cnt);
            }
            if (--freq[s.charAt(i - minSize + 1) - 'a'] == 0) {
                count--;
            }
        }
        return res;
    }
}

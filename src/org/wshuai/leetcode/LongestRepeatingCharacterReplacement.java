package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #0424 https://leetcode.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharacterReplacement {

    // time O(n), space O(1)
    public int characterReplacementSlidingWindow(String s, int k) {
		// https://leetcode.cn/problems/longest-repeating-character-replacement/solutions/586648/ti-huan-hou-de-zui-chang-zhong-fu-zi-fu-eaacp/
        int res = 0, n = s.length();
        int[] freq = new int[26];
        // We only need to update max occurrence when it becomes larger,
        // because only that can we generate a longer valid substring.
        // If we can't surpass the historic max occurrence, then we can
        // not generate a longer valid substring from current "start".
        for (int i = 0, j = 0, maxCount = 0; i < n; i++) {
            maxCount = Math.max(maxCount, ++freq[s.charAt(i) - 'A']);
            while (i - j + 1 - maxCount > k) {
                --freq[s.charAt(j++) - 'A'];
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // time O(n), space O(1)
    public int characterReplacementSlidingWindowMaintainMax(String s, int k) {
        int res = 0, n = s.length();
        int[] freq = new int[26];
        for (int i = 0, j = 0; i < n; i++) {
            freq[s.charAt(i) - 'A']++;
            while (min(freq) > k) {
                --freq[s.charAt(j++) - 'A'];
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    private int min(int[] freq) {
        int sum = 0, max = 0;
        for (int f : freq) {
            max = Math.max(max, f);
            sum += f;
        }
        return sum - max;
    }
}

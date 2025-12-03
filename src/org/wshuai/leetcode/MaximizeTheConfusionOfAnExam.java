package org.wshuai.leetcode;

/**
 * Created by Wei on 09/11/2023.
 * #2024 https://leetcode.com/problems/maximize-the-confusion-of-an-exam/
 */
public class MaximizeTheConfusionOfAnExam {

    // time O(n), space O(1)
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int res = 0, n = answerKey.length();
        for (int i = 0, j = 0, maxCount = 0, count = 0; i < n; i++) {
            count += answerKey.charAt(i) == 'T' ? 1 : 0;
            // The optimal solution is to replace the character with less
            // frequency, so we maintain a maxCount to track the maximum
            // frequency in current window (no matter if it is T or F). Then
            // we can check condition i - j + 1 - maxCount > k to see if
            // there are enough operations.
            maxCount = Math.max(maxCount, Math.max(count, i - j + 1 - count));
            // If we run out of operations, advance left end of the window
            while (i - j + 1 - maxCount > k) {
                count -= answerKey.charAt(j++) == 'T' ? 1 : 0;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // time O(n), space O(1)
    public int maxConsecutiveAnswersMaintainMin(String answerKey, int k) {
        int res = 0, n = answerKey.length(), countT = 0, countF = 0;
        for (int i = 0, j = 0; i < n; i++) {
            countT += answerKey.charAt(i) == 'T' ? 1 : 0;
            countF += answerKey.charAt(i) == 'T' ? 0 : 1;
            // It doesn't matter if the valid subarray is all Ts or Fs,
            // it is a good candidate unless we can make all the characters
            // the same so evaluate condition Math.min(countT, countF) > k.
            while (Math.min(countT, countF) > k) {
                countT -= answerKey.charAt(j) == 'T' ? 1 : 0;
                countF -= answerKey.charAt(j) == 'T' ? 0 : 1;
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}

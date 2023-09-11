package org.wshuai.leetcode;

/**
 * Created by Wei on 09/11/2023.
 * #2024 https://leetcode.com/problems/maximize-the-confusion-of-an-exam/
 */
public class MaximizeTheConfusionOfAnExam {

    // time O(n), space O(1)
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int res = 0, countT = 0, countF = 0;
        for (int i = 0, j = 0; j < answerKey.length(); j++) {
            countT += (answerKey.charAt(j) == 'T') ? 1 : 0;
            countF += (answerKey.charAt(j) == 'F') ? 1 : 0;
            while (Math.min(countT, countF) > k) {
                countT -= (answerKey.charAt(i) == 'T') ? 1 : 0;
                countF -= (answerKey.charAt(i) == 'F') ? 1 : 0;
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}

package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 12/03/2019.
 * #1255 https://leetcode.com/problems/maximum-score-words-formed-by-letters/
 */
public class MaximumScoreWordsFormedByLetters {
    private int res = 0;

	// time O(), space O()
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        res = 0;
        int n = words.length;
        int[][] wordMap = new int[n][26];
		// 统计构造每个单词需要的字符数
        for (int i = 0; i < n; i++) { // O(n)
            int[] freq = new int[26];
            for (char c : words[i].toCharArray()) {
                freq[c - 'a']++;
            }
            wordMap[i] = freq;
        }
		// 统计提供的字符数量和该字符相应的得分
        int[][] letterMap = new int[26][2];
        for (char c : letters) {
            letterMap[c - 'a'][0]++;
        }
        for (int i = 0; i < 26; i++) {
            letterMap[i][1] = score[i];
        }
        dfs(0, 0, wordMap, letterMap);
        return res;
    }

    private void dfs(int x, int score, int[][] wordMap, int[][] letterMap) {
		// 选与不选
        if (x == wordMap.length) {
            res = Math.max(res, score);
            return;
        }
		// 不构造当前的单词
        dfs(x + 1, score, wordMap, letterMap);
		// 构造当前的单词
        int s = 0; // 构造当前单词能获得的得分
        boolean canForm = true; // 检查当前拥有的所有字符是否能构造当前的单词
        int[] freq = wordMap[x]; // 得到构造当前单词需要的字符数量
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) {
                continue;
            }
            s += freq[i] * letterMap[i][1]; // 计算使用字符的分数
            letterMap[i][0] -= freq[i]; // 将所需字符数扣除
            if (letterMap[i][0] < 0) { // 字符不够
                canForm = false;
            }
        }
        if (canForm) { // 如果有任何字符不够则不能构造单词
            dfs(x + 1, score + s, wordMap, letterMap);
        }
		// 恢复现场
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) {
                continue;
            }
            letterMap[i][0] += freq[i];
        }
    }
}

package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 04/10/2020.
 * #0720 https://leetcode.com/problems/longest-word-in-dictionary/
 */
public class LongestWordInDictionary {

    // time O(n + sum(L)), space O(sum(L))
    public String longestWord(String[] words) {
        String res = "";
        int max = 0;
        TrieNode root = new TrieNode();
        // 遍历数组，把单词依次插入字典树中。如果能只插入最后一个字符则当前单
        // 词可以被成功构造。
        for (String word : words) { // O(n)
            insert(word, root); // O(L)
        }
        // 再次遍历数组，在字典树中查询每个单词，如果查询每一个字符都得到字典
        // 树中的一个终结节点则当前单词可以被成功构造。找出这里面最长且字典序
        // 最小的单词即为答案。
        for (String word : words) {
            if (search(word, root) && (word.length() > max
                    || word.length() == max && word.compareTo(res) < 0)) {
                res = word;
                max = word.length();
            }
        }
        return res;
    }

    private boolean search(String word, TrieNode root) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr = curr.get(c);
            if (!curr.isEnd()) { // 每一步都是一个终结节点
                return false;
            }
        }
        return true;
    }

    private void insert(String word, TrieNode root) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.containsKey(c)) {
                curr.put(c, new TrieNode());
            }
            curr = curr.get(c);
        }
        curr.setEnd();
    }

    // time O(sum(L) + n * log(n)), space O(sum(L))
    public String longestWordSorting(String[] words) {
        // 把数组排序，排序后数组按照字典序排列(同样字典序短的在前长的在后)。
        // 遍历数组，把单词依次插入字典树中。如果能只插入最后一个字符则当前单
        // 词可以被成功构造。时间复杂度的瓶颈是排序。
        String res = "";
        int max = 0;
        Arrays.sort(words); // O(n * log(n))
        TrieNode root = new TrieNode();
        for (String word : words) { // O(n)
            if (tryInsert(word, root) && word.length() > max) { // O(L)
                res = word;
                max = word.length();
            }
        }
        return res;
    }

    private boolean tryInsert(String word, TrieNode root) {
        TrieNode curr = root;
        int i = 0;
        for (; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.containsKey(c)) {
                break;
            }
            curr = curr.get(c);
        }
        // 判断是否已经达到最后一个字符
        if (i == word.length() - 1) {
            curr.put(word.charAt(word.length() - 1), new TrieNode());
            return true;
        }
        return false;
    }

    private static class TrieNode {

        private static final int N = 26;
        private TrieNode[] nodes;
        private boolean end;

        public TrieNode() {
            nodes = new TrieNode[N];
            end = false;
        }

        public boolean containsKey(char c) {
            return nodes[c - 'a'] != null;
        }

        public TrieNode get(char c) {
            return nodes[c - 'a'];
        }

        public void put(char c, TrieNode node) {
            nodes[c - 'a'] = node;
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd() {
            end = true;
        }
    }
}

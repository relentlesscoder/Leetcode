package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 12/12/2025.
 * #3597 https://leetcode.com/problems/partition-string/
 */
public class PartitionString {

    // time O(n * sqrt(n)), space O(n)
    public List<String> partitionStringHashSet(String s) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (set.add(sb.toString())) {
                res.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return res;
    }

    // time O(n * sqrt(n)), space O(n)
    public List<String> partitionStringTrie(String s) {
        List<String> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (!search(sb, root)) {
                res.add(sb.toString());
                insert(sb, root);
                sb = new StringBuilder();
            }
        }
        return res;
    }

    public void insert(StringBuilder word, TrieNode root) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.containsKey(c)) {
                curr.put(c, new TrieNode());
            }
            curr = curr.get(c);
        }
        curr.setEnd();
    }

    // time O(n), space O(1)
    public boolean search(StringBuilder word, TrieNode root) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.containsKey(c)) {
                return false;
            }
            curr = curr.get(c);
        }
        return curr.isEnd();
    }

    private static class TrieNode {

        private final TrieNode[] nodes;
        private boolean end;

        public TrieNode() {
            nodes = new TrieNode[26];
        }

        public TrieNode get(char c) {
            return nodes[c - 'a'];
        }

        public boolean containsKey(char c) {
            return nodes[c - 'a'] != null;
        }

        public void put(char c, TrieNode node) {
            nodes[c - 'a'] = node;
        }

        public boolean isEnd() {
            return this.end;
        }

        public void setEnd() {
            this.end = true;
        }
    }
}

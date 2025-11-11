package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2025.
 * #2213 https://leetcode.com/problems/longest-substring-of-one-repeating-character/
 */
public class LongestSubstringOfOneRepeatingCharacter {

    // time O(k * log(n)), space O(n)
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length(), k = queryIndices.length;
        char[] chars = s.toCharArray();
        SegmentTree st = new SegmentTree(chars);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            chars[queryIndices[i]] = queryCharacters.charAt(i);
            st.update(queryIndices[i] + 1);
            res[i] = st.rangeMax(1, n);
        }
        return res;
    }

    private static class SegmentTree {

        private final int n;
        private final char[] chars;
        private final int[] pre, suf, max;

        public SegmentTree(char[] chars) {
            this.chars = chars;
            this.n = chars.length;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n));
            pre = new int[size]; // Length for prefix with one repeating character
            suf = new int[size]; // Length for suffix with one repeating character
            max = new int[size]; // Maximum length with one repeating character
            build(1, 1, n);
        }

        private void maintain(int node, int left, int right) {
            int lc = node * 2, rc = node * 2 + 1;
            pre[node] = pre[lc]; // Set prefix to prefix of the left child node
            suf[node] = suf[rc]; // Set suffix to suffix of the right child node
            max[node] = Math.max(max[lc], max[rc]);
            int mid = (left + right) / 2;
            // Start merging if middle characters are matching
            if (chars[mid - 1] == chars[mid]) {
                // If suffix[lc] == len(lc) == prefix[lc], the entire substring for
                // left child contains only one character. Then we can extend the
                // prefix for the parent node to add prefix of right child.
                if (suf[lc] == mid - left + 1) {
                    pre[node] += pre[rc];
                }
                // If prefix[rc] == len(rc) == suffix[rc], the entire substring for
                // right child contains only one character. Then we can extend the
                // suffix for the parent node to add suffix of left child.
                if (pre[rc] == right - mid) {
                    suf[node] += suf[lc];
                }
                // Now suf[lc] + pre[rc] becomes a candidate
                max[node] = Math.max(max[node], suf[lc] + pre[rc]);
            }
        }

        private void build(int node, int left, int right) {
            if (left == right) {
                pre[node] = suf[node] = max[node] = 1;
                return;
            }
            int mid = (left + right) / 2;
            build(node * 2, left, mid);
            build(node * 2 + 1, mid + 1, right);
            maintain(node, left, right);
        }

        private void update(int node, int left, int right, int index) {
            if (left == right) {
                return;
            }
            int mid = (left + right) / 2;
            if (index <= mid) {
                update(node * 2, left, mid, index);
            } else {
                update(node * 2 + 1, mid + 1, right, index);
            }
            maintain(node, left, right);
        }

        private int rangeMax(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return max[node];
            }
            int mid = (left + right) / 2;
            if (end <= mid) {
                return rangeMax(node * 2, left, mid, start, end);
            }
            if (start > right) {
                return rangeMax(node * 2 + 1, mid + 1, right, start, end);
            }
            int lmax = rangeMax(node * 2, left, mid, start, end);
            int rmax = rangeMax(node * 2 + 1, mid + 1, right, start, end);
            return Math.max(lmax, rmax);
        }

        public void update(int index) {
            update(1, 1, n, index);
        }

        public int rangeMax(int start, int end) {
            return rangeMax(1, 1, n, start, end);
        }
    }
}

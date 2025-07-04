package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 07/04/2025.
 * #3211 https://leetcode.com/problems/generate-binary-strings-without-adjacent-zeros/
 */
public class GenerateBinaryStringsWithoutAdjacentZeros {

    // time O(2^n), space O(2^n)
    public List<String> validStrings(int n) {
        List<String> res = new ArrayList<>();
        backtracking(new StringBuilder(), 1, n, res);
        return res;
    }

    private void backtracking(StringBuilder str, int prev, int n, List<String> res) {
        if (n == 0) {
            res.add(str.toString());
            return;
        }
        str.append('1');
        backtracking(str, 1, n - 1, res);
        str.deleteCharAt(str.length() - 1);
        if (prev == 1) {
            str.append('0');
            backtracking(str, 0, n - 1, res);
            str.deleteCharAt(str.length() - 1);
        }
    }

    // time O(2^n), space O(2^n)
    public List<String> validStringsBFS(int n) {
        int len = n;
        List<String> res = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        queue.offer(1);
        n--;
        while (n-- > 0) {
            int size = queue.size();
            while (size-- > 0) {
                Integer curr = queue.poll();
                if ((curr & 1) == 1) {
                    queue.offer(curr << 1);
                }
                queue.offer((curr << 1) + 1);
            }
        }
        while (!queue.isEmpty()) {
            int num = queue.poll();
            char[] arr = new char[len];
            for (int i = len - 1; i >= 0; i--) {
                arr[i] = (char)((num & 1) + '0');
                num = num >> 1;
            }
            res.add(String.valueOf(arr));
        }
        return res;
    }
}

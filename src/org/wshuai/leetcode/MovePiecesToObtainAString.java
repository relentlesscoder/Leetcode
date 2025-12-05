package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/03/2025.
 * #2337 https://leetcode.com/problems/move-pieces-to-obtain-a-string/
 */
public class MovePiecesToObtainAString {

    // time O(n), space O(1)
    public boolean canChange(String start, String target) {
        int m = start.length(), n = target.length(), i = 0, j = 0;
        if (m != n) {
            return false;
        }
        while (i < m || j < n) {
            while (i < m && start.charAt(i) == '_') {
                i++;
            }
            while (j < n && target.charAt(j) == '_') {
                j++;
            }
            if (i == m || j == n) {
                return i == m && j == n;
            }
            char sc = start.charAt(i), tc = target.charAt(j);
            if (sc == tc && (
                    (sc == 'L' && i >= j) ||
                            (sc == 'R' && i <= j)
            )) {
                i++;
                j++;
            } else {
                return false;
            }
        }
        return true;
    }

    // time O(n), space O(n)
    public boolean canChangeQueue(String start, String target) {
        int m = start.length(), n = target.length();
        if (m != n) {
            return false;
        }
        Deque<int[]> startQueue = new ArrayDeque<>()
                , targetQueue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (target.charAt(i) != '_') {
                targetQueue.offer(new int[]{i, target.charAt(i) == 'L' ? 0 : 1});
            }
            if (start.charAt(i) != '_') {
                startQueue.offer(new int[]{i, start.charAt(i) == 'L' ? 0 : 1});
            }
        }
        if (startQueue.size() != targetQueue.size()) {
            return false;
        }
        while (!startQueue.isEmpty()) {
            int[] s = startQueue.poll(), t = targetQueue.poll();
            if (!((s[1] == 0 && t[1] == 0 && s[0] >= t[0]) ||
                    (s[1] == 1 && t[1] == 1 && s[0] <= t[0]))) {
                return false;
            }
        }
        return true;
    }
}

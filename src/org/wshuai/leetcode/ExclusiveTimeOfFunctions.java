package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 03/20/2020.
 * #0636 https://leetcode.com/problems/exclusive-time-of-functions/
 */
public class ExclusiveTimeOfFunctions {

    // time O(n), space O(n)
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Deque<int[]> stack = new ArrayDeque<>();
        for (String log : logs) {
            String[] strs = log.split(":");
            int funcId = Integer.parseInt(strs[0]);
            int timestamp = Integer.parseInt(strs[2]);
            if (strs[1].equals("start")) {
                stack.push(new int[]{funcId, timestamp});
            } else {
                int time = timestamp - stack.pop()[1] + 1;
                if (!stack.isEmpty()) {
                    res[stack.peek()[0]] -= time;
                }
                res[funcId] += time;
            }
        }
        return res;
    }
}

package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by Wei on 08/05/2025.
 * #3362 https://leetcode.com/problems/zero-array-transformation-iii/
 */
public class ZeroArrayTransformationIII {

    // time O(n + m * log(m)), space O(n + m)
    public int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        int[] deltaArray = new int[nums.length + 1];
        int operations = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            operations += deltaArray[i];
            while (j < queries.length && queries[j][0] == i) {
                heap.offer(queries[j++][1]);
            }
            while (operations < nums[i] && !heap.isEmpty() && heap.peek() >= i) {
                operations++;
                deltaArray[heap.poll() + 1] -= 1;
            }
            if (operations < nums[i]) {
                return -1;
            }
        }
        return heap.size();
    }
}

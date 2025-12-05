package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/24/2025.
 * #1776 https://leetcode.com/problems/car-fleet-ii/
 */
public class CarFleetII {

    private static final double INF = Double.POSITIVE_INFINITY;

    // time O(n), space O(n)
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] res = new double[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            res[i] = INF;
            // If cars[i] is slower than stack top cars[j], it can't catch up with
            // it before it collide with another car in front of it or disappear.
            // We can pop cars[j] out and try chasing with cars in front of it.
            //   e.g. [[1,2],[2,4],[4,5],[7,3]]
            // cars[1] can't catch up with cars[2] but it can catches up with cars[3]
            // (cars[2] will collide with cars[3])
            // Final answer is [-1.00000,5.00000,1.50000,-1.00000]
            while (!stack.isEmpty() && cars[i][1] <= cars[stack.peek()][1]) {
                stack.pop();
            }
            // If possible to catch up if cars[i] is faster than stack top cars[j]
            //   e.g. [[1,5],[5,4],[6,3]]
            // cars[1] collide with cars[2] at second 1 so res[1] = 1. cars[0] can catch
            // up with cars[1] at second 4 but since 4 > res[1] it can't collide with
            // cars[1] before it collide with cars[2]. So in this case we pop it out and
            // continue chasing with cars[2].
            // Final answer is [2.50000,1.00000,-1.00000]
            while (!stack.isEmpty()) {
                double time = calc(cars[i], cars[stack.peek()]);
                // If time <= res[stack.peek()] (the time it collide with another car)
                // update its collision time and break.
                if (time <= res[stack.peek()]) {
                    res[i] = time;
                    break;
                }
                stack.pop();
            }
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            res[i] = res[i] == INF ? -1 : res[i];
        }
        return res;
    }

    private double calc(int[] c1, int[] c2) {
        return 1.0 * (c2[0] - c1[0]) / (c1[1] - c2[1]);
    }
}

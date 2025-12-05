package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/29/2023.
 * #2126 https://leetcode.com/problems/destroying-asteroids/
 */
public class DestroyingAsteroids {

    // time O(n * log(n)), space O(n)
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long sum = mass;
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] > sum) {
                return false;
            } else {
                sum += asteroids[i];
            }
        }
        return true;
    }

    // time O(n * log(n)), space O(n)
    public boolean asteroidsDestroyedPriorityQueue(int mass, int[] asteroids) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        long sum = 0, planet = mass;
        for (int a : asteroids) {
            if (a <= mass) {
                sum += a;
            } else {
                minQueue.offer(a);
            }
        }
        planet += sum;
        while (!minQueue.isEmpty() && planet >= minQueue.peek()) {
            planet += minQueue.poll();
        }
        return minQueue.isEmpty();
    }
}

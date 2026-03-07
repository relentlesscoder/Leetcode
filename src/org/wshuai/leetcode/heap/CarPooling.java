package org.wshuai.leetcode.heap;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wei on 09/30/2019.
 * #1094 https://leetcode.com/problems/car-pooling/
 */
public class CarPooling {

    // time O(n + MAX), space O(MAX)
    public boolean carPooling(int[][] trips, int capacity) {
        // 利用差分数组统计在每个点上车和下车的人数，然后遍历每个点确保
        // 在每个点车上的乘客人数不超过限载人数。
        int max = 0;
        // 预先计算最远的地点。
        for (int[] t : trips) { // O(n)
            max = Math.max(max, t[2]);
        }
        // 建立差分数组
        int[] diff = new int[max + 1];
        for (int[] t : trips) { // O(n)
            int cnt = t[0], from = t[1], to = t[2];
            diff[from] += cnt;
            diff[to] -= cnt;
        }
        // 利用差分数组统计每个点的乘客数
        for (int i = 0, sum = 0; i <= max; i++) { // O(MAX)
            sum += diff[i];
            if (sum > capacity) {
                return false;
            }
        }
        return true;
    }

    // time O(n * log(n)), space O(n)
    public boolean carPoolingTreeMap(int[][] trips, int capacity) {
        // 基于有序哈希表的实现，好处是不用实现计算最远的地点。
        Map<Integer, Integer> counts = new TreeMap<>();
        for (int[] t : trips) {
            counts.merge(t[1], t[0], Integer::sum);
            counts.merge(t[2], -t[0], Integer::sum);
        }
        int current = 0;
        for (int key : counts.keySet()) {
            current += counts.get(key);
            if (current > capacity) {
                return false;
            }
        }
        return true;
    }
}

package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Wei on 06/21/2020.
 * #1488 https://leetcode.com/problems/avoid-flood-in-the-city/
 */
public class AvoidFloodInTheCity {

    // time O(n), space O(n)
    public int[] avoidFloodUnionFind(int[] rains) {
        int n = rains.length;
        int[] res = new int[n], roots = new int[n + 1];
        // 维护一个并查集存已经遍历过的不下雨的日期
        Arrays.setAll(roots, i -> i);
        // 维护一个哈希表存已满的湖泊和上次被灌满水的日期
        Map<Integer, Integer> fullLakes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int lake = rains[i];
            // 如果不下雨
            if (lake == 0) {
                // 随便抽干一个湖泊
                res[i] = 1;
                continue;
            }
            // 如果今天下雨并且当前湖泊已满
            if (fullLakes.containsKey(lake)) {
                int fullDay = fullLakes.get(lake);
                // 从并查集中找到大于上次湖泊被灌满水的日期的最小日期
                int day = find(fullDay + 1, roots);
                // 如果这个日期大于等于当前日期，说明不可能防止当前湖泊发生洪水
                if (day >= i) {
                    return new int[0];
                }
                // 将找到的日期用于抽干当前湖泊的水
                res[day] = lake;
                // 将使用过了的日期在并查集中的根结点设为下一个日期 (等同于删除该日期)
                // 示例1: [1,2,0,0,2,1]
                // 当遍历到索引4时，用并查集找到的前一个2(索引1)的根结点为索引2. 使用
                // 这个日期来抽干湖中的水，此时将索引2的根结点设为3. 当遍历到索引5时，
                // 用并查集找到的前一个1(索引0)的根结点为索引3.
                roots[day] = find(day + 1, roots);
            }
            res[i] = -1;
            // 将当前日期在并查集中的根结点设为下一个日期 (等同于删除该日期)
            // 示例2: [3,1,2,0,0,2,1,3]
            // 当遍历到索引7时，用并查集找到的前一个3(索引0)的根结点为索引7所以无法找到
            // 可用的日期来抽干湖中的水.
            roots[i] = i + 1;
            // 将灌满水的当前湖泊和日期加入哈希表
            fullLakes.put(lake, i);
        }
        return res;
    }

    private int find(int x, int[] roots) {
        if (x != roots[x]) {
            roots[x] = find(roots[x], roots);
        }
        return roots[x];
    }

    // time O(n * log(n)), space O(n)
    public int[] avoidFloodGreedy(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
		// 维护一个平衡树存已经遍历过的不下雨的日期
        TreeSet<Integer> dryDays = new TreeSet<>();
		// 维护一个哈希表存已满的湖泊和上次被灌满水的日期
        Map<Integer, Integer> fullLakes = new HashMap<>();
        for (int i = 0; i < n; i++) { // O(n)
            int lake = rains[i];
			// 如果不下雨
            if (lake == 0) {
				// 随便抽干一个湖泊
                res[i] = 1;
				// 把当前日期加入平衡树
                dryDays.add(i); // O(log(n))
                continue;
            }
			// 如果今天下雨并且当前湖泊已满
            if (fullLakes.containsKey(lake)) {
				// 从平衡树中找到大于上次湖泊被灌满水的日期的最小日期
                Integer day = dryDays.higher(fullLakes.get(lake)); // O(log(n))
				// 如果找不到，说明不可能防止当前湖泊发生洪水
                if (day == null) {
                    return new int[0];
                }
				// 将找到的日期用于抽干当前湖泊的水
                res[day] = lake;
				// 从哈希表中删掉这个日期
                dryDays.remove(day);
            }
            res[i] = -1;
			// 将灌满水的当前湖泊和日期加入哈希表
            fullLakes.put(lake, i);
        }
        return res;
    }
}

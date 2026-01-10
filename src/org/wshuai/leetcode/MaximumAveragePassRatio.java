package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/20/2023.
 * #1792 https://leetcode.com/problems/maximum-average-pass-ratio/
 */
public class MaximumAveragePassRatio {

    // time O((m + n) * log(n)), space O(n)
    public double maxAverageRatioInteger(int[][] classes, int extraStudents) {
        // 使用整型计算, 利用 a / b > c / d 则 a * d > c * b 来排序。
        int n = classes.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) ->
                Long.compare(1L * (b[1] - b[0]) * (a[1] + 1) * a[1],
						1L * (a[1] - a[0]) * (b[1] + 1) * b[1]));
        for (int[] c : classes) {
            queue.offer(new int[]{c[0], c[1]});
        }
        while (extraStudents-- > 0) {
            int[] curr = queue.poll();
            queue.offer(new int[]{curr[0] + 1, curr[1] + 1});
        }
        double res = 0.0;
        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            res += 1.0 * top[0] / top[1];
        }
        return res / n;
    }

    // time O((m + n) * log(n)), space O(n)
    public double maxAverageRatioFloat(int[][] classes, int extraStudents) {
        // 使用浮点数计算 (有误差)
        // 计算班级通过人数加1后通过率取得的增量:
        //   (x + 1) / (y + 1) - x / y
        //   (y - x) / (y + 1) * y
        int n = classes.length;
        PriorityQueue<double[]> queue = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        for (int[] c : classes) { // O(n)
            double gain = 1.0 * (c[1] - c[0]) / (1L * c[1] * (c[1] + 1));
            queue.offer(new double[]{gain, c[0], c[1]}); // O(log(n))
        }
        // 每次找到增量最大的班级来加人
        while (extraStudents-- > 0) { // O(m)
            double[] curr = queue.poll();
            double x = curr[1] + 1, y = curr[2] + 1;
            double gain = 1.0 * (y - x) / (1L * y * (y + 1));
            queue.offer(new double[]{gain, x, y}); // O(log(n))
        }
        // 计算所有人被加完后的平均通过率
        double res = 0.0;
        while (!queue.isEmpty()) {
            double[] top = queue.poll();
            res += 1.0 * top[1] / top[2];
        }
        return res / n;
    }
}

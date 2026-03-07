package org.wshuai.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/23/2023.
 * #2956 https://leetcode.com/problems/number-of-divisible-substrings/
 */
public class NumberOfDivisibleSubstrings {

    // time O(n * R), space O(n)
    public int countDivisibleSubstrings(String word) {
        // 根据题意子数组和能被子数组的长度整除 s % l = 0，并且子数组中的每个数都在范围
        // R = [min, max] 之内 (1 <= min <= max <= 9)。则 s / l 肯定也在同样的范
        // 围[min, max]之内, 所以我们可以遍历这个范围然后求出平均值为每个可能值的子数组。
        // 假设当前平均值为 a 而子数组为 [i, j] 长度为 l，则这些值一定满足下式:
        //   nums[i] + nums[i + 1] ... nums[j] = l * a
        //   (nums[i] - a) + (nums[i + 1] - a) ... (nums[j] - a) = 0
        // 用前缀和找到这样的子数组的数量即可。
        int res = 0, n = word.length(), max = 0, min = 9;
        // 将字符串转换为整形数组并且找到值的范围
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            int v = (word.charAt(i) - 'a' + 1) / 3 + 1;
            nums[i] = v;
            max = Math.max(max, v);
            min = Math.min(min, v);
        }
        // 如果只有一个值，则所有字符串都符合要求
        if (max == min) {
            return n * (n + 1) / 2;
        }
        // 遍历可能的平均值
        for (int v = min; v <= max; v++) {
            Map<Integer, Integer> cnt = new HashMap<>();
            cnt.put(0, 1);
            for (int i = 0, sum = 0; i < n; i++) {
                sum += nums[i] - v;
                res += cnt.getOrDefault(sum, 0);
                cnt.merge(sum, 1, Integer::sum);
            }
        }
        return res;
    }
}

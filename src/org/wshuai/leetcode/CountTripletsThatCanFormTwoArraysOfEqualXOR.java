package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 05/11/2020.
 * #1442 https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXOR {

    // time O(n), space O(n)
    public int countTriplets(int[] arr) {
        // 在下解的基础上还可以用前缀异或和来优化。如果子数组[j, i]的异或和是0，
        // 则前缀和 s[i] ^ s[j - 1] = 0 因为它们的共同前缀子数组中相同的元素
        // 都可以被两两抵消 (异或为 0 )。根据下解的分析，每个长度为 l 的子数组
        // 都可划分为 l - 1 个不同的三元组。假设对当前索引 i 前缀异或和 s[i],
        // 在前缀子数组中有 j0, j1, ..., jm 个相同的前缀异或和，则它们可以形
        // 成的不同的三元组的总数为:
        //   (i - j0 - 1) + (i - j1 - 1) + ... + (i - jm - 1)
        //   m * (i - 1) - (j0 + j1 + ... + jm)
        // 所以我们需要知道这些前缀异或和的数量和其对应的所有索引的和。
        int res = 0, n = arr.length;
        // 维护一个哈希表键为前缀异或和，值为异或和的数量和其对应的所有索引的和。
        Map<Integer, int[]> count = new HashMap<>();
        count.put(0, new int[]{1, -1});
        for (int i = 0, xor = 0; i < n; i++) {
            xor ^= arr[i];
            int[] cnt = count.getOrDefault(xor, new int[]{0, 0});
            // 计算以当前元素结尾的三元组的总数
            res += cnt[0] * (i - 1) - cnt[1];
            // 维护哈希表
            count.put(xor, new int[]{cnt[0] + 1, cnt[1] + i});
        }
        return res;
    }

    // time O(n^2), space O(1)
    public int countTripletsBF(int[] arr) {
        // 根据题意，找三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length)
        //   a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
        //   b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
        // 满足 a == b
        // 如果 a == b 则 a ^ b = 0，所以实际上我们需要找到异或和为0的子数组[i, k]。
        // 因为 i < j <= k，所以没个这样的子数组都可以划分为 k - i 个不同的三元组:
        //   i, j, k 其中 j 的取值范围是 [i + 1, k]
        // 因为数组长度小于等于300，所以可以直接暴力求解。
        int res = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            int xor = arr[i];
            for (int j = i + 1; j < n; j++) {
                xor ^= arr[j];
                if (xor == 0) {
                    res += j - i;
                }
            }
        }
        return res;
    }
}

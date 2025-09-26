package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/25/2025.
 * #3337 https://leetcode.com/problems/total-characters-in-string-after-transformations-ii/
 */
public class TotalCharactersInStringAfterTransformationsII {

    private static final int MOD = (int)1e9 + 7;

    // time O(26^3 * log(t)), space O(26^2)
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        // https://leetcode.cn/problems/total-characters-in-string-after-transformations-ii/solutions/2967037/ju-zhen-kuai-su-mi-you-hua-dppythonjavac-cd2j/
        int[][] matrix = buildTransformationMatrix(nums);
        int[][] powMatrix = matrixPow(matrix, t);
        int[] freq = new int[26], lengths = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                lengths[j] = (int)((lengths[j] + 1L * freq[i] * powMatrix[i][j]) % MOD);
            }
        }

        int res = 0;
        for (int length : lengths) {
            res = (res + length) % MOD;
        }

        return res;
    }

    private int[][] buildTransformationMatrix(List<Integer> nums) {
        int[][] transform = new int[26][26];
        for (int i = 0; i < nums.size(); i++) {
            for (int step = 1; step <= nums.get(i); step++) {
                transform[i][(i + step) % 26]++;
            }
        }
        return transform;
    }

    private int[][] buildIdentityMatrix(int size) {
        int[][] identity = new int[size][size];
        for (int i = 0; i < size; i++) {
            identity[i][i] = 1;
        }
        return identity;
    }

    private int[][] matrixMult(int[][] a, int[][] b) {
        int size = a.length;
        int[][] res = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                long sum = 0;
                for (int k = 0; k < size; k++) {
                    sum = (sum + 1L * a[i][k] * b[k][j]) % MOD;
                }
                res[i][j] = (int)sum;
            }
        }
        return res;
    }

    private int[][] matrixPow(int[][] matrix, int n) {
        if (n == 0) {
            return buildIdentityMatrix(matrix.length);
        }
        if ((n & 1) == 1) {
            return matrixMult(matrix, matrixPow(matrix, n - 1));
        }
        int[][] half = matrixPow(matrix, n / 2);
        return matrixMult(half, half);
    }

    // time O(t * 26^2), space O(t * 26)
    public int lengthAfterTransformationsDP(String s, int t, List<Integer> nums) {
        // TLE
        int res = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        List<char[]> transform = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            char curr = (char)(i + 'a');
            char[] arr = new char[nums.get(i)];
            for (int j = 0; j < nums.get(i); j++) {
                arr[j] = curr = curr == 'z' ? 'a' : (char)(curr + 1);
            }
            transform.add(arr);
        }
        for (int i = 1; i <= t; i++) {
            int[] next = new int[26];
            for (int j = 0; j < 26; j++) {
                char[] arr = transform.get(j);
                for (int k = 0; k < arr.length; k++) {
                    if (k > 0) {
                        res = (res + freq[j]) % MOD;
                    }
                    next[arr[k] - 'a'] = (next[arr[k] - 'a'] + freq[j]) % MOD;
                }
            }
            freq = next;
        }
        return res;
    }
}

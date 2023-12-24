package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/23/2023.
 * #2956 https://leetcode.com/problems/number-of-divisible-substrings/
 */
public class NumberOfDivisibleSubstrings {

    // time O(n), space O(n)
    public int countDivisibleSubstrings(String word) {
        int res = 0; // https://leetcode.com/problems/number-of-divisible-substrings/solutions/4344411/simple-linear-if-take-9-as-a-constant-solutions-in-c-java-python/
        for (int i = 1; i <= 9; i++) {
            Map<Integer, Integer> prefixSum = new HashMap<>();
            prefixSum.put(0, 1);
            int sum = 0;
            for (char c : word.toCharArray()) {
                sum += 9 - ('z' - c) / 3 - i;
                int count = prefixSum.getOrDefault(sum, 0);
                res += count;
                prefixSum.put(sum, count + 1);
            }
        }
        return res;
    }
}

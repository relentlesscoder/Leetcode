package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/04/2023.
 * #2053 https://leetcode.com/problems/kth-distinct-string-in-an-array/description/
 */
public class KthDistinctStringInAnArray {

    // time O(n), space O(n)
    public String kthDistinct(String[] arr, int k) {
        int count = 0;
        Map<String, Integer> visited = new HashMap<>();
        for (String str : arr) {
            visited.put(str, visited.getOrDefault(str, 0) + 1);
        }
        for (String str : arr) {
            count += visited.get(str) == 1 ? 1 : 0;
            if (count == k) {
                return str;
            }
        }
        return "";
    }
}

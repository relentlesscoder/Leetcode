package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/07/2024.
 * #2899 https://leetcode.com/problems/last-visited-integers/
 */
public class LastVisitedIntegers {

    // time O(n), space O(n)
    public List<Integer> lastVisitedIntegers(List<String> words) {
        List<Integer> res = new ArrayList<>(), vals = new ArrayList<>();
        int index = -1;
        for (String word : words) {
            if (word.equals("prev")) {
                res.add(index >= 0 ? vals.get(index--) : -1);
            } else {
                vals.add(Integer.parseInt(word));
                index = vals.size() - 1;
            }
        }
        return res;
    }
}

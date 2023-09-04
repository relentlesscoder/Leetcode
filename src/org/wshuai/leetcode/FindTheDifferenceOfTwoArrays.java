package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 09/04/2023.
 * #2215 https://leetcode.com/problems/find-the-difference-of-two-arrays/description/
 */
public class FindTheDifferenceOfTwoArrays {

    // time O(n), space O(n)
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> onlyInNums1 = new ArrayList<>(), onlyInNums2 = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        for (int num : set1){
            if (!set2.contains(num)) {
                onlyInNums1.add(num);
            }
        }
        for (int num : set2){
            if (!set1.contains(num)) {
                onlyInNums2.add(num);
            }
        }
        res.add(onlyInNums1);
        res.add(onlyInNums2);
        return res;
    }
}

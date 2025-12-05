package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2025.
 * #3289 https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/
 */
public class TheTwoSneakyNumbersOfDigitville {

    // time O(n), space O(1)
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] != i && nums[nums[i]] != nums[i]) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        return new int[] {nums[n - 1], nums[n - 2]};
    }

    // time O(n), space O(n)
    public int[] getSneakyNumbersHashMap(int[] nums) {
        int i = 0;
        int[] res = new int[2], map = new int[nums.length - 2];
        for (int num : nums) {
            if (map[num] != 0) {
                res[i++] = num;
                if (i == 2) {
                    break;
                }
            }
            map[num]++;
        }
        return res;
    }
}

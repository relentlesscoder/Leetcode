package org.wshuai.leetcode;

/**
 * Created by Wei on 09/08/2016.
 * #0075 https://leetcode.com/problems/sort-colors/
 */
public class SortColors {

    // time O(n), space O(1)
    public void sortColors(int[] nums) {
        for (int p0 = 0, p1 = 0, i = 0; i < nums.length; i++) {
            int x = nums[i];
            // 把当前位置的值预设为2
            nums[i] = 2;
            // 当前数字是0或者1，都把1的右边界扩展1个位置。这样做可以保证：
            //   1. 当前数字是1，加上这个1。
            //   2. 当前数字是0，把整个1的子数组右移一位。然后下面对于0的
            //      操作会把原来的第一个1变成0. 整个操作相当于多加了一个0
            //      ，然后1的子数组右移一位。
            if (x <= 1) {
                nums[p1++] = 1;
            }
            // 当前数字为0，把0的有边界扩展一个位置
            if (x == 0) {
                nums[p0++] = 0;
            }
        }
    }

    // time O(n), space O(1)
    public void sortColorsCounting(int[] nums) {
        int[] counters = new int[3];
        for (int num : nums) {
            if (num == 0) {
                counters[0]++;
            } else if (num == 1) {
                counters[1]++;
            } else {
                counters[2]++;
            }
        }
        for (int i = 0, j = 0; i < 3; i++) {
            int cnt = counters[i];
            while (cnt-- > 0) {
                nums[j++] = i;
            }
        }
    }
}

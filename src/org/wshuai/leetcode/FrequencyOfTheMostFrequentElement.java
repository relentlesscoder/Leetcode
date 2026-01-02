package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/31/2023.
 * #1838 https://leetcode.com/problems/frequency-of-the-most-frequent-element/
 */
public class FrequencyOfTheMostFrequentElement {

    // time O(n * log(n)), space O(1)
    public int maxFrequency(int[] nums, int k) {
        // 一图流: https://leetcode.cn/problems/frequency-of-the-most-frequent-element/solutions/742562/pai-xu-qian-zhui-he-er-fen-zuo-duan-dian-xegv/
        // 给数组排序，需要对每个位置的元素 nums[i] 计算可以把多少个其左边
        // 的元素变为相同值。从上图中可以看出如果前面的窗口无法向左继续延伸
        // 则后面的窗口也不行 - 因为随着每次加入一个更大的数前一个窗口的所有
        // 数都需要更大的增量来变成它所以左端点只能保持不变或者右移，即左端点
        // 和右端点的移动是单调的 - 这意味着可以用滑动窗口来计算。
        int res = 0, n = nums.length;
        long sum = 0;
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < n; i++) {
            sum += nums[i];
            while (sum + k < (long) nums[i] * (i - j + 1)) {
                sum -= nums[j++];
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}

package org.wshuai.leetcode.heap;

import java.util.Arrays;

/**
 * Created by Wei on 01/01/2026.
 * #3422 https://leetcode.com/problems/minimum-operations-to-make-subarray-elements-equal/
 */
public class MinimumOperationsToMakeSubarrayElementsEqual {

    // time O(n * log(m) * log(m)), space O(n)
    public long minOperations(int[] nums, int k) {
        // 这题实际上是求把每个定长滑动窗口长度为 k 的子数组内所有元素转化为相同值
        // 的最小操作和。因为数组不是有序的我们不能用 #2968 同样的方法计算这题。
        // 通过 #2968 的分析我们知道要把一个子数组的数专为相同数最小操作是将所有元
        // 素转化为它们的中位数然后分别计算把中位数左右的元素转化为它的操作和，所以
        // 我们需要一个高效的数据结构告诉我们滑动窗口中的中位数是什么并且小于等于它和
        // 大于它的元素分别有对少个。我们可以用树状数组，维护两个树状数组分别存滑动窗
        // 口中的元素数量和元素和。对每个窗口，用二分找到中位数 (前缀数量和大于等于
        // (k + 1) / 2) 在树状数组中的前缀数量以及前缀和然后用 #2968 中同样的方法
        // 计算操作数和。
        long res = Long.MAX_VALUE, windowSum = 0;
        // 对数组中的值做离散化处理
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray(); // O(n * log(n))
        int n = nums.length, m = sorted.length, t = (k + 1) / 2;
        BIT bit = new BIT(m);
        for (int i = 0; i < n; i++) { // O(n)
            windowSum += nums[i]; // 把元素加入窗口
            // 在树状数组中加入元素
            bit.update(binarySearch(sorted, nums[i]) + 1, 1, nums[i]); // O(log(m))
            int head = i - k + 1;
            if (head < 0) {
                continue;
            }
            // 二分找中位数的索引
            int idx = bit.search(t); // O(log(m) * log(m))
            long target = (long) sorted[idx - 1];
            int cnt = bit.getCount(idx); // O(log(m))
            long sum = bit.getSum(idx); // O(log(m))
            // 计算操作数和
            long cost = target * cnt - sum;
            cost += (windowSum - sum) - target * (k - cnt);
            res = Math.min(res, cost);
            // 从树状数组中移除元素
            bit.update(binarySearch(sorted, nums[head]) + 1, -1, -nums[head]); // O(log(m))
            windowSum -= nums[head]; // 从窗口中移除元素
        }
        return res;
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static class BIT {

        private final int[] count;
        private final long[] sum;

        public BIT(int n) {
            count = new int[n + 1];
            sum = new long[n + 1];
        }

        public void update(int index, int cnt, int val) {
            while (index < count.length) {
                count[index] += cnt;
                sum[index] += val;
                index += index & -index;
            }
        }

        public int getCount(int index) {
            int res = 0;
            while (index > 0) {
                res += count[index];
                index -= index & -index;
            }
            return res;
        }

        public int search(int cnt) {
            int low = 1, high = count.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (getCount(mid) < cnt) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        public long getSum(int index) {
            long res = 0;
            while (index > 0) {
                res += sum[index];
                index -= index & -index;
            }
            return res;
        }
    }
}

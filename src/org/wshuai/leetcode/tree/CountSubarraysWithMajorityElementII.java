package org.wshuai.leetcode.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/29/2025.
 * #3739 https://leetcode.com/problems/count-subarrays-with-majority-element-ii/
 */
public class CountSubarraysWithMajorityElementII {

    // time O(n), space O(n)
    public long countMajoritySubarraysPrefixSumArray(int[] nums, int target) {
        // 方法同下解但是使用数组代替哈希表来优化。因为 sum 的值域范围在 [-n, n] 所以
        // 通过把每个值加 n 映射到值域范围 [0, 2 * n] 中这样我们需要一个长度为
        // 2 * n + 1 的数组。
        long res = 0;
        int n = nums.length;
        int[] freq = new int[(n << 1) + 1];
        freq[n]++;
        for (int i = 0, sum = 0, count = 0; i < n; i++) {
            if (nums[i] == target) {
                count += freq[sum + n];
                sum++;
            } else {
                sum--;
                count -= freq[sum + n];
            }
            res += count;
            freq[sum + n]++;
        }
        return res;
    }

    // time O(n), space O(n)
    public long countMajoritySubarraysPrefixSumHashMap(int[] nums, int target) {
        // 根据下解转化后，可以观察到前缀和是每次以 1 为变化量来变化的。用 f[i] 表示索引 i
        // 左边小于前缀和 s[i] 的前缀和个数则我们可以得出两种情况:
        //   1. s[i + 1] = s[i] + 1: f[i + 1] = f[i] + count[s[i]] - 比如s[i] = 1
        //      则 f[i] 表示 i 左边小与 1 的前缀和数量。由于 s[i + 1] = s[i] + 1 = 2
        //      所以 f[i + 1] 表示 i + 1 左边小与 2 的前缀和数量所以在 f[i] 的基础上加上
        //      s[i] = 1 的前缀和数量即可。
        //   2. s[i + 1] = s[i] - 1: f[i + 1] = f[i] - count[s[i]] - 比如s[i] = 1
        //      则 f[i] 表示 i 左边小与 1 的前缀和数量。由于 s[i + 1] = s[i] - 1 = 0
        //      所以 f[i + 1] 表示 i + 1 左边小与 0 的前缀和数量所以在 f[i] 的基础上减去
        //      s[i] = 0 的前缀和数量即可。
        // 示例1:
        // nums = [ 1,2,2,3, 3,2,2,2,4,5], target = 2
        // s:   [0,-1,0,1,0,-1,0,1,2,1,0]
        // f:     [ 0,1,3,1, 0,2,6,8,6,2]
        long res = 0;
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);
        for (int i = 0, sum = 0, count = 0; i < n; i++) {
            if (nums[i] == target) {
                count += freq.getOrDefault(sum, 0);
                sum++;
            } else {
                sum--;
                count -= freq.getOrDefault(sum, 0);
            }
            res += count;
            freq.merge(sum, 1, Integer::sum);
        }
        return res;
    }

    // time O(n * log(m)), space O(n + m)
    public long countMajoritySubarraysBIT1(int[] nums, int target) {
        // 简化下解中的公式，将前缀和变为统计数组中等于和不等于目标数数量的差:
        //   sum = (nums[i] == target) ? 1 : -1
        // 则子数组中目标数是主要元素需满足:
        //   sum > 0
        // 将 sum 用前缀和之差表示则上式可以转化为:
        //   s[r] - s[l] > 0 and r > l
        // 要求变为找到前缀和 s[r] 左边小于它的数的个数 s[l]:
        //   s[r] > s[l]
        long res = 0;
        int n = nums.length;
        int[] vals = new int[n + 1];
        for (int i = 0, sum = 0; i < n; i++) {
            sum += (nums[i] == target ? 1 : -1);
            vals[i + 1] = sum;
        }
        int[] sorted = Arrays.stream(vals).distinct().sorted().toArray();
        int m = sorted.length;
        BIT bit = new BIT(m);
        bit.update(binarySearch(sorted, 0) + 1);
        for (int i = 0; i < n; i++) {
            int v = vals[i + 1];
            res += bit.query(binarySearch(sorted, v));
            bit.update(binarySearch(sorted, v) + 1);
        }
        return res;
    }

    // time O(n * log(m)), space O(n + m)
    public long countMajoritySubarraysBIT2(int[] nums, int target) {
        // 根据题意，子数组中主要元素的个数 cnt 必须满足:
        //   cnt > (r - l) / 2
        // 计算数组中等于目标元素的个数的前缀和，用前缀和之差来表示cnt则上式
        // 可以转化为:
        //   s[r] - s[l] > (r - l) / 2 and r > l
        //   2 * s[r] - 2 * s[l] > r - l
        //   2 * s[r] - r > 2 * s[l] - l
        // 遍历数组，对每个点可以计算等式左边部分。我们需要一个高效的数据结构
        // 能查询索引 r 之前有多少个索引 l 满足等式
        //   2 * s[r] - r > 2 * s[l] - l
        // 可以用树状数组 + 离散化来实现这个数据结构。
        long res = 0;
        int n = nums.length;
        int[] vals = new int[n + 1];
        vals[0] = 1;
        for (int i = 0, cnt = 0; i < n; i++) {
            cnt += (nums[i] == target ? 1 : 0);
            vals[i + 1] = 2 * cnt - i;
        }
        int[] sorted = Arrays.stream(vals).distinct().sorted().toArray();
        int m = sorted.length;
        BIT bit = new BIT(m);
        bit.update(binarySearch(sorted, 1) + 1);
        for (int i = 0; i < n; i++) {
            int v = vals[i + 1];
            // 注意 v -> i 在树状数组中原本对应的索引是 i + 1，这里在树状数组
            // 中查询 i 来实现找到所有小于 v 的数。如果需要找小于等于则需要加一。
            res += bit.query(binarySearch(sorted, v));
            bit.update(binarySearch(sorted, v) + 1);
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

        private final int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index) {
            while (index < tree.length) {
                tree[index]++;
                index += index & -index;
            }
        }

        public int query(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }
    }
}

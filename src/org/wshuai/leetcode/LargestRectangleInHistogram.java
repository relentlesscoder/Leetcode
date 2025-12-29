package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 10/04/2016.
 * #0084 https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleInHistogram {

    // time O(n), space O(n)
    public int largestRectangleArea(int[] heights) {
		// https://leetcode.cn/problems/largest-rectangle-in-histogram/solutions/2695467/dan-diao-zhan-fu-ti-dan-pythonjavacgojsr-89s7/
        int res = 0, n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // Left sentinel -1
        for (int right = 0; right <= n; right++) {
            int h = right < n ? heights[right] : -1; // Right sentinel n
            // 从下面第二种解法我们可以得到栈顶元素的右边第一个较小的值的索引，而因为单调栈
            // 是单调递减的，在当前栈顶出栈后，新得栈顶就是左边第一个较小的值的索引.
            while (stack.size() > 1 && heights[stack.peek()] >= h) {
                int curr = stack.pop();
                int left = stack.peek();
                res = Math.max(res, heights[curr] * (right - left - 1));
            }
            stack.push(right);
        }
        return res;
    }

    // time O(n), space O(n)
    public int largestRectangleAreaMonotonicStackOnePass(int[] heights) {
        int res = 0, n = heights.length;
        int[] left = new int[n], right = new int[n];
        Arrays.fill(right, n); // Right sentinel n
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // Left sentinel -1
        for (int i = 0; i < n; i++) {
            // 在从左往右遍历中，利用heights[stack.peek()] >= heights[i]可以直接找到当前
            // 栈顶位置的右边第一个比他小的位置。但是这样找到的并非一定是准确的，这个值也可能与
            // 栈顶相等。
            //   e.g. [1,3,4,3,2]
            // 比如上面例子中对索引1（值为3）这样计算会得到所以3（值为3）这个是不对的，正确的索
            // 引应该是4（值为2）。但这并不会影响最终结果，当我们在处理索引3时依然会得到正确的结
            // 果。
            while (stack.size() > 1 && heights[stack.peek()] >= heights[i]) {
                right[stack.pop()] = i;
            }
            left[i] = stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        }
        return res;
    }

    // time O(n), space O(n)
    public int largestRectangleAreaMonotonicStackTwoPass(int[] heights) {
        // 对数组中的每个高度heights[i]来说，想以之为高度组成矩形的所有高度都不能比他更小。
        // 所以需要分别找到左右两边第一个比他小的高度，假设他们的位置分别为l和r。那么这个矩
        // 形的面积就是：
        //   heights[i] x (r - l + 1)
        // 从左到右遍历数组中的每个高度，维护一个单调栈来找到在当前位置之前一个比他小的位置l。
        // 用同样的方法找到r。代入上面的公式计算每个矩形的面积，答案即为所有面积的最大值。
        int res = 0, n = heights.length;
        int[] left = new int[n], right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // Left sentinel
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.peek();
            stack.push(i);
        }
        stack.clear();
        stack.push(n); // Right sentinel
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 1 && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        }
        return res;
    }
}

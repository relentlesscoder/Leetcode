package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 10/30/2019.
 * #0901 https://leetcode.com/problems/online-stock-span/
 */
public class OnlineStockSpan {
    private static class StockSpanner {

        private final List<Integer> prices;
        private final Deque<Integer> stack;

        public StockSpanner() {
            prices = new ArrayList<>();
            stack = new ArrayDeque<>();
            stack.push(-1);
        }

        public int next(int price) {
            prices.add(price);
            int idx = prices.size() - 1;
            while (stack.size() > 1 && prices.get(stack.peek()) <= price) {
                stack.pop();
            }
            int top = stack.peek();
            stack.push(idx);
            return idx - top;
        }
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */

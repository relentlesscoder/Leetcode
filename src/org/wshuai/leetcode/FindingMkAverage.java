package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeSet;

/**
 * Created by Wei on 12/26/2023.
 * #1825 https://leetcode.com/problems/finding-mk-average/
 */
public class FindingMkAverage {

    // time O(m * log(m)), space O(m)
    private class MKAverage {

        private int m, k, middleSize, id, sum;
        private Deque<Node> queue;
        private TreeSet<Node> left, right, middle;

        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
            this.middleSize = m - 2 * k;
            this.id = 0;
            this.sum = 0;
            queue = new ArrayDeque<>();
            left = new TreeSet<>((a, b) -> a.num == b.num ? a.id - b.id : a.num - b.num);
            right = new TreeSet<>((a, b) -> a.num == b.num ? a.id - b.id : a.num - b.num);
            middle = new TreeSet<>((a, b) -> a.num == b.num ? a.id - b.id : a.num - b.num);
        }

        // time O(log(m))
        public void addElement(int num) {
            Node node = new Node(num, id++);
            if (queue.size() >= m) {
                remove(queue.poll());
            }
            add(node);
            queue.offer(node);
        }

        // time O(1)
        public int calculateMKAverage() {
            if (queue.size() < m) {
                return -1;
            }
            return sum / middleSize;
        }

        private void remove(Node curr) {
            if (!left.isEmpty() && left.last().num >= curr.num) {
                left.remove(left.floor(curr));
            } else if (!middle.isEmpty() && middle.last().num >= curr.num) {
                Node toRemove = middle.floor(curr);
                sum -= toRemove.num;
                middle.remove(toRemove);
            } else {
                Node toRemove = right.floor(curr);
                right.remove(toRemove);
            }

            if (left.size() < k) {
                Node toMove = middle.first();
                left.add(toMove);
                sum -= toMove.num;
                middle.remove(toMove);
            }

            if (middle.size() < middleSize) {
                Node toMove = right.first();
                middle.add(toMove);
                sum += toMove.num;
                right.remove(toMove);
            }
        }

        private void add(Node curr) {
            left.add(curr);

            if (left.size() > k) {
                Node toMove = left.last();
                middle.add(toMove);
                sum += toMove.num;
                left.remove(toMove);
            }

            if (middle.size() > middleSize) {
                Node toMove = middle.last();
                right.add(toMove);
                sum -= toMove.num;
                middle.remove(toMove);
            }
        }

        private class Node{

            private int num;

            private int id;

            private Node(int num, int id) {
                this.num = num;
                this.id = id;
            }
        }
    }

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */
}

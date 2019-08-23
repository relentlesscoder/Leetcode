package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.BaseballGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BaseballGameTest {
    @Test
    public void testcase(){
        BaseballGame bg = new BaseballGame();
        int sum = bg.calPoints(new String[]{"5","-2","4","C","D","9","+","+"});
    }

    @Test
    public void testcase1(){

        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);
        lst.add(4);
        lst.add(5);

        LinkedList<Integer> queue = new LinkedList<>(lst);

        // 1
        int first = queue.getFirst();

        // 5
        int last = queue.getLast();

        // 4
        int val1 = queue.get(3);

        // add to the last (tail)
        queue.add(7);

        // add to the first (head)
        queue.addFirst(8);

        // add to the last
        queue.addLast(9);

        // add to the last
        queue.offer(10);

        // add to the first
        queue.offerFirst(11);

        // add to the last
        queue.offerLast(12);

        // return first
        int val2 = queue.peek();

        // return first
        int val3 = queue.peekFirst();

        //return last
        int val4 = queue.peekLast();

        // return and remove first
        int val5 = queue.poll();

        // return and remove first
        int val6 = queue.pollFirst();

        //return and remove last
        int val7 = queue.pollLast();

        // push to the first
        queue.push(13);

        // return and remove first
        int val8 = queue.pop();

        // remove first
        queue.remove();

        // remove first
        queue.removeFirst();

        // remove first
        queue.removeLast();

    }
}

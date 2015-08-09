package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.AddTwoNumbers;
import org.wshuai.leetcode.ListNode;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/9/15.
 */
public class AddTwoNumbersTest {
    @Test
    public void addTwoNumbersShouldReturnSumOfTheTwo(){
        ListNode num1 = new ListNode(2);
        ListNode root1 = num1;
        num1.next = new ListNode(4);
        num1 = num1.next;
        num1.next = new ListNode(3);

        ListNode num2 = new ListNode(5);
        ListNode root2 = num2;
        num2.next = new ListNode(6);
        num2 = num2.next;
        num2.next = new ListNode(4);

        ListNode result = AddTwoNumbers.addTwoNumbers(root1, root2);
        assertEquals(result.val, 7);
        result = result.next;
        assertEquals(result.val, 0);
        result = result.next;
        assertEquals(result.val, 8);
    }

    @Test
    public void addTwoNumbersWithDiffLengthShouldReturnSumOfTheTwo(){
        ListNode num1 = new ListNode(1);
        ListNode root1 = num1;
        num1.next = new ListNode(8);

        ListNode num2 = new ListNode(0);
        ListNode root2 = num2;

        ListNode result = AddTwoNumbers.addTwoNumbers(root1, root2);
        assertEquals(result.val, 1);
        result = result.next;
        assertEquals(result.val, 8);
    }

    @Test
    public void addTwoZerosShouldReturnZero(){
        ListNode num1 = new ListNode(0);
        ListNode root1 = num1;

        ListNode num2 = new ListNode(0);
        ListNode root2 = num2;

        ListNode result = AddTwoNumbers.addTwoNumbers(root1, root2);
        assertEquals(result.val, 0);
        result = result.next;
        assertEquals(result, null);
    }
}

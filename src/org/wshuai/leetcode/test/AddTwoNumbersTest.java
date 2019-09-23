package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.AddTwoNumbers;
import org.wshuai.leetcode.LinkedListNode;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/9/15.
 */
public class AddTwoNumbersTest {
	@Test
	public void addTwoNumbersShouldReturnSumOfTheTwo() {
		LinkedListNode num1 = new LinkedListNode(2);
		LinkedListNode root1 = num1;
		num1.next = new LinkedListNode(4);
		num1 = num1.next;
		num1.next = new LinkedListNode(3);

		LinkedListNode num2 = new LinkedListNode(5);
		LinkedListNode root2 = num2;
		num2.next = new LinkedListNode(6);
		num2 = num2.next;
		num2.next = new LinkedListNode(4);

		LinkedListNode result = AddTwoNumbers.addTwoNumbers(root1, root2);
		assertEquals(7, result.val);
		result = result.next;
		assertEquals(0, result.val);
		result = result.next;
		assertEquals(8, result.val);
	}

	@Test
	public void addTwoNumbersWithDiffLengthShouldReturnSumOfTheTwo() {
		LinkedListNode num1 = new LinkedListNode(1);
		LinkedListNode root1 = num1;
		num1.next = new LinkedListNode(8);

		LinkedListNode num2 = new LinkedListNode(0);
		LinkedListNode root2 = num2;

		LinkedListNode result = AddTwoNumbers.addTwoNumbers(root1, root2);
		assertEquals(1, result.val);
		result = result.next;
		assertEquals(8, result.val);
	}

	@Test
	public void addTwoZerosShouldReturnZero() {
		LinkedListNode num1 = new LinkedListNode(0);
		LinkedListNode root1 = num1;

		LinkedListNode num2 = new LinkedListNode(0);
		LinkedListNode root2 = num2;

		LinkedListNode result = AddTwoNumbers.addTwoNumbers(root1, root2);
		assertEquals(0, result.val);
		result = result.next;
		assertEquals(null, result);
	}
}

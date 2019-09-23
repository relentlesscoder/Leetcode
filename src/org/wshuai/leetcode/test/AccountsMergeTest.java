package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.AccountsMerge;

import java.util.ArrayList;
import java.util.List;

public class AccountsMergeTest {
	@Test
	public void testcase() {
		AccountsMerge am = new AccountsMerge();
		List<List<String>> input = new ArrayList<>();
		List<String> first = new ArrayList<>();
		first.add("John");
		first.add("johnsmith@mail.com");
		first.add("john00@mail.com");
		List<String> second = new ArrayList<>();
		second.add("John");
		second.add("johnnybravo@mail.com");
		List<String> third = new ArrayList<>();
		third.add("John");
		third.add("johnsmith@mail.com");
		third.add("john_newyork@mail.com");
		List<String> fourth = new ArrayList<>();
		fourth.add("Mary");
		fourth.add("mary@mail.com");
		input.add(first);
		input.add(second);
		input.add(third);
		input.add(fourth);
		List<List<String>> output = am.accountsMerge(input);
	}
}

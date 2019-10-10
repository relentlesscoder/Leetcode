package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.NaryTreeNode;
import org.wshuai.leetcode.SerializeAndDeserializeNaryTree;

import java.util.ArrayList;

public class SerializeAndDeserializeNaryTreeTest {
	@Test
	public void testcase1() {
		SerializeAndDeserializeNaryTree sd = new SerializeAndDeserializeNaryTree();
		NaryTreeNode root = new NaryTreeNode(1, new ArrayList<>());
		NaryTreeNode left = new NaryTreeNode(3, new ArrayList<>());
		root.children.add(left);
		root.children.add(new NaryTreeNode(2, new ArrayList<>()));
		root.children.add(new NaryTreeNode(4, new ArrayList<>()));
		left.children.add(new NaryTreeNode(5, new ArrayList<>()));
		left.children.add(new NaryTreeNode(6, new ArrayList<>()));
		String res = sd.serialize(root);
	}

	@Test
	public void testcase2() {
		SerializeAndDeserializeNaryTree sd = new SerializeAndDeserializeNaryTree();
		NaryTreeNode root = new NaryTreeNode(1, new ArrayList<>());
		NaryTreeNode left = new NaryTreeNode(10, new ArrayList<>());
		NaryTreeNode right = new NaryTreeNode(3, new ArrayList<>());
		root.children.add(left);
		root.children.add(right);
		left.children.add(new NaryTreeNode(5, new ArrayList<>()));
		left.children.add(new NaryTreeNode(0, new ArrayList<>()));
		right.children.add(new NaryTreeNode(6, new ArrayList<>()));
		String res = sd.serialize(root);
		NaryTreeNode node = sd.deserialize(res);
	}
}

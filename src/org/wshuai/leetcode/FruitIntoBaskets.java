package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 10/9/2019.
 * #904 https://leetcode.com/problems/fruit-into-baskets/
 */
public class FruitIntoBaskets {
	public int totalFruit(int[] tree) {
		int max = 0;
		LinkedList<Integer> queue = new LinkedList<>();
		LinkedList<Integer> next = new LinkedList<>();
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < tree.length; i++){
			if(set.size() == 2 && !set.contains(tree[i])){
				next = new LinkedList<>();
				max = Math.max(queue.size(), max);
				int tail = queue.peekLast();
				while(queue.peekLast() == tail){
					next.offerLast(queue.pollLast());
				}
				set.remove(queue.peekLast());
				queue = next;
			}
			queue.offerLast(tree[i]);
			set.add(tree[i]);
		}
		max = Math.max(queue.size(), max);
		return max;
	}
}

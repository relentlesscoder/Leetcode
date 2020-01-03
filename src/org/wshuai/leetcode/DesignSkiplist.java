package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Wei on 12/14/2019.
 * #1206 https://leetcode.com/problems/design-skiplist/
 */
public class DesignSkiplist {
	private static final double DEFAULT_PROB = 0.5;
	private final Random rand = new Random();
	private final List<SkiplistNode> sentinels = new ArrayList<>();

	// https://leetcode.com/problems/design-skiplist/discuss/393499/Java-Solution-beats-100
	public DesignSkiplist() {
		sentinels.add(new SkiplistNode(Integer.MIN_VALUE));
	}

	public boolean search(int target) {
		SkiplistNode se = find(target);
		return se.val == target;
	}

	public void add(int num) {
		SkiplistNode cur = find(num);
		SkiplistNode toInsert = new SkiplistNode(num);
		append(cur, toInsert);
		populateLevelUp(toInsert);
	}

	public boolean erase(int num) {
		SkiplistNode toRemove = find(num);
		if(toRemove.val != num){
			return false;
		}
		SkiplistNode cur = toRemove;
		while(cur != null){
			SkiplistNode prev = cur.left;
			SkiplistNode next = cur.right;
			prev.right = next;
			if(next != null){
				next.left = prev;
			}
			cur = cur.up;
		}
		return true;
	}

	private void populateLevelUp(SkiplistNode toInsert){
		SkiplistNode curPrev = toInsert.left;
		SkiplistNode cur = toInsert;

		while(flipCoin()){
			while(curPrev.left != null && curPrev.up == null){
				curPrev = curPrev.left;
			}
			if(curPrev.up == null){
				SkiplistNode newSentinel = new SkiplistNode(Integer.MIN_VALUE);
				curPrev.up = newSentinel;
				newSentinel.down = curPrev;
				sentinels.add(curPrev.up);
			}
			curPrev = curPrev.up;
			SkiplistNode newNode = new SkiplistNode(toInsert.val);
			cur.up = newNode;
			newNode.down = cur;
			cur = cur.up;
			curPrev.right = cur;
			cur.left = curPrev;
		}
	}

	private void append(SkiplistNode prev, SkiplistNode cur){
		SkiplistNode next = prev.right;
		prev.right = cur;
		cur.left = prev;
		if(next != null){
			next.left = cur;
			cur.right = next;
		}
	}

	private SkiplistNode find(int target){
		SkiplistNode cur = getSentinel();
		while(cur != null){
			if(cur.right == null || cur.right.val > target){
				if(cur.down == null){
					break;
				}
				cur = cur.down;
			}else{
				cur = cur.right;
			}
		}
		return cur;
	}

	private SkiplistNode getSentinel(){
		return sentinels.get(sentinels.size() - 1);
	}

	private boolean flipCoin() {
		return rand.nextDouble() < DEFAULT_PROB;
	}
}

class SkiplistNode{
	int val;
	SkiplistNode left, right, up, down;

	public SkiplistNode(int val){
		this.val = val;
	}
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */

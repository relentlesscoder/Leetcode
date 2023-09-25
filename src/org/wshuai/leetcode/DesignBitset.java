package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2023.
 * #2166 https://leetcode.com/problems/design-bitset/
 */
public class DesignBitset {

	private StringBuilder current = new StringBuilder(), flipped = new StringBuilder();
	private int size = 0, ones = 0;

	public DesignBitset(int size) {
		this.size = size;
		for (int i = 0; i < size; i++) {
			current.append('0');
			flipped.append('1');
		}
	}

	public void fix(int idx) {
		if (current.charAt(idx) == '1') {
			return;
		}
		ones++;
		current.setCharAt(idx, '1');
		flipped.setCharAt(idx, '0');
	}

	public void unfix(int idx) {
		if (current.charAt(idx) == '0') {
			return;
		}
		ones--;
		current.setCharAt(idx, '0');
		flipped.setCharAt(idx, '1');
	}

	public void flip() {
		ones = size - ones;
		StringBuilder temp = current;
		current = flipped;
		flipped = temp;
	}

	public boolean all() {
		return ones == size;
	}

	public boolean one() {
		return ones > 0;
	}

	public int count() {
		return ones;
	}

	public String toString() {
		return current.toString();
	}
}

/**
 * Your Bitset object will be instantiated and called as such:
 * Bitset obj = new Bitset(size);
 * obj.fix(idx);
 * obj.unfix(idx);
 * obj.flip();
 * boolean param_4 = obj.all();
 * boolean param_5 = obj.one();
 * int param_6 = obj.count();
 * String param_7 = obj.toString();
 */

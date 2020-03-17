package org.wshuai.leetcode;

/**
 * Created by Wei on 07/29/2017.
 * #0604 https://leetcode.com/problems/design-compressed-string-iterator/
 */
public class DesignCompressedStringIterator {
	private String data;
	private int count, index;
	private char cur;

	public DesignCompressedStringIterator(String compressedString) {
		data = compressedString;
		count = 0;
		index = 0;
		cur = ' ';
	}

	public char next() {
		if(!hasNext()){
			return ' ';
		}
		if(count == 0){
			cur = data.charAt(index++);
			int i = index;
			while(i < data.length() && Character.isDigit(data.charAt(i))){
				i++;
			}
			count = Integer.parseInt(data.substring(index, i));
			index = i;
		}
		count--;
		return cur;
	}

	public boolean hasNext() {
		return count > 0 || index < data.length();
	}
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

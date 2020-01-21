package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2016.
 * #0157 https://leetcode.com/problems/read-n-characters-given-read4/
 */
public class ReadNCharactersGivenRead4 {
	/**
	 * @param buf Destination buffer
	 * @param n   Maximum number of characters to read
	 * @return The number of characters read
	 */
	// time O(n)
	public int read(char[] buf, int n) {
		int total = 0;
		boolean eof = false;
		char[] temp = new char[4];
		while(!eof && total < n){
			int chars = read4(temp);
			eof = chars < 4;

			chars = Math.min(n - total, chars);
			for(int i = 0; i < chars; i++){
				buf[total++] = temp[i];
			}
		}
		return total;
	}

	// Fake implementation
	public int read4(char[] buf) {
		return 0;
	}
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2016.
 * #0157 https://leetcode.com/problems/read-n-characters-given-read4/
 */
public class ReadNCharactersGivenRead4 {

	private char[] prevBuf = new char[4];
	private int prevSize = 0;
	private int prevIndex = 0;

	/**
	 * @param buf Destination buffer
	 * @param n   Maximum number of characters to read
	 * @return The number of characters read
	 */
	// time O(n)
	public int read(char[] buf, int n) {
		int counter = 0;
		while (counter < n) {
			if (prevIndex < prevSize) {
				buf[counter++] = prevBuf[prevIndex++];
			} else {
				prevSize = read4(prevBuf);
				prevIndex = 0;
				if (prevSize == 0) {
					// no more data to consume from stream
					break;
				}
			}
		}
		return counter;
	}

	// Fake implementation
	public int read4(char[] buf) {
		return 0;
	}
}

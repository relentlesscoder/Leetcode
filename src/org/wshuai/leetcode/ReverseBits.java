package org.wshuai.leetcode;

/**
 * Created by Wei on 01/21/2020.
 * #0190 https://leetcode.com/problems/reverse-bits/
 */
public class ReverseBits {
	// time O(1)
	public int reverseBits(int n) {
		/*
		Take 12345678 as an example.
		First step, interchange 1234 with 5678 -> 56781234
		Second step, interchange 56~~12~~ with ~~78~~34-> 78563412
		Last step, interchange 7~5~3~1~ with ~8~6~4~2 ->87654321
		 */
		int ret = n;
		ret = ret >>> 16 | ret << 16;
		ret = (ret & 0xff00ff00) >>> 8 | (ret & 0x00ff00ff) << 8;
		ret = (ret & 0xf0f0f0f0) >>> 4 | (ret & 0x0f0f0f0f) << 4;
		ret = (ret & 0xcccccccc) >>> 2 | (ret & 0x33333333) << 2;
		ret = (ret & 0xaaaaaaaa) >>> 1 | (ret & 0x55555555) << 1;
		return ret;
	}
}

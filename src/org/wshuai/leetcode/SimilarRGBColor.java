package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2019.
 * #0800 https://leetcode.com/problems/similar-rgb-color/
 */
public class SimilarRGBColor {

	private static final int[] values = new int[]{0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff};
	private static final String[] hexs = new String[]{"00", "11", "22", "33", "44", "55", "66", "77", "88", "99", "aa", "bb", "cc", "dd", "ee", "ff"};

	public String similarRGB(String color) {
		String s1 = color.substring(1, 3), s2 = color.substring(3, 5), s3 = color.substring(5);
		return "#" + replace(s1) + replace(s2) + replace(s3);
	}

	private String replace(String in){
		String res = "";
		int min = Integer.MAX_VALUE, val = Integer.parseInt(in, 16);
		for(int i = 0; i < 16; i++){
			int diff = Math.abs(val - values[i]);
			if(diff < min){
				min = diff;
				res = hexs[i];
			}
		}
		return res;
	}
}

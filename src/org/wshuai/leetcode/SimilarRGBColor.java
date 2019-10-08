package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/2019.
 * #800 https://leetcode.com/problems/similar-rgb-color/
 */
public class SimilarRGBColor {
	private String[] arr;

	public SimilarRGBColor(){
		arr = new String[]{"00","11","22","33","44","55","66","77","88","99","aa","bb","cc","dd","ee","ff"};
	}

	public String similarRGB(String color) {
		String s1 = color.substring(1, 3);
		String s2 = color.substring(3, 5);
		String s3 = color.substring(5);
		return "#" + replace(s1) + replace(s2) + replace(s3);
	}

	private String replace(String s){
		char f = s.charAt(0);
		int idx = getIndex(f);
		int low = f == '0' ? idx : idx - 1;
		int high = f == 'f' ? idx : idx + 1;
		int min = Integer.MAX_VALUE;
		String res = "";
		for(int i = low; i <= high; i++){
			int diff = Math.abs(Integer.parseInt(s, 16) - Integer.parseInt(arr[i], 16));
			if(diff < min){
				min = diff;
				res = arr[i];
			}
		}
		return res;
	}

	private int getIndex(int f){
		if(Character.isDigit(f)){
			return (int)(f - '0');
		}else{
			return (int)(f - 'a') + 10;
		}
	}
}

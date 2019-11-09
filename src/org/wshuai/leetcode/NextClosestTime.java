package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/9/2019.
 * #681 https://leetcode.com/problems/next-closest-time/
 */
public class NextClosestTime {
	public String nextClosestTime(String time) {
		String res = "";
		String[] arr = time.split(":");
		int h = Integer.parseInt(arr[0]);
		int m = Integer.parseInt(arr[1]);
		int target = h * 60 + m;
		Set<Integer> d1 = new HashSet<>();
		for(char c : time.toCharArray()){
			if(Character.isDigit(c)){
				d1.add(c - '0');
			}
		}
		int minDiff = 3_601;
		for(int f: d1){
			for(int s: d1){
				for(int t: d1){
					for(int k: d1){
						int hour = f * 10 + s;
						int min = t * 10 + k;
						if(hour >= 24 || min >= 60){
							continue;
						}
						int val = hour * 60 + min;
						int diff = val <= target ? val + 3_600 - target : val - target;
						if(diff < minDiff){
							minDiff = diff;
							res = (hour < 10 ? "0" : "") + hour + ":" + (min < 10 ? "0" : "") + min;
						}
					}
				}
			}
		}
		return res;
	}
}

package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/26/2019.
 * #1024 https://leetcode.com/problems/video-stitching/
 */
public class VideoStitching {
	// time O(n)
	// same as #1326
	public int videoStitching(int[][] clips, int T) {
		Arrays.sort(clips, (a, b) -> a[0] - b[0]);
		int count = 0;
		int curend = 0;

		for(int i = 0; i < clips.length; ) {
			if(clips[i][0] > curend) {
				return -1;
			}
			int maxend = curend;
			while(i < clips.length && clips[i][0] <= curend) { // while one clip's start is before or equal to current end
				maxend = Math.max(maxend, clips[i][1]); // find out the one with the max possible end
				i++;
			}
			count++;
			curend = maxend;
			if(curend >= T) {
				return count;
			}
		}
		return -1;
	}

}

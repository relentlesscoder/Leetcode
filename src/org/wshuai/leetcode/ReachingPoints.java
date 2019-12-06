package org.wshuai.leetcode;

/**
 * Created by Wei on 12/5/2019.
 * #780 https://leetcode.com/problems/reaching-points/
 */
public class ReachingPoints {
	// https://leetcode.com/problems/reaching-points/discuss/375429/Detailed-explanation.-or-full-through-process-or-Java-100-beat
	public boolean reachingPoints(int sx, int sy, int tx, int ty) {
		while(sx < tx && sy < ty){
			if(tx < ty){
				ty %= tx;
			}else{
				tx %= ty;
			}
		}

		if(sx == tx && sy <= ty && (ty - sy) % sx == 0){
			return true;
		}
		return sy == ty && sx <= tx && (tx - sx) % sy == 0;
	}
}

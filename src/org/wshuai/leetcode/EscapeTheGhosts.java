package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2019.
 * #0789 https://leetcode.com/problems/escape-the-ghosts/
 */
public class EscapeTheGhosts {

	// time O(n)
    // https://leetcode.com/problems/escape-the-ghosts/discuss/116678/Why-interception-in-the-middle-is-not-a-good-idea-for-ghosts.
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
	    int dist = Math.abs(target[0]) + Math.abs(target[1]);
	    for(int[] ghost : ghosts){
		    int cur = Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1]);
		    if(cur <= dist){
			    return false;
		    }
	    }
	    return true;
    }
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 08/13/2016.
 * #0011 https://leetcode.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {

    // time O(n), space O(1)
    public int maxArea(int[] height) {
		/**
		 这道题就像相亲一样，越早遇见对的人越好。当我遇到的人都比我自己差的时候，
		 我还是不满足，所以把差的抛弃了，直到遇到一个比自己更好的，那这个人一定
		 就是我所能遇到最好的了。因为自此之后遇到再好的人，他所超过我的部分对我
		 来说也没有价值，我自己还是那个短板。自此之后遇到同样的人，也没有和他的
		 时间一样多，而且还可能遇到更差的人。
		 */
        int res = 0, n = height.length;
        for (int i = 0, j = n - 1, left = 0, right = 0; i < j; ) {
            left = Math.max(left, height[i]);
            right = Math.max(right, height[j]);
            res = Math.max(res, Math.min(left, right) * (j - i));
            // Since the shorter side height decides the final area, move
            // the shorter side to create a better solution if possible.
            if (left < right) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}

package org.wshuai.leetcode;

/**
 * Created by Wei on 01/08/2024.
 * #1954 https://leetcode.com/problems/minimum-garden-perimeter-to-collect-enough-apples/
 */
public class MinimumGardenPerimeterToCollectEnoughApples {

    // time O(log(n)), space O(1)
    public long minimumPerimeter(long neededApples) {
        long sum = 0, i = 0;
        while (sum < neededApples) {
            i++;
            sum += i * i * 12;
        }
        return 8 * i;
    }
}

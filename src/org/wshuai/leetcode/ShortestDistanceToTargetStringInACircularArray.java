package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2025.
 * #2515 https://leetcode.com/problems/shortest-distance-to-target-string-in-a-circular-array/
 */
public class ShortestDistanceToTargetStringInACircularArray {

    // time O(n), space O(1)
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length, moveForward = 0, moveBackward = 0, i = startIndex;
        while (true) {
            int curr = i % n;
            // Back to the start index without finding the target
            if (i != startIndex && curr == startIndex) {
                return -1;
            }
            if (words[curr].equals(target)) {
                break;
            }
            i++;
            moveForward++;
        }
        i = startIndex;
        while (true) {
            int curr = (i + n) % n;
            if (words[curr].equals(target)) {
                break;
            }
            i--;
            moveBackward++;
        }
        return Math.min(moveForward, moveBackward);
    }
}

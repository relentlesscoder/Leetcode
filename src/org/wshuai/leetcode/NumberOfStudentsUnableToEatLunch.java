package org.wshuai.leetcode;

/**
 * Created by Wei on 12/28/2020.
 * #1700 https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
 */
public class NumberOfStudentsUnableToEatLunch {

    // time O(m + n)
    public int countStudents(int[] students, int[] sandwiches) {
        int s0 = 0, s1 = 0;
        for(int s : students){
            if(s == 0){
                s0++;
            }else{
                s1++;
            }
        }
        for(int s : sandwiches){// process until none of the remaining students likes the sandwich on top of the queue
            if(s == 0){
                if(s0 == 0){
                    break;
                }
                s0--;
            }
            if(s == 1){
                if(s1 == 0){
                    break;
                }
                s1--;
            }
        }
        return s0 + s1;
    }
}

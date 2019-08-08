package org.wshuai.leetcode;

/**
 * Created by Wei on 8/8/19.
 * #657 https://leetcode.com/problems/robot-return-to-origin/
 */
public class RobotReturnToOrigin {
    public boolean judgeCircle(String moves) {
        if(moves == null || moves.length() == 0){
            return true;
        }
        int x = 0;
        int y = 0;
        for(int i = 0; i < moves.length(); i++){
            char c = moves.charAt(i);
            if(c == 'R'){
                x++;
            }
            if(c == 'L'){
                x--;
            }
            if(c == 'U'){
                y++;
            }
            if(c == 'D'){
                y--;
            }
        }
        return x == 0 && y == 0;
    }
}

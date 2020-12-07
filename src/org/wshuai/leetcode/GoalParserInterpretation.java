package org.wshuai.leetcode;

/**
 * Created by Wei on 12/07/2020.
 * #1678 https://leetcode.com/problems/goal-parser-interpretation/
 */
public class GoalParserInterpretation {

    // time O(n)
    public String interpret(String command) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < command.length(); i++){
            char c = command.charAt(i);
            if(c == 'G'){
                res.append(c);
            }else if(c == '(' && command.charAt(i + 1) == ')'){
                res.append("o");
                i++;
            }else{
                res.append("al");
                i += 3;
            }
        }
        return res.toString();
    }
}

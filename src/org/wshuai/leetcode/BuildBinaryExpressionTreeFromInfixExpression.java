package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/28/2020.
 * #1597 https://leetcode.com/problems/build-binary-expression-tree-from-infix-expression/
 */
public class BuildBinaryExpressionTreeFromInfixExpression {

    private static final Map<Character, Integer> PRECEDENCE;

    static {
        PRECEDENCE = new HashMap<>();
        PRECEDENCE.put('(', -1);
        PRECEDENCE.put('+', 0);
        PRECEDENCE.put('-', 0);
        PRECEDENCE.put('*', 1);
        PRECEDENCE.put('/', 1);
    }

    // time O(n), space O(n)
    // same as #0772 https://leetcode.com/problems/basic-calculator-iii/
    public Node expTree(String s) {
        Deque<Character> operands = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();
        int n = s.length();
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(c >= '0' && c <= '9'){
                operands.push(c);
            }else if(c == '('){
                operators.push(c);
            }else if(c == ')'){
                while(!operators.isEmpty() && operators.peek() != '('){
                    operands.push(operators.pop());
                }
                operators.pop();
            }else{
                while(!operators.isEmpty() && comparePrecedence(c, operators.peek()) <= 0){
                    operands.push(operators.pop());
                }
                operators.push(c);
            }
        }
        while(!operators.isEmpty()){
            operands.push(operators.pop());
        }
        Stack<Node> stack = new Stack<>();
        while(!operands.isEmpty()){
            char c = operands.pollLast();
            if(c == '+' || c == '-' || c == '*' || c == '/'){
                Node right = stack.pop();
                Node left = stack.pop();
                stack.push(new Node(c, left, right));
            }else{
                stack.push(new Node(c));
            }
        }
        return stack.peek();
    }

    private int comparePrecedence(char a, char b) {
        return PRECEDENCE.get(a) - PRECEDENCE.get(b);
    }

    //Definition for a binary tree node.
    private class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}

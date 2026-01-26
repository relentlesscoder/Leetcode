package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/22/2020.
 * #1628 https://leetcode.com/problems/design-an-expression-tree-with-evaluate-function/
 */
public class DesignAnExpressionTreeWithEvaluateFunction {

    /**
     * This is the TreeBuilder class.
     * You can treat it as the driver code that takes the postinfix input
     * and returns the expression tree representing it as a Node.
     */
    private static class TreeBuilder {
        Node buildTree(String[] postfix) {
            int n = postfix.length;
            Deque<ExpressionTreeNode> stack = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (postfix[i].equals("+")
                        || postfix[i].equals("-")
                        || postfix[i].equals("*")
                        || postfix[i].equals("/")) {
                    ExpressionTreeNode node =
                            new ExpressionTreeNode(
                                    true,
                                    0,
                                    postfix[i].charAt(0));
                    node.right = stack.pop();
                    node.left = stack.pop();
                    stack.push(node);
                } else {
                    stack.push(
                            new ExpressionTreeNode(
                                    false,
                                    Integer.parseInt(postfix[i]),
                                    '#'));
                }
            }
            return stack.peek();
        }

        private static class ExpressionTreeNode extends Node {

            private ExpressionTreeNode left;
            private ExpressionTreeNode right;

            public ExpressionTreeNode(boolean isOperator, int value, char operator) {
                this.isOperator = isOperator;
                this.value = value;
                this.operator = operator;
                this.left = null;
                this.right = null;
            }

            @Override
            public int evaluate() {
                return evaluate(this);
            }

            private int evaluate(ExpressionTreeNode root) {
                if (!root.isOperator) {
                    return root.value;
                }
                return calc(evaluate(root.left), evaluate(root.right), root.operator);
            }

            private int calc(int v1, int v2, char operator) {
                int res = switch (operator) {
                    case '+' -> v1 + v2;
                    case '-' -> v1 - v2;
                    case '*' -> v1 * v2;
                    case '/' -> v1 / v2;
                    default -> throw new RuntimeException("Unsupported operator");
                };
                return res;
            }
        }
    }

    /**
     * This is the interface for the expression tree Node.
     * You should not remove it, and you can define some classes to implement it.
     */
    private abstract static class Node {
        public abstract int evaluate();

        // define your fields here
        public boolean isOperator;
        public int value;
        public char operator;
    }

/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */
}

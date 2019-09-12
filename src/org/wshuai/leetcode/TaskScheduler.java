package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by Wei on 9/12/2019.
 * #621 https://leetcode.com/problems/task-scheduler/
 */
public class TaskScheduler {

    public int leastIntervalSorting(char[] tasks, int n) {
        int[] count = new int[26];
        for(char c: tasks){
            count[c-'A']++;
        }
        Arrays.sort(count);
        int time = 0;
        while(count[25] > 0){
            int i = 0;
            while(i <= n){
                if(count[25] == 0){
                    break;
                }
                if(i < 26 && count[25 - i] > 0){
                    count[25 - i]--;
                }
                time++;
                i++;
            }
            Arrays.sort(count);
        }
        return time;
    }

    public int leastIntervalPriorityQueue(char[] tasks, int n) {
        if(n == 0){
            return tasks.length;
        }
        int[] count = new int[26];
        for(char t: tasks){
            count[t-'A']++;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(26, Collections.reverseOrder());
        for(int i = 0; i < 26; i++){
            if(count[i] > 0){
                queue.offer(count[i]);
            }
        }
        int res = 0;
        int c = n + 1;
        Stack<Integer> stack = new Stack<>();
        while(c > 0){
            if(!queue.isEmpty()){
                int tc = queue.poll();
                tc--;
                if(tc > 0){
                    stack.push(tc);
                }
                res++;
            }else{
                if(!stack.isEmpty()){
                    res++;
                }
            }
            c--;
            if(c == 0){
                while(!stack.isEmpty()){
                    queue.offer(stack.pop());
                }
                if(queue.isEmpty()){
                    break;
                }
                c = n+1;
            }
        }
        return res;
    }
}

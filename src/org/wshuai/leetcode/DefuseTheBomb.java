package org.wshuai.leetcode;

/**
 * Created by Wei on 12/07/2020.
 * #1652 https://leetcode.com/problems/defuse-the-bomb/
 */
public class DefuseTheBomb {

    // time O(n)
    public int[] decrypt(int[] code, int k) {
        int n = code.length, sum = 0, head, tail;
        int[] res = new int[n];
        if(k == 0){
            return res;
        }
        if(k > 0){
            head = 1 % n;
            tail = 0;
            for(int i = 0; i < k; i++){
                tail = (tail + 1) % n;
                sum += code[tail];
            }
        }else{
            tail = n - 1;
            head = 0;
            for(int i = 0; i < -k; i++){
                head = (n + head - 1) % n;
                sum += code[head];
            }
        }
        // sliding window
        for(int i = 0; i < n; i++){
            res[i] = sum;
            sum -= code[head];
            head = (head + 1) % n;
            tail = (tail + 1) % n;
            sum += code[tail];
        }
        return res;
    }
}

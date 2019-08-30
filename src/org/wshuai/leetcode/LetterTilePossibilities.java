package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 8/30/2019.
 * #1079 https://leetcode.com/problems/letter-tile-possibilities/
 */
public class LetterTilePossibilities {
    int count = 0;

    public int numTilePossibilities(String tiles) {
        char[] arr = tiles.toCharArray();
        Arrays.sort(arr);
        int[] used = new int[arr.length];
        dfs(arr, used);
        return count;
    }

    private void dfs(char[] tiles, int[] used){
        for(int i = 0; i < tiles.length; i++){
            // remove duplicates
            if(used[i] == 1 || (i > 0 && used[i-1] == 0 && tiles[i] == tiles[i-1])){
                continue;
            }
            used[i] = 1;
            count++;
            dfs(tiles, used);
            used[i] = 0;
        }
    }
}

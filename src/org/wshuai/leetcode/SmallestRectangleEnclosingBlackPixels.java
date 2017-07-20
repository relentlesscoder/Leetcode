package org.wshuai.leetcode;

/**
 * Created by Wei on 7/20/2017.
 * #302 https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/
 */
public class SmallestRectangleEnclosingBlackPixels {
  //DFS to find max/min x and y
  public int minArea(char[][] image, int x, int y) {
    if(image == null || image.length == 0 || image[0].length == 0){
      return 0;
    }
    int row = image.length;
    int col = image[0].length;
    int[] pos = new int[]{Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 0};
    int[] hm = new int[]{0, 0, 1, -1};
    int[] vm = new int[]{1, -1, 0, 0};
    minAreaUtil(image, x, y, pos, row, col, hm, vm);
    return (pos[1]-pos[0]+1)*(pos[3]-pos[2]+1);
  }

  private void minAreaUtil(char[][] image, int x, int y, int[] pos, int row, int col, int[] hm, int[] vm){
    if(x < 0 || x >= row || y < 0 || y >= col || image[x][y] == '0'){
      return;
    }
    image[x][y] = '0';
    pos[0] = Math.min(pos[0], x);
    pos[1] = Math.max(pos[1], x);
    pos[2] = Math.min(pos[2], y);
    pos[3] = Math.max(pos[3], y);
    for(int i = 0; i < 4; i++){
      minAreaUtil(image, x + hm[i], y + vm[i], pos, row, col, hm, vm);
    }
  }
}

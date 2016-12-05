package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 11/23/2016.
 * #353 https://leetcode.com/problems/design-snake-game/
 */
public class DesignSnakeGame {
  int width;
  int height;
  int[][] food;
  List<int[]> snake;
  int idx;
  Set<Integer> body;

  /** Initialize your data structure here.
   @param width - screen width
   @param height - screen height
   @param food - A list of food positions
   E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
  public DesignSnakeGame(int width, int height, int[][] food) {
    this.width = width;
    this.height = height;
    this.food = food;
    this.idx = 0;
    this.body = new HashSet<Integer>();
    snake = new ArrayList<int[]>();
    snake.add(new int[]{0,0});
    body.add(0);
  }

  /** Moves the snake.
   @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
   @return The game's score after the move. Return -1 if game over.
   Game over when snake crosses the screen boundary or bites its body. */
  public int move(String direction) {
    int[] head = snake.get(snake.size()-1);
    int[] nhead = new int[2];
    int x = head[0];
    int y = head[1];
    if(direction.equals("U")){
      x--;
    }else if(direction.equals("D")){
      x++;
    }else if(direction.equals("L")){
      y--;
    }else{
      y++;
    }
    //Collides with border
    if(x < 0 || x >= height || y < 0 || y >= width){
      return -1;
    }
    nhead[0] = x;
    nhead[1] = y;
    snake.add(nhead);
    //Eats food
    if(idx < food.length && food[idx][0] == x && food[idx][1] == y){
      idx++;
    }else{
      int[] tail = snake.get(0);
      snake.remove(0);
      body.remove(tail[0]*width+tail[1]);
      //Collides with its body
      if(body.contains(x*width+y)){
        return -1;
      }
    }
    body.add(x*width+y);
    return idx;
  }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */

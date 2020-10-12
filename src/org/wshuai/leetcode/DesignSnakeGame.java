package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/23/2016.
 * #0353 https://leetcode.com/problems/design-snake-game/
 */
public class DesignSnakeGame {

	private LinkedList<int[]> snake;
	private Set<Integer> body;
	private int width, height, index;
	private int[][] food;

	/** Initialize your data structure here.
	 @param width - screen width
	 @param height - screen height
	 @param food - A list of food positions
	 E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
	public DesignSnakeGame(int width, int height, int[][] food) {
		snake = new LinkedList<>();
		body = new HashSet<>();
		this.width = width;
		this.height = height;
		this.food = food;
		snake.offerLast(new int[]{0, 0});
		body.add(0);
	}

	/** Moves the snake.
	 @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
	 @return The game's score after the move. Return -1 if game over.
	 Game over when snake crosses the screen boundary or bites its body. */
	public int move(String direction) {
		int[] head = snake.peekLast(), next = new int[]{head[0], head[1]};
		char dir = direction.charAt(0);
		if(dir == 'U'){
			next[0] -= 1;
		}else if(dir == 'L'){
			next[1] -= 1;
		}else if(dir == 'R'){
			next[1] += 1;
		}else{
			next[0] += 1;
		}
		if(next[0] < 0 || next[0] >= height || next[1] < 0 || next[1] >= width){
			return -1;
		}
		int encode = next[0] * width + next[1];
		if(index < food.length && next[0] == food[index][0] && next[1] == food[index][1]){
			++index;
		}else{
			int[] tail = snake.pollFirst();
			body.remove(tail[0] * width + tail[1]);
			// detect body collision
			if(body.contains(encode)){
				return -1;
			}
		}
		snake.offerLast(new int[]{next[0], next[1]});
		body.add(encode);
		return index;
	}
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */

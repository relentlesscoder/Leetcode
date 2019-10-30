package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/29/19.
 * #855 https://leetcode.com/problems/exam-room/
 */
public class ExamRoom {
	// https://leetcode.com/problems/exam-room/discuss/148595/Java-PriorityQueue-with-customized-object.-seat%3A-O(logn)-leave-O(n)-with-explanation
	class StudentInterval{

		int x;
		int y;
		int dist;

		public StudentInterval(int x, int y){
			this.x = x;
			this.y = y;
			if(x == -1){
				this.dist = y;
			}else if(y == N){
				this.dist = N - 1 - x;
			}else{
				this.dist = Math.abs(x - y) / 2;
			}
		}
	}

	private PriorityQueue<StudentInterval> queue;
	private int N;

	public ExamRoom(int N) {
		this.queue = new PriorityQueue<>((a, b) -> a.dist != b.dist ? b.dist - a.dist : a.x - b.x);
		this.N = N;
		queue.offer(new StudentInterval(-1, N));
	}

	public int seat() {
		int seat = 0;
		StudentInterval interval = queue.poll();
		if(interval.x == -1){
			seat = 0;
		}else if(interval.y == N){
			seat = N - 1;
		}else{
			seat = (interval.x + interval.y) / 2;
		}

		queue.offer(new StudentInterval(interval.x, seat));
		queue.offer(new StudentInterval(seat, interval.y));

		return seat;
	}

	public void leave(int p) {
		StudentInterval head = null;
		StudentInterval tail = null;
		List<StudentInterval> intervals = new ArrayList<>(queue);
		for(StudentInterval interval : intervals){
			if(interval.x == p){
				tail = interval;
			}
			if(interval.y == p){
				head = interval;
			}
			if(head != null && tail != null){
				break;
			}
		}
		queue.remove(head);
		queue.remove(tail);

		queue.offer(new StudentInterval(head.x, tail.y));
	}
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */

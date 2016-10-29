package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/2016.
 * #252 https://leetcode.com/problems/meeting-rooms/
 */
public class MeetingRooms {
  public boolean canAttendMeetings(Interval[] intervals) {
    if(intervals == null){
      throw new IllegalArgumentException("Invalid input.");
    }
    int len = intervals.length;
    quickSortInterval(intervals, 0, len - 1);
    for(int i = 0; i < len - 1; i++){
      Interval curr = intervals[i];
      Interval next = intervals[i + 1];
      if(curr.end > next.start){
        return false;
      }
    }
    return true;
  }

  public void quickSortInterval(Interval[] ins, int p, int r){
    if(p < r){
      int q = partitionInterval(ins, p, r);
      quickSortInterval(ins, p, q - 1);
      quickSortInterval(ins, q + 1, r);
    }
  }

  public int partitionInterval(Interval[] ins, int p, int r){
    Interval in = ins[r];
    int j = p;
    for(int i = p; i < r; i++){
      Interval curr = ins[i];
      if(curr.start < in.start){
        Interval temp = ins[j];
        ins[j] = curr;
        ins[i] = temp;
        j++;
      }
    }
    ins[r] = ins[j];
    ins[j] = in;
    return j;
  }
}

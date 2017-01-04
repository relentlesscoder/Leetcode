package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/4/16.
 * #207 https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {
  //DFS
  public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
    if(numCourses <= 0 || prerequisites == null || prerequisites.length == 0){
      return true;
    }
    int len = prerequisites.length;
    List<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[numCourses];
    for(int i = 0; i < len; i++){
      int[] arr = prerequisites[i];
      int idx = arr[1];
      if(adj[idx] == null){
        adj[idx] = new ArrayList<Integer>();
      }
      adj[idx].add(arr[0]);
    }
    int[] aux = new int[numCourses];
    for(int i = 0; i < numCourses; i++){
      if(!canFinishUtil(i, adj, aux)){
        return false;
      }
    }
    return true;
  }

  private boolean canFinishUtil(int i, List<Integer>[] adj, int[] aux){
    if(aux[i] == -1){
      return false;
    }
    if(aux[i] == 1){
      return true;
    }
    aux[i] = -1;
    if(adj[i] != null){
      for(int j: adj[i]){
        if(!canFinishUtil(j, adj, aux)){
          return false;
        }
      }
    }
    aux[i] = 1;
    return true;
  }
}

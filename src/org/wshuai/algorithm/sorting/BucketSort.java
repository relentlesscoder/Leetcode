package org.wshuai.algorithm.sorting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Wei on 8/11/2015.
 */
public class BucketSort {
  public static void sort(Comparable[] array){
    if(array == null || array.length <= 0){
      return;
    }

    Map<Comparable, Integer> map = new HashMap<Comparable, Integer>();
    for(int i=0; i < array.length; i++){
      Integer count = map.get(array[i]);
      map.put(array[i], count == null ? 1 : ++count);
    }

    Iterator it = map.keySet().iterator();
    int index = 0;
    while (it.hasNext()){
      Comparable key = (Comparable)it.next();
      Integer count = map.get(key);
      while (count > 0){
        array[index] = key;
        index++;
        count--;
      }
    }
  }
}

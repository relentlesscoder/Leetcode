package org.wshuai.algorithm.sorting;

import org.wshuai.utility.ArrayUtil;

/**
 * Created by Wei on 8/10/15.
 */
public class SelectionSort {
    public static void sort(Comparable[] array){
        if(array == null || array.length <= 1){
            return;
        }

        for(int i=0; i < array.length; i++){
            int min = i;
            for(int j=i+1; j < array.length; j++){
                if(ArrayUtil.less(array[j], array[min])){
                    min = j;
                }
            }
            ArrayUtil.exchange(array, i, min);
        }
    }
}

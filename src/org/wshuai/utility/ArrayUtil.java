package org.wshuai.utility;

/**
 * Created by Wei on 8/10/15.
 */
public class ArrayUtil {
    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void exchange(Comparable[] array, int i, int j){
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static String show(Comparable[] array){
        if(array == null || array.length == 0){
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i < array.length; i++){
            buffer.append(array[i]);
            if(i != array.length - 1){
                buffer.append(",");
            }
        }
        return buffer.toString();
    }

    public static boolean isSorted(Comparable[] array){
        for(int i=1; i < array.length; i++){
            if(less(array[i], array[i-1])){
                return false;
            }
        }

        return true;
    }
}

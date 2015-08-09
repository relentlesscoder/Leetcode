import java.util.HashMap;

/**
 * Created by Wei on 8/9/15.
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target){
        int[] result = new int[]{};
        if(nums.length > 1){
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int i = 0; i < nums.length; i++){
                map.put(nums[i], i);
            }
            for(int i = 0; i < nums.length; i++){
                int diff = target - nums[i];
                if(map.containsKey(diff)){
                    int index1 = map.get(diff);
                    if(index1 < i){
                        result[0] = index1;
                        result[1] = i;
                    }
                    else{
                        result[0] = i;
                        result[1] = index1;
                    }
                }
            }
        }
        return result;
    }
}

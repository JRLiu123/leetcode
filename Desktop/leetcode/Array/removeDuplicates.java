package Array;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: removeDuplicates
 * Package: Array
 * Description:
 *
 * @Author Jingran Liu
 * @Create 8/12/2024 2:43 PM
 * @Version 1.0
 */
public class removeDuplicates {
    public int removeDuplicates(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for(int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], 0);
            if(map.get(nums[i]) < 2) {
                map.put(nums[i],map.get(nums[i]) + 1);
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}

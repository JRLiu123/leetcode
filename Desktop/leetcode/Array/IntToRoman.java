package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: IntToRoman
 * Package: Array
 * Description:
 *
 * @Author Jingran Liu
 * @Create 8/27/2024 2:56 PM
 * @Version 1.0
 */
public class IntToRoman {
    public static String intToRoman(int num) {

        List<Integer> nums = new ArrayList<>();
        while(num != 0) {
            nums.add((num%10));
            num /= 10;

        }

        Map<Integer, Character> map = new HashMap<>();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');

        StringBuilder sb = new StringBuilder();


        int len = nums.size();

        int factor = (int) Math.pow(10, len - 1);


        for(int i = nums.size()-1; i >= 0; i--) {
            if(nums.get(i) == 4) {

                sb.append(map.get(factor));
                sb.append(map.get(5*factor));
            }
            else if(nums.get(i) == 9) {
                sb.append(map.get(factor));
                sb.append(map.get(10*factor));
            }
            else if(nums.get(i) < 5){
                int j = nums.get(i);
                while(j > 0) {
                    sb.append(map.get(factor));
                    j -= 1;
                }
            }
            else {
                int j = nums.get(i);
                sb.append(map.get(5*factor));
                j -= 5;
                while(j > 0) {
                    sb.append(map.get(factor));
                    j -= 1;
                }
            }

            factor /= 10;
        }

        return sb.toString();
    }
    public static void main(String[] args) {
        String nums = intToRoman(58);
        System.out.println(nums);
    }
}

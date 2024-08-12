package Array;

import java.util.*;

/**
 * ClassName: RandomizeSet
 * Package: Array
 * Description:
 *
 * @Author Jingran Liu
 * @Create 8/12/2024 2:28 PM
 * @Version 1.0
 */
public class RandomizedSet {
    Map<Integer, Integer> map;//key: number, value: index
    List<Integer> list;
    Random rand;
    public RandomizedSet() {
        map =  new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if(map.containsKey(val)) {
            return false;
        }

        int size = list.size();
        list.add(val);
        map.put(val, size);
        return true;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)) {
            return false;
        }

        int size = list.size();
        int lastNum = list.get(size-1);

        int targetIndex = map.get(val);


        list.set(targetIndex, lastNum);
        list.remove(size-1);

        map.put(lastNum, targetIndex);
        map.remove(val);

        return true;

    }

    public int getRandom() {
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}

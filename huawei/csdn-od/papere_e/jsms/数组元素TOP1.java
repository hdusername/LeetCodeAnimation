import java.util.HashMap;
import java.util.Map;

/**
 * 查找一个数组中出现最多次数的值，如果存在相同的数量按大的值输出。
 */
public class 数组元素TOP1 {

        public static void top1 (int[] array)
        {
            // map的key存放数组中的数字，value存放该数字出现的次数
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int i = 0; i < array.length; i++)
            {
                if(map.containsKey(array[i]))
                {
                    int formerValue = map.get(array[i]);
                    map.put(array[i], formerValue + 1);
                }
                else
                {
                    map.put(array[i], 1);
                }
            }

            int maxCount = 0;
            int maxNumber = 0;
            for(Map.Entry<Integer, Integer> entry : map.entrySet())
            {
                //得到value为maxCount的key，也就是数组中出现次数最多的数字
                if(entry.getValue() >= maxCount) {
                    if (entry.getValue() == maxCount){
                        maxNumber = Math.max(entry.getKey(), maxNumber);
                    } else {
                        maxNumber = entry.getKey();
                    }

                }
            }
            System.out.println("出现次数最多的数字为：" + maxNumber);
            System.out.println("该数字一共出现" + maxCount + "次");
        }


        //解法2
    //class Main {
    //    public static void candidate (int[] array) {
    //        int[] count = new int[101];                // 假设元素最大不超过100,可以与面试官进行交流
    //        for(int i = 0; i < array.length; i++)
    //        {
    //            count[array[i]]++;                    // 对应的计数值加1
    //        }
    //        int maxCount = count[0];
    //        int maxNumber = 0;
    //        for(int i = 1; i < 100; i++)            // 找出最多出现的次数
    //        {
    //            if(count[i] > maxCount)
    //                maxCount = count[i];
    //        }
    //        for(int i = 0; i < 100; i++)            // 找出出现最多次的那个数字
    //        {
    //            if(count[i] == maxCount)
    //                maxNumber = i;
    //        }
    //        System.out.println("出现次数最多的数字为：" + maxNumber);
    //        System.out.println("该数字一共出现" + maxCount + "次");
    //    }
    //}
}

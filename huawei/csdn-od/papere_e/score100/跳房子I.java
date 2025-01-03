import java.util.*;

/**
 * 跳房子，也叫跳飞机，是一种世界性的儿童游戏。
 * 游戏参与者需要分多个回合按顺序跳到第1格直到房子的最后一格。
 * 跳房子的过程中，可以向前跳，也可以向后跳。
 *
 * 假设房子的总格数是count，小红每回合可能连续跳的步教都放在数组steps中，请问数组中是否有一种步数的组合，可以让小红两个回合跳到量后一格?
 * 如果有，请输出索引和最小的步数组合。
 *
 *  注意：
 *  数组中的步数可以重复，但数组中的元素不能重复使用。
 *  提供的数据保证存在满足题目要求的组合，且索引和最小的步数组合是唯一的。
 *
 *  输入描述：
 *  第一行输入为房子总格数count，它是int整数类型
 *  第二行输入为每回合可能连续跳的步数，它是int整数数组类型。
 *
 *  输出描述：
 *  返回“索引和”最小的满足要求的步数组合（顺序保持steps中原有顺序）
 *
 *  备注：
 *  count ≤ 1000
 *  0 ≤ steps.length ≤ 5000
 *  -100000000 ≤ steps[i] ≤ 100000000
 */
public class 跳房子I {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] arrays = Arrays.stream(scanner.nextLine().replace("[", "").replace("]", "").split(",")).mapToInt(Integer::parseInt).toArray();
        int count = Integer.parseInt(scanner.nextLine());
        Map<Integer,Integer> map = new HashMap<>();

        //StringJoiner stringJoiner = new StringJoiner(" ,");
        ArrayList<Integer> objects = new ArrayList<>();

        int minIndex = Integer.MAX_VALUE;
        for(int i=0;i<arrays.length;i++){
            int needCount = count-arrays[i];
            if(map.get(needCount) != null && minIndex>map.get(needCount)+i){
//                stringJoiner.add(needCount+"");
//                stringJoiner.add(arrays[i]+"");
                //这里找到了符合条件的值，索引的和也不一定是最小的，所以不能退出，要找完所有符合条件的值后排序找最小
                //[1,1000,1000,1000,1000,1000,1000,3,2,4]
                //5
                objects.clear();
                objects.add(needCount);
                objects.add(arrays[i]);
                //赋值最小值
                minIndex=map.get(needCount)+i;

            }
            if(map.get(arrays[i])==null) {
                //为了保证map里面放的是最小索引，因为数组中的数字是有可能重复的，这个put不在if判断中的话会导致相同的数字后面出现的会覆盖前面出现的
                map.put(arrays[i], i);
            }

        }

//        System.out.println("["+stringJoiner+"]");
        System.out.println(objects.toString());

    }
}

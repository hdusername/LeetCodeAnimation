import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 给定一个乱序的数组,删除所有的重复元素，使得每个元素只出现一次，并且按照出现的次数从高到低进行排序，相同出现次数按照第一次出现顺序进行先后排序。
 * 输入描述：
 * 一个数组
 *
 * 输出描述
 * 去重排序后的数组
 */
public class 数组去重和排序 {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String[] arr = sc.nextLine().split(",");
            System.out.println(getResult(arr));
        }

        public static String getResult(String[] arr) {
            HashMap<String, Integer> count = new HashMap<>();
            HashMap<String, Integer> first = new HashMap<>();

            for (int i = 0; i < arr.length; i++) {
                String s = arr[i];
                count.put(s, count.getOrDefault(s, 0) + 1);
                first.putIfAbsent(s, i);
            }

            StringJoiner sj = new StringJoiner(",");

            first.keySet().stream()
                    .sorted(
                            (a, b) -> {
                                int countA = count.get(a);
                                int countB = count.get(b);

                                if (countA != countB) {
                                    return countB - countA;
                                } else {
                                    int firstA = first.get(a);
                                    int firstB = first.get(b);
                                    return firstA - firstB;
                                }
                            })
                    .forEach(s -> sj.add(s));

            return sj.toString();
        }

}

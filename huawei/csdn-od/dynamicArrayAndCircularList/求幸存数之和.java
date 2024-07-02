import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 给一个正整数数列 nums，一个跳数 jump，及幸存数量 left。
 * 运算过程为：从索引0的位置开始向后跳，中间跳过 J 个数字，命中索引为 J+1 的数字，该数被敲出，并从该点起跳，以此类推，直到幸存 left 个数为止，然后返回幸存数之和。
 * 约束：
 * 0是第一个起跳点
 * 起跳点和命中点之间间隔 jump 个数字，已被敲出的数字不计入在内。
 * 跳到末尾时无缝从头开始（循环查找），并可以多次循环。
 * 若起始时 left > len(nums) 则无需跳数处理过程。
 *
 * 输入描述：
 * 第一行输入正整数数列
 * 第二行输入跳数
 * 第三行输入幸存数量
 *
 * 输出描述：
 * 输出幸存数之和
 */
public class 求幸存数之和 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int jump = Integer.parseInt(scanner.nextLine());

        int survive = Integer.parseInt(scanner.nextLine());

        ArrayList<Integer> linkedList = new ArrayList<>();

        int idx =1;

        for(int i =0;i<array.length;i++){
            linkedList.add(array[i]);
        }

        while (linkedList.size()>survive){

            idx+=jump;

            idx = idx % linkedList.size();

            linkedList.remove(idx);

            //idx--;


        }

        System.out.println(linkedList.stream().reduce(Integer::sum).orElse(0));
    }
}

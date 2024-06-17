import java.util.Arrays;
import java.util.Scanner;

/**
 * 小朋友出操，按学号从小到大排成一列；
 * 小明来迟了，请你给小明出个主意，让他尽快找到他应该排的位置。
 * 算法复杂度要求不高于nLog(n)；学号为整数类型，队列规模 ≤ 10000；
 *
 * 输入描述
 * 第一行：输入已排成队列的小朋友的学号（正整数），以空格隔开，例如：
 * 第二行：小明学号，如：110
 *
 * 输出描述：
 * 输出一个数字，代表队列位置（从1开始）。例如：6
 */
public class 小明找位置 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int ans = 0;

        int[] studentIds = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int in = Integer.parseInt(scanner.nextLine());

        //在studentIds中找in这个数字，找到了返回其在数组中的角标，找不到就返回（-其应该所在位置的角标-1）
        //因为如果在角标0处有要找的数字，就返回0，如果比角标0处的数字还要小，就返回-1，就区分开了，注意数字前提是要有序
        int pos = Arrays.binarySearch(studentIds, in);

        if(pos<0){
            ans=-pos;
        }else {
            ans+=1;
        }
        System.out.println(ans);

    }
}

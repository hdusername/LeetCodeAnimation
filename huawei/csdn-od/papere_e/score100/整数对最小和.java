import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定两个整数数组array1、array2，数组元素按升序排列
 * 假设从array1、array2中分别取出一个元素可构成一对元素，现在需要取出k对元素，
 * 并对取出的所有元素求和，计算和的最小值
 *
 * 注意：
 * 两对元素如果对应于array1、array2中的两个下标均相同，则视为同一对元素。
 *
 * 输入描述：
 * 输入两行数组array1、array2，每行首个数字为数组大小size(0 < size <= 100);
 * 0 < array1[i] <= 1000
 * 0 < array2[i] <= 1000
 * 接下来一行为正整数k
 * 0 < k <= array1.size() * array2.size()
 *
 * 输出描述：
 * 满足要求的最小和
 *
 * 例如：
 * 输入
 * 3 1 1 2
 * 3 1 2 3
 * 2
 *
 * 输出：
 * 4
 *
 * 用例中，需要取2对元素
 * 取第一个数组第0个元素与第二个数组第0个元素组成1对元素[1,1];
 * 取第一个数组第1个元素与第二个数组第0个元素组成1对元素[1,1];
 * 求和为1+1+1+1=4，为满足要求的最小和。
 */
public class 整数对最小和 {

    public static void main(String[] args) {
        //本题将所有可能都放到list种，然后排序取前k对数的和

        Scanner scanner = new Scanner(System.in);
        int[] s1 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] s2 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int k = Integer.parseInt(scanner.nextLine());

        ArrayList<Integer> sortedList = new ArrayList<>();
        int ans = 0;

        //因为输入的第一个数字为数组大小，所以去掉，从1开始遍历
        for(int x=1;x< s1.length; x++){
            for(int y=1; y<s2.length; y++){
                sortedList.add(s1[x] + s2[y]);
            }
        }

        sortedList.sort((a, b)->a-b);

        for(int i =0; i<k; i++){
            ans+=sortedList.get(i);
        }

        System.out.println(ans);
    }
}

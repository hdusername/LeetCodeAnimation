import java.util.HashSet;
import java.util.Scanner;

/**
 * 小明玩一个游戏。
 * 系统发1+n张牌，每张牌上有一个整数。
 * 第一张给小明，后n张按照发牌顺序排成连续的一行。
 * 需要小明判断，后n张牌中，是否存在连续的若干张牌，其和可以整除小明手中牌上的数字。
 *
 * 输入描述:
 * 输入数据有多组，每组输入数据有两行，输入到文件结尾结束。
 * 第一行有两个整数n和m，空格隔开。m代表发给小明牌上的数字。
 * 第二行有n个数，代表后续发的n张牌上的数字，以空格隔开。
 *
 * 输出描述：
 * 对每组输入，如果存在满足条件的连续若干张牌，则输出1;否则，输出0
 *
 * 备注：
 * 1 ≤ n ≤ 1000
 * 1 ≤ 牌上的整数 ≤ 400000
 * 输入的组数，不多于1000
 * 用例确保输入都正确，不需要考虑非法情况。
 *
 * 用例输入：
 * 6 7
 * 2 12 6 3 5 5
 * 10 11
 * 1 1 1 1 1 1 1 1 1 1
 *
 * 输出：
 * 1
 * 0
 *
 * 解释：
 * 两组输入。第一组小明牌的数字为7，再发了6张牌。第1、2两张牌教字和为14，可以整除7，输出1，第二组小明牌的教字为11，再发了10张牌，这10张牌数字和为10，无法整除11，输出0。
 */
public class 数字游戏 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        out:while (scanner.hasNextInt()){
            HashSet<Integer> hashSet = new HashSet<>();
            hashSet.add(0);
            int sum = 0;
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] nums = new int[n];
            for(int i=0; i<n; i++){
                nums[i] = scanner.nextInt();
            }

            for(int i=0; i<n; i++){
                int input = nums[i];
                sum += input;
                int num = sum%m;

                if(hashSet.contains(num)){
                    System.out.println(1);
                    continue out;
                }
                hashSet.add(num);
            }
            System.out.println(0);
        }

    }

    //标准答案
    //import java.util.Arrays;
    //import java.util.HashSet;
    //import java.util.Scanner;
    //
    //public class Main {
    //    public static void main(String[] args) {
    //        Scanner sc = new Scanner(System.in);
    //
    //        while (sc.hasNextLine()) {
    //            int[] tmp = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    //            int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    //            System.out.println(isExist(nums, tmp[1]));
    //        }
    //    }
    //
    //    public static int isExist(int[] nums, int m) {
    //        HashSet<Integer> remain = new HashSet<>();
    //        remain.add(0);
    //
    //        int sum = 0;
    //        for (int num : nums) {
    //            sum += num;
    //            if (remain.contains(sum % m)) {
    //                return 1;
    //            } else {
    //                remain.add(sum % m);
    //            }
    //        }
    //
    //        return 0;
    //    }
    //}
}

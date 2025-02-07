import java.util.Scanner;

/**
 * 在通信系统中，一个常见的问题是对用户进行不同策略的调度，会得到不同的系统消耗和性能
 * 假设当前有n个待串行调度用户，每个用户可以使用A/B/C三种不同的调度策略，不同的策略会消耗不同的系统资源。请你根据如下规则进行用户调度，并返回总的消耗资源数。
 * 规则：
 * 相邻的用户不能使用相同的调度策略，例如，第1个用户使用了A策略，则第2个用户只能使用B或者C策略。
 * 对单个用户而言，不同的调度策略对系统资源的消耗可以归一化后抽象为数值。例如，某用户分别使用A/B/C策略的系统消耗分别为15/8/17。
 * 每个用户依次选择当前所能选择的对系统资源消耗最少的策略（局部最优），如果有多个满足要求的策略，选最后一个。
 *
 * 输入描述：
 * 第一行表示用户个数n
 * 接下来每一行表示一个用户分别使用三个策略的系统消耗resA resB resC
 *
 * 输出描述；
 * 最优策略组合下的总的系统资源消耗数
 */
public class 用户调度问题 {

    /**
     * 注意题目中说的是相邻的用户不能使用相同的策略，也就是说第一个用户和第三个用户是可以使用相同的策略的
     * @param args
     */
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int n = sc.nextInt();

            int[][] res = new int[n][3];
            for (int i = 0; i < n; i++) {
                res[i][0] = sc.nextInt();
                res[i][1] = sc.nextInt();
                res[i][2] = sc.nextInt();
            }

            System.out.println(getResult(n, res));
        }

        public static int getResult(int n, int[][] res) {
            int last = -1;
            int sum = 0;

            for (int i = 0; i < n; i++) {
                last = getMinEleIdx(res[i], last);
                sum += res[i][last];
            }

            return sum;
        }

        public static int getMinEleIdx(int[] arr, int excludeIdx) {
            int minEleVal = Integer.MAX_VALUE;
            int minEleIdx = -1;

            for (int i = 0; i < arr.length; i++) {
                if (i == excludeIdx) continue;

                if (arr[i] <= minEleVal) {
                    minEleVal = arr[i];
                    minEleIdx = i;
                }
            }

            return minEleIdx;
        }

}

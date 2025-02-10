import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个长度为n的整型数组，表示一个选手在n轮内可选择的牌面分数。选手基于规则选牌，
 * 请计算所有轮结束后其可以获得的最高总分数。
 *
 * 选择规则如下：
 * 在每轮里选手可以选择获取该轮牌面，则其总分数加上该轮牌面分数，为其新的总分数。
 * 选手也可不选择本轮牌面直接跳到下一轮，此时将当前总分数还原为3轮前的总分数，若当前轮次小于等于3（即在第1、2、3轮选择跳过轮次），则总分数置为0。
 * 选手的初始总分数为0，且必须依次参加每一轮。
 *
 * 输入描述：
 * 第一行为一个小写逗号分割的字符串，表示n轮的牌面分数，1<= n <=20。
 * 分数值为整数，-100 <= 分数值 <= 100。
 * 不考虑格式问题。
 *
 * 输出描述：
 * 所有轮结束后选手获得的最高总分数。
 */
public class 玩牌高手 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputs = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int n = inputs.length;
        //这个问题相当于完全背包，所以可以用一维数组解决问题
        //这里多新建了一个位置的数组，是为了在比较第一轮时数组不越界的，也可以写为如下方式，单独判断0这个位置
        /**
         * import java.util.Arrays;
         * import java.util.Scanner;
         *
         * public class Main {
         *     public static void main(String[] args) {
         *         Scanner sc = new Scanner(System.in);
         *
         *         int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
         *         int n = nums.length;
         *
         *         int[] dp = new int[n];
         *         for (int i = 0; i < n; i++) {
         *             if (i == 0) {
         *                 dp[0] = Math.max(0, nums[0]);
         *             } else if (i < 3) {
         *                 dp[i] = Math.max(0, dp[i - 1] + nums[i]);
         *             } else {
         *                 dp[i] = Math.max(dp[i - 3], dp[i - 1] + nums[i]);
         *             }
         *         }
         *
         *         System.out.println(dp[n - 1]);
         *     }
         * }
         */
        int[] results = new int[n+1];

        for(int i=1; i<n+1; i++){
            if(i<=3){
                results[i] = Math.max(results[i-1]+inputs[i-1], 0);
            }else {
                results[i] = Math.max(results[i-1]+inputs[i-1], results[i-3]);
            }
        }
        System.out.print(results[n]);
    }
}

import java.util.Scanner;

/**
 * 在一个国家仅有1分，2分，3分硬币，将钱N兑换成硬币有很多种兑法。请你编程序计算出共有多少种兑法。
 */
public class 换硬币 {


//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("请输入金额 N：");
//        int n = scanner.nextInt();
//        System.out.println("共有 " + calculateWays(n) + " 种兑换方式。");
//    }
//chatgpt的答案
//    public static int calculateWays(int n) {
//        if (n < 0) {
//            return 0; // 金额为负数，没有合法解
//        }
//
//        // dp[i] 表示金额 i 的兑换方式数
//        int[] dp = new int[n + 1];
//        dp[0] = 1; // 金额为 0 的兑换方式只有 1 种：不使用任何硬币
//
//        // 硬币面值数组
//        int[] coins = {1, 2, 3};
//
//        // 动态规划填表
//        for (int coin : coins) {
//            for (int i = coin; i <= n; i++) {
//                dp[i] += dp[i - coin];
//            }
//        }
//        return dp[n];
//    }





  public static void main(String[] args) {

      //作者给出的答案
                     Scanner cin = new Scanner (System.in);
                     while(cin.hasNext()){
                             int n = cin.nextInt();
                             int [] value = {1,2,3};
                             int [] dp = new int [32800];//N的最大值

                             dp [0] =1;
                             for(int i = 0;i<value.length;i++){
                                     for(int j = value[i];j<=n;j++){
                                             dp[j] = dp[j]+dp[j-value[i]];
                                         }
                                 }


                             System.out.println(dp[n]);
                         }

                 }


//    /**
//     * 计算硬币兑换的方式数（暴力解法）
//     * @param n 要兑换的金额
//     * @return 兑换方式数
//     */
//    public static int calculateWays(int n) {
//        int count = 0;
//        // 暴力枚举所有可能的硬币组合
//        for (int one = 0; one <= n; one++) { // 枚举 1 分硬币的个数
//            for (int two = 0; two <= n / 2; two++) { // 枚举 2 分硬币的个数
//                for (int three = 0; three <= n / 3; three++) { // 枚举 3 分硬币的个数
//                    if (one + 2 * two + 3 * three == n) { // 判断当前组合是否满足总金额
//                        count++;
//                    }
//                }
//            }
//        }
//        return count;
//    }
}

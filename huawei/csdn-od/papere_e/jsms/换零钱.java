/**
 * 给定一个整数数组 coins，表示不同面额的硬币；以及一个整数 amount，表示总金额。本题要求的是在每种硬币的数量是无限的情况下，计算并返回可以凑成总金额所需的 最少的硬币个数 。
 * 每种硬币的数量是无限的，这是一个典型的「完全背包」求最优解问题。
 */
public class 换零钱 {


    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int max = Integer.MAX_VALUE;
        for (int j = 0; j < dp.length; j++) {
            dp[j] = max;
        }
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if(dp[j-coins[i]] != max)
                    dp[j] = Math.min(dp[j],dp[j-coins[i]] + 1);
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }


}


//一维数组解决01背包问题
//题目描述
//某个 充电站 ，可提供n个充电设备，每个充电设备均有对应的輸出功率。任意个充电
//设备组合的输出功率总和，均构成功率集合P的1个元素。功率集合P的最优元秦，表
//示最接近充电站最大输出功率p_max的元素。
//输入描述
//输入为3行：
//第1行为 充电 设备个数n.
//第2行为每个充电设备的输出功率。
//第3行为充电站最大输出功率p_maxₒ
//输出描述
//功率隼合P的最优元表
//补充说明：
//1 充电设备个数n>0
//2.最优元素必须小于或等于充电站最大输出功率p_maxₒ
//示例1
//输入
//4
//50 20 20 60
//90
//输出
//90
//说明
//当充电设备输出功率为50,20,20组合时,其充电功率总和为90,最接近充电站最大充电输出功率,
//因此最优元素为90
//示例2
//输入
//2
//50 40
//30
//输出
//0
//说明
//所有率电设备的输出功率组合,均大于充电站最大充电功率30,此时最优元素为0

//import java.util.Scanner;
//2
//3 public class Main {
//4 public static void main(String[] args) {
//5 Scanner sc = new Scanner(System.in);
//6
//7 int n = sc.nextInt(); // 输入充电设备个数
//8 int[] a = new int[n];
//9 for (int i = 0; i < n; ++i) {
//10 a[i] = sc.nextInt(); // 输入每个充电设备的输出功率
//11 }
//12 int m = sc.nextInt(); // 输入充电站最大输出功率
//13
//14 int[] f = new int[m + 1]; // 初始化动态规划数组f
//15
//16 // 遍历每个充电设备
//17 for (int i = 0; i < n; ++i) {
//18 // 从最大功率m开始，向下遍历到当前设备的功率a[i]
//19 for (int j = m; j >= a[i]; --j) {
//20 f[j] = Math.max(f[j], f[j - a[i]] + a[i]); // 更新f[j]
//21 }
//22 }
//23
//24 int max_f = 0;
//25 for (int i = 0; i <= m; ++i) {
//26 if (f[i] > max_f) {
//27 max_f = f[i];
//28 }
//29 }
//30
//31 System.out.println(max_f); // 输出最优功率和
//Python3
//C++
//C语言
//32 sc.close();33 }
//34 }
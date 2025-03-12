import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个二维整数矩阵，要在这个矩阵中选出一个子矩阵，使得这个子矩阵内所有的数字和尽量大，我们把这个子矩阵称为子矩阵，子矩阵的选取原则是原矩阵中一块相互连续的矩形区域。
 *
 * 输入描述：
 * 输入的第一行包含2个整数n, m(1 <= n, m <= 10)，表示一个n行m列的矩阵，下面有n行，每行有m个整数，同一行中，每2个数字之间有1个空格，最后一个数字后面没有空格，所有的数字的在[-1000, 1000]之间。
 *
 * 输出描述：
 * 输出一行一个数字，表示选出的和最大子矩阵内所有的数字和。
 *
 * 输入：
 * 3 4
 * -3 5 -1 5
 * 2 4 -2 4
 * -1 3 -1 3
 *
 * 输出
 * 20
 *
 * 解释：
 * 一个3*4的矩阵中，后面3列的子矩阵求和加起来等于20，和最大。
 */
public class 最大矩阵和 {

    /**
     * 本题需要使用最大子数组和的思想，将二维数组转变为一维数组后就可以使用最大子数组和了
     * 最大子数组和使用动态规划方式实现
     * @param args
     */
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] matrix = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }

            //需要求最大值，所以这里先赋值为最小，方便后续比较
            int ans = Integer.MIN_VALUE;

            for(int i=0; i<n; i++){
                //先计算单行矩阵中和最大的子数组
                ans = Math.max(ans, getMaxSum(matrix[i]));
                for(int j=i+1; j<n; j++){
                    for(int k=0; k<m; k++){
                        //合并多行为一行，对应列的值相加，改变了单行矩阵中的值，因为之前单行矩阵已经比较过大小了，所以正好存放合并后的值
                        matrix[i][k] = matrix[j][k]+matrix[i][k];
                    }
                    ans = Math.max(ans, getMaxSum(matrix[i]));
                }
            }

            System.out.println(ans);



//以下为给出的正确答案
//            for (int i = 0; i < n; i++) {
//                ans = Math.max(ans, maxSubArraySum(matrix[i])); // 单行(第i行)子矩阵最大和
//
//                for (int j = i + 1; j < n; j++) { // 将第 i+1 ~ n-1 行依次合并入第 i 行
//
//                    for (int k = 0; k < m; k++) {  // 矩阵压缩
//                        matrix[i][k] += matrix[j][k];
//                    }
//
//                    ans = Math.max(ans, maxSubArraySum(matrix[i])); // 多行子矩阵最大和
//                }
//            }

//            System.out.println(ans);
        }

    private static int getMaxSum(int[] matrix) {
        int[] dp = new int[matrix.length];
        int maxVal = matrix[0];
        dp[0]=maxVal;
        for(int i=1; i<matrix.length; i++){
            dp[i] = Math.max(matrix[i], dp[i-1]+matrix[i]);
            maxVal = Math.max(dp[i], maxVal);
        }
        return maxVal;
    }

    // 最大子数组和求解
        public static int maxSubArraySum(int[] nums) {
            int dp = nums[0];
            int res = dp;

            for (int i = 1; i < nums.length; i++) {
                dp = Math.max(dp, 0) + nums[i];
                res = Math.max(res, dp);
            }

            return res;
        }

}

//标准答案
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//
//        int[][] matrix = new int[n][m];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                matrix[i][j] = sc.nextInt();
//            }
//        }
//
//        int ans = Integer.MIN_VALUE;
//
//        for (int i = 0; i < n; i++) {
//            ans = Math.max(ans, maxSubArraySum(matrix[i])); // 单行(第i行)子矩阵最大和
//
//            for (int j = i + 1; j < n; j++) { // 将第 i+1 ~ n-1 行依次合并入第 i 行
//
//                for (int k = 0; k < m; k++) {  // 矩阵压缩
//                    matrix[i][k] += matrix[j][k];
//                }
//
//                ans = Math.max(ans, maxSubArraySum(matrix[i])); // 多行子矩阵最大和
//            }
//        }
//
//        System.out.println(ans);
//    }
//
//    // 最大子数组和求解
//    public static int maxSubArraySum(int[] nums) {
//        int dp = nums[0];
//        int res = dp;
//
//        for (int i = 1; i < nums.length; i++) {
//            dp = Math.max(dp, 0) + nums[i];
//            res = Math.max(res, dp);
//        }
//
//        return res;
//    }
//}

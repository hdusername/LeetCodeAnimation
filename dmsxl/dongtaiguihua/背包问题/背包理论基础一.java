import java.util.Arrays;

/**
 * 有n件物品和一个最多能背重量为w 的背包。第i件物品的重量是weight[i]，得到的价值是value[i] 。每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大
 *
 * 背包的最大容量是4，物品为：物品0重量1价值15，物品1重量3价值20，物品2重量4价值30
 * 问背包能背的物品最大价值是多少？
 *
 * 要知道这是一个0-1背包问题，也就是说每个物品只能放到背包里一次，并且只有一个，要么选择放进去，要么选择不放进去
 */
public class 背包理论基础一 {

    public static void main(String[] args) {
        int[] weight = {1,3,4};
        int[] value = {15,20,30};
        int bagSize = 4;
        //解法1
       // testWeightBagProblem(weight,value,bagSize);
        //解法2
        testWeightBagProblem2(weight,value,bagSize);
    }

    /**
     * 动态规划获得结果
     * @param weight  物品的重量
     * @param value   物品的价值
     * @param bagSize 背包的容量
     */
    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize){

        // 创建dp数组
        int goods = weight.length;  // 获取物品的数量
        int[][] dp = new int[goods][bagSize + 1];

        // 初始化dp数组
        // 创建数组后，其中默认的值就是0
        //j代表了背包总容量是多少
        //这里的逻辑表示放入物品0，当背包容量是多少的时候，价值是多少，此时只有物品0，没有其它物品
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }

        // 填充dp数组
        //i=0\1\2表示着三个物品，i=0的价值已经在上一个for循环中填入了
        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) {
                    /**
                     * 当前背包的容量都没有当前物品i大的时候，是不放物品i的
                     * 那么前i-1个物品能放下的最大价值就是当前情况的最大价值
                     */
                    dp[i][j] = dp[i-1][j];
                } else {
                    /**
                     * 当前背包的容量可以放下物品i
                     * 那么此时分两种情况：
                     *    1、不放物品i
                     *    2、放物品i
                     * 比较这两种情况下，哪种背包中物品的最大价值最大
                     */
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i-1][j-weight[i]] + value[i]);
                }
            }
        }

        // 打印dp数组
        for (int i = 0; i < goods; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }

    /**
     * 初始化 dp 数组做了简化(给物品增加冗余维)。这样初始化dp数组，默认全为0即可。
     * dp[i][j] 表示从下标为[0 - i-1]的物品里任意取，放进容量为j的背包，价值总和最大是多少。
     * 其实是模仿背包重量从 0 开始，背包容量 j 为 0 的话，即dp[i][0]，无论是选取哪些物品，背包价值总和一定为 0。
     * 可选物品也可以从无开始，也就是没有物品可选，即dp[0][j]，这样无论背包容量为多少，背包价值总和一定为 0。
     * @param weight  物品的重量
     * @param value   物品的价值
     * @param bagSize 背包的容量
     */
    public static void testWeightBagProblem2(int[] weight, int[] value, int bagSize){

        // 创建dp数组
        int goods = weight.length;  // 获取物品的数量
        int[][] dp = new int[goods + 1][bagSize + 1];  // 给物品增加冗余维，i = 0 表示没有物品可选

        // 初始化dp数组，默认全为0即可
        // 填充dp数组
        // ///注意这里是小于等于，上面那个方法testWeightBagProblem这里是小于i=0\1\2表示着三个物品，这里的i=1\2\3表示着三个物品
        for (int i = 1; i <= goods; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i - 1]) {  // i - 1 对应物品 i，因为weight[i-1]取出的是当前物品容量，weight数组中保存数据的索引还是0，1，2
                    /**
                     * 当前背包的容量都没有当前物品i大的时候，是不放物品i的
                     * 那么前i-1个物品能放下的最大价值就是当前情况的最大价值
                     */
                    dp[i][j] = dp[i - 1][j];
                } else {
                    /**
                     * 当前背包的容量可以放下物品i
                     * 那么此时分两种情况：
                     *    1、不放物品i
                     *    2、放物品i
                     * 比较这两种情况下，哪种背包中物品的最大价值最大
                     */
                    //这里当i=1时，都是dp[0][?]，此时不管j等于多少，价值都是0，所以最大值取的都是value[i - 1]，类似于testWeightBagProblem方法中for (int j = weight[0]; j <= bagSize; j++) {
                    //            dp[0][j] = value[0];
                    //        }
                    //的逻辑
                    dp[i][j] = Math.max(dp[i - 1][j] , dp[i - 1][j - weight[i - 1]] + value[i - 1]);  // i - 1 对应物品 i，同样的和weight一样，value数组中保存数据的索引还是0，1，2
                }
            }
        }

        // 打印dp数组
        for(int[] arr : dp){
            System.out.println(Arrays.toString(arr));
        }
    }



}

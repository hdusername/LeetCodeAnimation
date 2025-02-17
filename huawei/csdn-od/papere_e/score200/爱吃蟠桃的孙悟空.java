import java.util.Arrays;
import java.util.Scanner;

/**
 * 孙悟空爱吃蟠桃，有一天趁着蟠桃园守卫不在来偷吃。已知蟠桃园有 N 棵桃树，每颗树上都有桃子，守卫将在 H 小时后回来。
 * 孙悟空可以决定他吃蟠桃的速度K（个/小时），每个小时选一颗桃树，并从树上吃掉 K 个，如果树上的桃子少于 K 个，则全部吃掉，并且这一小时剩余的时间里不再吃桃。
 * 孙悟空喜欢慢慢吃，但又想在守卫回来前吃完桃子。
 * 请返回孙悟空可以在 H 小时内吃掉所有桃子的最小速度 K（K为整数）。如果以任何速度都吃不完所有桃子，则返回0。
 *
 * 输入描述：
 * 第一行输入为 N 个数字，N 表示桃树的数量，这 N 个数字表示每颗桃树上蟠桃的数量。
 * 第二行输入为一个数字，表示守卫离开的时间 H。
 * 其中数字通过空格分割，N、H为正整数，每颗树上都有蟠桃，且 0 < N < 10000，0 < H < 10000。
 *
 * 输出描述：
 * 吃掉所有蟠桃的最小速度 K，无解或输入异常时输出 0。
 *
 * 用例输入：
 * 2 3 4 5
 * 4
 *
 * 输出
 * 5
 *
 *
 */
public class 爱吃蟠桃的孙悟空 {
    public static void main(String[] args) {
        int k = 0;
        try {

            Scanner scanner = new Scanner(System.in);
            int[] trees = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int h = Integer.parseInt(scanner.nextLine());
            Arrays.sort(trees);
            if (trees.length > h) {
                //每个小时只能在一棵树上，如果树的数量超过了总时间，是吃不完的，所以此情况异常
                throw new RuntimeException("");
            }

            if(trees.length == h){
                //如果数量相同就取树上桃子的最大值，因为在h小时内，只能上trees.length颗树，意味着一棵树只能待1个小时
                k=trees[trees.length-1];
                throw new RuntimeException("");
            }


            //桃的总数量
            //int treeSum = Arrays.stream(trees).sum();
            //k的最小值是桃树中最少桃的数量
            //k的最大值是桃树中最多桃的数量
            int lK = trees[0];
            int rK = trees[trees.length-1];

            while (lK<=rK){
                int middleK = (lK+rK) >> 1;

                //几个小时可以吃完所有桃
                int sumHour =0;

                for(int i=0; i<trees.length; i++){

                    int count = getCount(trees[i], middleK);
                    sumHour+=count;
                }

                if(sumHour<=h){
                    k=middleK;
                    rK=middleK-1;
                }else {
                    lK = middleK+1;
                }
            }
            System.out.println(k);
        }catch (Exception e){
            System.out.println(k);
        }

    }

    private static int getCount(int tree, int middleK) {

        //注意这个括号一定要加上，不然计算结果是错误的
       return tree/middleK+(tree%middleK>0?1:0);
    }


// 给的正确答案
//        public static int getResult(int[] cnts, int h) {
//            // 每个小时只能选一颗桃树，因此 h 小时最多只能选 h 棵树，如果桃树数量cnts.length > h，那么h小时肯定吃不完
//            if (cnts.length > h) {
//                return 0;
//            }
//
//            // 拥有最多桃子的那颗桃树上的桃子数量
//            int max = Arrays.stream(cnts).max().orElse(0);
//
//            // 如果桃树数量就是h棵，那么只能一小时吃完一颗树，才能保证h小时内吃完。此时，吃桃速度至少是max
//            if (cnts.length == h) {
//                return max;
//            }
//
//            // 如果只有1棵桃树，且这颗树上只有1个桃，那么吃桃速度可以是1
//            int min = 1;
//
//            // 当桃树数量少于h棵时，以max速度吃桃肯定可以吃完，但是不一定是最优解
//            int ans = max;
//
//            // 二分法
//            while (min <= max) {
//                // 取中间值作为吃桃速度进行尝试
//                int mid = (min + max) >> 1;
//
//                // 如果以mid速度，可以在h小时内吃完cnts所有桃，那么mid就是一个可能解
//                if (check(mid, h, cnts)) {
//                    ans = mid;
//                    // 继续尝试更小的速度
//                    max = mid - 1;
//                } else {
//                    // 以mid速度无法在h小时内吃完cnts所有桃，那么mid就取小了，下次应该取更大的吃桃速度
//                    min = mid + 1;
//                }
//            }
//
//            return ans;
//        }
//
//        public static boolean check(int speed, int limit, int[] cnts) {
//            // 已花费时间
//            int cost = 0;
//
//            for (int cnt : cnts) {
//                // 以speed速度吃完一颗桃树需要的时间，累加进cost
//                cost += cnt / speed + (cnt % speed > 0 ? 1 : 0);
//
//                // 如果已花费时间超过了limit限制，那么说明无法以speed速度在limit时间内吃完所有桃树，此时可以直接返回false
//                if (cost > limit) {
//                    return false;
//                }
//            }
//
//            // 可以以speed速度，在limit小时内吃完所有cnts桃树
//            return true;
//        }

}

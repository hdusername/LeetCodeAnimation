import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个数组X和正整数K，请找出使表达式：
 * X[i] - X[i + 1] -  ... - X[i + K - 1]
 *
 * 结果最接近于数组中位数的下标 i ，如果有多个 i 满足条件，请返回最大的 i.
 * 其中，数组中位数：长度为N的数组，按照元素的值大小升序排列后，下标为 N/2 元素的值
 *
 * 备注：
 * 数组X的元素均为正整数
 * X的长度n取值范围：2 ≤ n ≤ 1000
 * K大于0目小于数组的大小
 * i 的取值范围: 0 ≤ i < 1000
 * 题目的排序数组X[N]的中位数是X[N/2]
 *
 * 输入：
 * [50,50,2,3],2
 * 输出：
 * 1
 * 说明：
 * 中位数为50：[50,50,2,3]升序排序后变成[2,3,50,50]，中位数为下标4/2=2的元素50
 * 计算结果为1：X [50,50,2,3] 根据题目计算X[i] - ... - X[i + k - 1] 得出三个数0 (X[0] - X[1] = 50 - 50) 、48 (X[1] - X[2] = 50 - 2)
 * 和 -1 (X[2]-X[3] = 2 - 3) ，其中48最接近50，因此返回下标1。
 */
public class 计算最接近的数 {

    /**
     * 从 X[i] - X[i + 1] -  ... - X[i + K - 1]这个表达式可以看出，K为1，就只有一个X[i],如果K为2，就会取两个数字X[i]、X[i+1]
     * @param args
     */
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            String line = sc.nextLine();

            int i = line.lastIndexOf(",");

            int[] x =
                    Arrays.stream(line.substring(1, i - 1).split(",")).mapToInt(Integer::parseInt).toArray();
            int k = Integer.parseInt(line.substring(i + 1));

            System.out.println(getResult(x, k));
        }


        public static int getResult(int[] x, int k) {

            //这段逻辑是我自己写的
            int targetIndex = x.length/2;
            //获取中位数
            int targetVal = Arrays.stream(x).sorted().toArray()[targetIndex];


            int ans = 0;
            //首先构建第一个窗口值
            int window = x[0];
            for(int i=1; i<k; i++){
                window-=x[i];

            }
            //与中位数的差距
            int len = Math.abs(targetVal - window);

            //这里结束边界取x.length-k（开区间）避免越界
            for(int i=1; i<=x.length-k; i++){
                window = window -x[i-1]+2*x[i]-x[i+k-1];

                if(len>=Math.abs(targetVal-window)){
                    len = Math.abs(targetVal-window);

                    ans = i;
                }

            }

            return ans;

            //这段逻辑是答案中的
//            int n = x.length;
//
//            // x数组的中位数
//            int mid = Arrays.stream(x).sorted().toArray()[n / 2];
//
//            // 初始化滑窗0~k-1, window为滑窗内部元素的表达式计算结果
//            int window = x[0];
//            for (int i = 1; i < k; i++) {
//                window -= x[i];
//            }
//
//            // window和中位数的差距
//            int minDiff = Math.abs(mid - window);
//            // window滑窗起始索引
//            int idx = 0;
//
//            // 滑窗右移
//            for (int i = 1; i <= n - k; i++) {
//                // 右移一格后，新滑窗的表达式计算结果
//                window += -x[i - 1] + 2 * x[i] - x[i + k - 1];
//
//                // 新滑窗window值和中位数的差距
//                int diff = Math.abs(mid - window);
//
//                // 结果最接近于数组中位数的下标 i ，如果有多个 i 满足条件，请返回最大的 i
//                if (diff <= minDiff) {
//                    minDiff = diff;
//                    idx = i;
//                }
//            }
//
//            return idx;
        }

}

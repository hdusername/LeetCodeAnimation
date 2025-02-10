import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * 在一条笔直的公路上安装了N个路灯，从位置0开始安装，路灯之间间距固定为100米。 每个路灯都有自己的照明半径，请计算第一个路灯和最后一个路灯之间，无法照明的区间的长度和。
 *
 * 输入描述：
 * 第一行为一个数N，表示路灯个数，1<=N<=100000
 * 第二行为N个空格分隔的数，表示路灯的照明半径，1<=照明半径<=100000*100
 *
 * 输出描述：
 * 第一个路灯和最后一个路灯之间，无法照明的区间的长度和
 *
 * 输入：
 * 2
 * 50 50
 *
 * 输出：
 * 0
 *
 * 解释
 * 路灯1覆盖0-50，路灯2覆盖50-100，路灯1和路灯2之间(0米-100米)无未覆盖的区间。
 */
public class 路灯照明问题 {



        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int n = sc.nextInt();

            int[][] ranges = new int[n][2];
            for (int i = 0; i < n; i++) {
                int center = i * 100;
                int r = sc.nextInt();
                ranges[i][0] = center - r;
                ranges[i][1] = center + r;
            }

            System.out.println(getResult(n, ranges));
        }

        public static int getResult(int n, int[][] ranges) {
            int ans = 0;

            // 按起始位置升序，起始位置相同，则继续按结束位置降序
            Arrays.sort(ranges, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);

            int t = ranges[0][1]; // 上一个区间的结束位置

            for (int i = 1; i < n; i++) {
                // 当前区间的【开始位置，结束位置】
                int s = ranges[i][0];
                int e = ranges[i][1];

                // 有交集
                if (t >= s) {
                    // 合并后的新区间将变为下一轮的上一个区间，t为新区间的结束位置
                    //考虑两个起始位置相同但结束位置不同的区间：[1,5]和[1,3]。按结束位置降序排列后，先处理[1,5]，合并后t=5。接下来处理[1,3]，
                    // s=1 <= t=5，无需更新t，直接跳过。如果排序相反，先处理[1,3]，合并后t=3，然后处理[1,5]，s=1 <= t=3，但e=5 > t=3，
                    // 需要更新t=5。虽然最终结果相同，但第二种情况多了一次合并操作
                    //但是对于本题来说只是更新了一下t的值，感觉没有太大影响
                    t = Math.max(e, t);
                } else {
                    // 没有交集，则统计区间间隙 s - t
                    ans += s - t;
                    // 当前区间变为下一轮的上一个区间，更新t
                    t = e;
                }
            }

            return ans;
        }



}

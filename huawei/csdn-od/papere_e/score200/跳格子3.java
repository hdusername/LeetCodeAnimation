import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 小明和朋友们一起玩跳格子游戏，每个格子上有特定的分数 score = [1, -1, -6, 7, -17, 7]，
 * 从起点score[0]开始，每次最大的步长为k，请你返回小明跳到终点 score[n-1] 时，能得到的最大得分。
 *
 * 输入描述：
 * 第一行输入总的格子数量 n
 * 第二行输入每个格子的分数 score[i]
 * 第三行输入最大跳的步长 k
 *
 * 输出描述：
 * 输出最大得分
 *
 * 备注：
 * 格子的总长度 n 和步长 k 的区间在 [1, 100000]
 * 每个格子的分数 score[i] 在 [-10000, 10000] 区间中
 *
 * 用例输入：
 * 6
 * 1 -1 -6 7 -17 7
 * 2
 *
 * 输出：
 * 14
 */
public class 跳格子3 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int n = Integer.parseInt(sc.nextLine());
//        int[] scores = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        int k = Integer.parseInt(sc.nextLine());
//
//        //本题可以抽象成取固定区间最大值dp[i] =max(dp[i-k], dp[i-k+1], ...., dp[i-2], dp[i-1]) + score[i]
//        //因为是跨越k步，所以取最大值的区间为k+1，意味着在这个区间内任意位置都可以通过多种方式跳到区间的最后一个位置，这时最后一个位置的
//        //值加上窗口内这个位置之前的值和最大的值，就是最后一个位置的最大值，比如题目中用例输入了1 -1 -6 7 -17 7，步长为2，那么第一个区间就是1 -1 -6,
//        //因为1可以分两种情况跨越到-6，一种是直接跨越2步就是-6，还有一种是跨越1步到-1，再跨越1步到-6，需要取这两种方式哪种到-6的和最大
//        //显然是直接跨越两步为-6位置最大，在递减队列中前两个位置的排序也是1 0，-6这个位置就用队列最大值1加上-6等于-5
//
//        //以上理解不太准确，通过构造的递减序列可以看到，队列头存的是max(dp[i-k], dp[i-k+1], ...., dp[i-2], dp[i-1]) + score[i]的最大值
//        //所以统计的窗口范围就是k+1

//        //可以理解为-6这个位置要加上前k步最大的值才可以取到-6这个位置的最大，窗口向后移动一位后，7加入到窗口中，1退出了窗口，所以在队列中把1这个值去掉
//        //7这个位置的最大值就是0(队列最大值)+7=7，再将7放入队列中。就这样依次向后求窗口内最后一个位置的最大值，直到求到最后一位也就是题解

//        //本解决方案的思路就是利用单调栈的方式，将窗口内包含的数字和放入栈中，递减排列，因为要取出的是最大值，然后窗口一步一步的向后移动
//        //在窗口整体向后移动一位后，如果单调栈栈顶最大元素与窗口左侧移出去的那位数字相同，就需要将栈顶元素去掉，然后再加入右侧新加入窗口的数字
//    }


    //标准答案
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int n = Integer.parseInt(sc.nextLine());
            int[] scores = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int k = Integer.parseInt(sc.nextLine());

            System.out.println(getResult(n, scores, k));
        }

        public static int getResult(int n, int[] scores, int k) {
            // 第i个格子，可以从第i-k个格子~第i-1个格子调过来，因此本题滑窗的长度相当于k+1,这里的滑窗包括了第i个格子
            int len = k + 1;

            // dp[i]表示跳到第i个格子能得到的最大分数
            int[] dp = new int[n];
            dp[0] = scores[0];

            // 单调队列（单调递减，队首是滑窗最大值）
            LinkedList<Integer> queue = new LinkedList<>();
            queue.addLast(dp[0]);

            // 初始滑窗的形成过程（即只有新增尾部元素的过程）
            for (int i = 1; i < Math.min(len, n); i++) { // 注意当len > n时, 需要取n, 此时只有滑窗形成过程，没有滑窗移动过程
                // dp[i] = max(dp[0]~dp[i-1]) + scores[i]
                // 即单调队列队首保存的是max(dp[0]~dp[i-1])
                dp[i] = queue.getFirst() + scores[i];

                // 保持单调队列的单调递减性，即如果后入队的dp[i] 大于 队尾元素，则队尾元素出队
                while (queue.size() > 0 && dp[i] > queue.getLast()) {
                    queue.removeLast();
                }

                // 当队尾没有比dp[i]更小的元素后，dp[i]才能入队
                queue.addLast(dp[i]);
            }

            // 滑窗的右移过程（即相较于老滑窗失去一个首元素，新增一个尾元素）
            for (int i = len; i < n; i++) {
                // 如果滑窗失去的元素dp[i - len]，和单调队列的队首元素queue[0]相同，则单调队列需要弹出头部元素
                // 可以理解成如果如果score = [7, 1, 1]，k=1，第一个窗口包括7、8（7+1），在队列中8为第一个元素，当右移后，第一个元素8加上score最后一个元素1最大值为9
                // 如果如果score = [7, -1, -1]，k=1，第一个窗口包括7、6（7-1），在队列中7为第一个元素，当右移后，7这个位置不能跳到score中最后的-1处的，所以要将
                //队列窗口中7这个数字去掉，用第二个数字6（这个位置可以跳到score中最后的-1处的）去加最后的-1，结果为5
                if (dp[i - len] == queue.getFirst()) {
                    queue.removeFirst();
                }

                // 下面逻辑同之前
                dp[i] = queue.getFirst() + scores[i];

                while (queue.size() > 0 && dp[i] > queue.getLast()) {
                    queue.removeLast();
                }

                queue.addLast(dp[i]);
            }

            return dp[n - 1];
        }

}

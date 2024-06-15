import java.util.Arrays;
import java.util.Scanner;

/**
 * 机器人搬砖，一共有 N 堆砖存放在 N 个不同的仓库中，第 i 堆砖中有 bricks[i] 块砖头，要求在 8 小时内搬完。
 * 机器人每小时能搬砖的数量取决于有多少能量格，机器人一个小时中只能在一个仓库中搬砖，机器人的能量格只在这一个小时有效，为使得机器人损耗最小化，应尽量减小每次补充的能量格数。
 * 为了保障在 8 小时内能完成搬砖任务，请计算每小时给机器人充能的最小能量格数。
 * 无需考虑机器人补充能力格的耗时；
 * 无需考虑机器人搬砖的耗时；
 * 机器人每小时补充能量格只在这一个小时中有效；
 *
 * 输入描述：
 * 第一行为一行数字，空格分隔
 *
 * 输出描述：
 * 机器人每小时最少需要充的能量格，若无法完成任务，输出 -1
 */
public class 机器人搬砖 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] bricks = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int minEnergy = 1;
        int maxEnergy = Arrays.stream(bricks).max().getAsInt();
        int ans = -1;

        //二分法求最小能量值
        while (minEnergy<=maxEnergy){
            int middle = (minEnergy+maxEnergy)>>1;

            if(check(middle, bricks)){
                ans = middle;
                maxEnergy=middle-1;

            }else {
                minEnergy=middle+1;
            }
        }
        System.out.println(ans);
    }

    /**
     * 检查给出的能量数是否可以搬完所有仓库
     * @param middle 每次补充的能量数
     * @param bricks 仓库砖的数组
     * @return 是否可以在8次内搬完
     */
    private static boolean check(int middle, int[] bricks) {
        int totalnums =0;

        for(int i=0;i<bricks.length;i++){

            //一个仓库需要搬得次数
            int nums = (bricks[i]/middle)+(bricks[i]%middle>0?1:0);
            totalnums+=nums;

            if(totalnums>8){
                return false;
            }

        }
        return true;
    }
}

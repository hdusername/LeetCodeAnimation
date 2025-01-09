import java.util.Arrays;
import java.util.Scanner;

/**
 * 日志采集是运维系统的的核心组件。日志是按行生成，每行记做一条，由采集系统分批上报。
 * 如果上报太频繁，会对服务端造成压力;
 * 如果上报太晚，会降低用户的体验；
 * 如果一次上报的条数太多，会导致超时失败。
 *
 * 为此，项目组设计了如下的上报策略：
 * 每成功上报一条日志，奖励1分
 * 每条日志每延迟上报1秒，扣1分
 * 积累日志达到100条，必须立即上报
 * 给出日志序列，根据该规则，计算首次上报能获得的最多积分数。
 *
 * 输入描述：
 * 按时序产生的日志条数 T1,T2…Tn，其中
 * 1 ≤ n ≤ 1000
 * 0 ≤ Ti ≤ 100
 *
 * 输出描述：
 * 首次上报最多能获得的积分数
 */
public class 日志采集系统 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inLogs = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int length = inLogs.length;
        int[] logs = new int[length + 1];
        //为了在使用dp[i] = dp[i-1]+logs[i]构造动态规划公式时不会造成数组越界，所以给数组增加一个长度，且值为0不影响结果
        for(int i=1; i<logs.length; i++){
            logs[i] = inLogs[i-1];
        }
        //dp[i]这个结果也要相应增加长度
        int[] dp = new int[length+1];
        int[] delay = new int[length+1];
        int score = 0;

        for(int i=1; i<logs.length; i++){
            dp[i] = dp[i-1]+logs[i];
            delay[i] = dp[i-1] + delay[i-1];

            int tmpScore = (dp[i]>100?100:dp[i]) - delay[i];
            score = Math.max(score, tmpScore);

            if(dp[i]>100){
                break;
            }

        }

        System.out.println(score);
    }
}

import java.util.Scanner;

/**
 * 小明每周上班都会拿到自己的工作清单，工作清单内包含 n 项工作，每项工作都有对应的耗时时间（单位 h）和报酬，工作的总报酬为所有已完成工作的报酬之和。
 * 那么请你帮小明安排一下工作，保证小明在指定的工作时间内工作收入最大化。
 *
 * 输入描述：
 * 输入的第一行为两个正整数 T，n
 * T 代表工作时长（单位 h， 0 < T < 1000000），
 * 接下来是 n 行，每行包含两个整数 t，w
 * t 代表该工作消耗的时长（单位 h， t > 0）
 * w 代表该项工作的报酬
 *
 * 输出描述：
 * 输出小明指定工作时长内工作可获得的最大报酬。
 */
public class 工作安排 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int n = scanner.nextInt();

        //报酬
        int[] ws = new int[n+1];

        //每项工作耗费的时间
        int[] ts = new int[n+1];

        int[][] dp = new int[n+1][T+1];

        for(int i=1;i<n+1; i++){
            ts[i]=scanner.nextInt();
            ws[i] = scanner.nextInt();
        }

        for(int i=1;i<n+1; i++){

            for(int j=1; j<T+1; j++){
                //j代表拥有的时长
                //ts[i]表示每项工作耗费的时长
                if(j<ts[i]){
                    //时长不够放不进去的情况下
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-ts[i]] + ws[i]);
                }
            }
        }
        System.out.println(dp[n][T]);
    }
}

import java.util.Scanner;

/**
 * 近些年来，我国防沙治沙取得显著成果。
 * 某沙漠新种植 N 棵胡杨（编号1-N），排成一排。一个月后，有 M 棵胡杨未能成活。
 * 现可补种胡杨 K 棵，请问如何补种（只能补种，不能新种），可以得到最多的连续胡杨树？
 *
 * 输入描述：
 * N 总种植数量
 * 1 ≤ N ≤ 100000
 * M 未成活胡杨数量，M 个空格分隔的数，按编号从小到大排列
 * 1 ≤ M ≤ N
 * K 最多可以补种的数量，
 * 0 ≤ K ≤ M
 *
 * 输出描述：
 * 最多的连续胡杨棵树
 */
public class 补种未成活胡杨 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //总种植数量
        int N = scanner.nextInt();
        //未成活数量
        int M = scanner.nextInt();
        //未成活树的数组
        int[] ms = new int[M];
        for(int i=0; i<M; i++){
            ms[i] = scanner.nextInt();
        }
        //补种树的数量
        int K = scanner.nextInt();

        int maxRange = 0;

        if(K==M){
            System.out.println(N);
        }else {
            //i最大未M-K的原因是，i如果为M-K，那么后面还有K个空位去补种树，再比M-K大的话，就留不出K个空位补树了
            //也就是说遍历的是未成活树的数组，在这个数组种进行补种树
            for (int i = 0; i <= M - K; i++) {
                if (i == 0) {
                    //说明补了第一个缺少的树，可以和总种植树的0位置的树连上
                    //范围就取i+k(这个位置树是第一个无法补到的位置)，减去1是为了取这个位置的前一个位置，表示范围
                    int range = ms[i+K] - 1;
                    maxRange = Math.max(maxRange, range);

                } else if (i == M - K) {
                    //说明从后向前共补充了K颗树
                    int range = N-ms[i-1];
                    maxRange = Math.max(maxRange, range);
                }else {
                    //说明是在中间位置补充了K颗树
                    int range = ms[i+K]-ms[i-1]-1;
                    maxRange = Math.max(maxRange, range);

                }
            }

            System.out.println(maxRange);
        }

    }
}



import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 一个有 N 个选手参加比赛，选手编号为1 ~ N（3 ≤ N ≤ 100），有 M（3 ≤ M ≤ 10）个评委对选手进行打分。
 * 打分规则为每个评委对选手打分，最高分10分，最低分1分。
 * 请计算得分最多的3位选手的编号。
 * 如果得分相同，则得分高分值最多的选手排名靠前（10分数量相同，则比较9分的数量，以此类推，用例中不会出现多个选手得分完全相同的情况）。
 *
 * 输入描述：
 * 第一行为半角逗号分割的两个正整数，第一个数字表示 M（3 ≤ M ≤ 10）个评委，第二个数字表示 N（3 ≤ N ≤ 100）个选手。
 * 第 2 到 M+1 行是半角逗号分割的整数序列，表示评委为每个选手的打分，0号下标数字表示1号选手分数，1号下标数字表示2号选手分数，依次类推。
 *
 * 输出描述：
 * 选手前3名的编号。
 *
 * 若输入为异常，输出-1，如 M、N、打分不在范围内。
 */
public class 选手前3名的编号 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("[,\n]");
        //m个评委
        int m = scanner.nextInt();
        //n个选手
        int n = scanner.nextInt();

        if(!(m>=3 && m<=10 && n>=3 && n<=100)){
            System.out.println(-1);
            return;
        }

        //n个选手每种分数的数量，n+1是因为1号选手是索引0表示的，11个长度中索引1-10表示1-10分
        int[][] scores = new int[n+1][11];
        int[] sumScores = new int[n+1];

        for(int i=0; i<m; i++){
            for(int j=1; j<=n; j++){
                int score = scanner.nextInt();
                if(!(score>=1 && score<=10)){
                    System.out.println(-1);
                    return;
                }
                //选手对应的分数++
                scores[j][score]++;
                sumScores[j]+=score;
            }
        }

        //把选手编号放入list中， 方便后续排序
        ArrayList<Integer> list = new ArrayList();
        for(int i=1; i<=n; i++){
            list.add(i);
        }
        StringJoiner stringJoiner = new StringJoiner(",");

        list.stream().sorted((a,b)->{
            //如果总分不相等，按照总分从大到小排序
            if(sumScores[a]!=sumScores[b]){
                return sumScores[b]-sumScores[a];
            }else {
                for(int i=10; i>=0; i--){
                    if(scores[a][i] != scores[b][i]){
                        return scores[b][i] - scores[a][i] ;
                    }
                }
            }
            return 0;
        }).limit(3).forEach(a->stringJoiner.add(a+""));

        System.out.println(stringJoiner);
    }
}

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 马是象棋（包括中国象棋和国际象棋）中的棋子，走法是每步直一格再斜一格，即先横着或者直者走一格，然后再斜着走一个对角线，可进可退，可越过河界，俗称"马走日"字。
 * 给定 m 行 n 列的棋盘（网格图），棋盘上只有棋子象棋中的棋子“马”，并且每个棋子有等级之分，等级为 k 的马可以跳 1~k 步（走的方式与象棋中“马”的规则一样，不可以超出棋盘位置），
 * 问是否能将所有马跳到同一位置，如果存在，输出最少需要的总步数（每匹马的步数相加），不存在则输出-1。
 * 注：允许不同的马在跳的过程中跳到同一位置，坐标为（x,y）的马跳一次可以跳到的坐标为：(x+1, y+2)，(x+1, y-2)，(x+2, y+1)，(x+2, y-1)，
 * (x-1, y+2)，(x-1, y-2)，(x-2, y+1)，(x-2, y-1)，的格点上，但是不可以超出棋盘范围。
 *
 * 输入描述：
 * 第一行输入m，n，代表 m 行 n 列的网格图棋盘（1 ≤ m, n ≤ 25）
 * 接下来输入 m 行 n 列的网格图棋盘，如果第 i 行，第 j 列的元素为 "." ，代表此格点没有棋子，如果为数字 k（1 ≤ k ≤ 9），代表此格点存在等级为 k 的“马”
 *
 * 输出描述：
 * 输出最少需要的总步数（每匹马的步数相加），不存在则输出-1。
 *
 * 用例输入：
 * 3 2
 * ..
 * 2.
 * ..
 *
 * 输出：
 * 0
 *
 * 解释：
 * 只有一匹马，不需要动
 */
public class 跳马 {
    static String[][] boards;

    static int[][] points = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};

    static int m;

    static int n;

    static int[][] stepSum;
    static HashSet<Integer> arrivaled;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputs = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //行
        m = inputs[0];
        //列
        n = inputs[1];

        //棋盘
        boards = new String[m][n];

        //每匹马到达对应点的步数和
        stepSum = new int[m][n];

        //所有马都可以到达的公共节点集合
        arrivaled = new HashSet<>();

        for(int i=0; i<m; i++){
            boards[i] = scanner.nextLine().split("");
            for(int j=0; j<n; j++){

                //初始化所有节点所有马都可以到达
                arrivaled.add(i * n + j);

            }
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(!".".equals(boards[i][j])) {
                    dfs(i, j, Integer.parseInt(boards[i][j]));
                }
            }
        }

        if(arrivaled.size()==0){
            System.out.println(-1);
            return;
        }
        int ans = Integer.MAX_VALUE;
        for(int pos : arrivaled){
            int x = pos/n;
            int y = pos%n;

            ans = Math.min(ans, stepSum[x][y]);
        }
        System.out.println(ans);
    }

    /**
     * 此方法寻找x,y位置处的马经过step步后都可以到达哪些位置，与arrivaled取交集，找出马到达的公共位置
     * @param x 当前处在的x坐标
     * @param y 当前所处的y坐标
     * @param k 可以走几步
     */
    private static void dfs(int x, int y, int k){

        LinkedList<int[]> queue = new LinkedList<>();
        //马到达初始位置x,y需要0步
        queue.add(new int[]{x, y, 0});

        //记录该马可以访问x, y的位置
        HashSet<Integer> vis = new HashSet<>();
        vis.add(x*n+y);

        //int[] arrivaled_temp = new int[m*n];
        while (queue.size()>0 && k>0) {

            LinkedList<int[]> newQueue = new LinkedList<>();

            //按层遍历，这是同一层数据
            for(int[] poll : queue) {
                int cx = poll[0];
                int cy = poll[1];
                int cStep = poll[2];

                for (int[] point : points) {
                    int newx = point[0] + cx;
                    int newy = point[1] + cy;

                    //与传统坐标轴是反的，传统x轴作为y轴，传统y轴作为x轴，因为x代表的是行，y代表的是列
                    if (newx < 0 || newx >= m || newy < 0 || newy >= n || vis.contains(newx*n+newy)) {
                        //到达不了走出的位置，或者当前马之前已经到过此位置了，就不能再走对应步数了，需要跳出继续判断下一个走的位置
                        continue;
                    }

                    newQueue.add(new int[]{newx, newy, cStep + 1});
                    //之前马到达newx,newy位置的步数和加上当前马到达此位置的步数
                    stepSum[newx][newy] += cStep + 1;

                    vis.add(newx * n + newy);

                }

            }
            queue = newQueue;
            k--;
        }

        //取到达节点的交集
        arrivaled.retainAll(vis);
    }
}

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/**
 * 周末小明准备去爬山锻炼，0代表平地，山的高度使用1到9来表示，小明每次爬山或下山高度只能相差k及k以内，每次只能上下左右一个方向上移动一格，小明从左上角(0,0)位置出发
 *
 * 输入描述：
 * 第一行输入m n k(空格分隔)
 *  代表m*n的二维山地图，k为小明每次爬山或下山高度差的最大值，
 * 然后接下来输入山地图，一共m行n列，均以空格分隔。取值范围：
 *  0 < m ≤ 500
 *  0< n ≤ 500
 *  0 < k < 5
 *
 * 输出描述：
 * 请问小明能爬到的最高峰多高，到该最高峰的最短步数，输出以空格分隔。
 * 同高度的山峰输出较短步数。
 * 如果没有可以爬的山峰，则高度和步数都返回0。
 *
 * 备注：
 * 所有用例输入均为正确格式，且在取值范围内，考生不需要考虑不合法的输入格式。
 *
 * 用例输入：
 * 5 4 1
 * 0 1 2 0
 * 1 0 0 0
 * 1 0 1 2
 * 1 3 1 0
 * 0 0 0 9
 *
 * 输出：
 * 2 2
 *
 * 解释：
 * 根据山地图可知，能爬到的最高峰在(0,2)位置，高度为2，最短路径为(0,0)-(0,1)-(0,2)，最短步数为2。
 *
 */
public class 周末爬山 {

    static int[][] offsets = {{0, 1},{0, -1},{-1, 0},{1, 0}};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] matrix = new int[m][n];
        boolean[][] used = new boolean[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                matrix[i][j] = scanner.nextInt();
            }
        }
        int maxHeight = matrix[0][0];
        int minStep = 0;


        LinkedList<int[]> queue = new LinkedList<>();
        //先将（0,0）位置放入,数组中元素表示x坐标，y坐标，走到此点的步数
        queue.add(new int[]{0, 0, 0});
        used[0][0] = true;

        while (queue.size()>0){
            //装入下一层遍历的元素
            LinkedList<int[]> newLinkedList = new LinkedList<>();

            //一层一层的遍历，方便统计步数
            for(int[] polled : queue) {
                int poll_x = polled[0];
                int poll_y = polled[1];
                int poll_step = polled[2];
                int poll_height = matrix[poll_x][poll_y];

                for(int[] offset : offsets){
                    int new_x = poll_x+offset[0];
                    int new_y = poll_y+offset[1];


                    if(new_x<0 || new_x>=m || new_y<0 || new_y>=n || used[new_x][new_y]){
                        continue;
                    }

                    int new_height = matrix[new_x][new_y];

                    //这里既可以上山也可以下山
                    if(Math.abs(new_height-poll_height)<=k){
                        //这个一定要写在这里，表示攀登高度在k以内的才可以标记为已经攀登过了，如下例子：
                        //5 4 2
                        //0 1 2 0
                        //1 0 0 0
                        //1 0 1 2
                        //1 3 1 0
                        //0 0 0 9
                        //路线为0，1，0，0，3这样走的话0到3是不满足在k以内的，但是如果把 used[new_x][new_y]=true;放在if(new_height-poll_height<=k){这个if语句
                        //前面，就会导致即使没攀登上去也会标记为已经到过这个位置，导致无法从0，1，1，1，3到达这个位置
                        used[new_x][new_y]=true;
                        if(new_height-poll_height>0){
                            //说明在提升高度
                            if(new_height>maxHeight){
                                maxHeight = new_height;
                                minStep = poll_step+1;
                            } else if (new_height == maxHeight) {
                                minStep = Math.min(minStep, poll_step+1);
                            }

                        }
                        newLinkedList.add(new int[]{new_x, new_y, poll_step+1});
                    }

                }
            }

            queue = newLinkedList;
        }

        System.out.println(minStep==0?0+" "+0:maxHeight+" "+minStep);
    }
}

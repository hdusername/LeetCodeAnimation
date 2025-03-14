import java.util.Arrays;
import java.util.Scanner;

/**
 * 学校组织活动，将学生排成一个矩形方阵。
 * 请在矩形方阵中找到最大的位置相连的男生数量。这个相连位置在一个直线上，方向可以是水平的，垂直的，成对角线的或者呈反对角线的。
 *
 * 注：学生个数不会超过10000
 *
 * 输入描述：
 * 输入的第一行为矩阵的行数和列数，
 * 接下来的 n 行为矩阵元素，元素间用 "," 分隔。
 *
 * 输出描述：
 * 输出一个整数，表示矩阵中最长的位置相连的男生个数。
 *
 * 用例输入：
 * 3,4
 * F,M,M,F
 * F,M,M,F
 * F,F,F,M
 *
 * 输出：
 * 3
 *
 *
 */
public class 学生方阵 {

    static String[][] matrix;
    //不向后进行遍历,这几个方向是从上到下，从左到右，也就是说只走右、右下、下、左下几个位置，其他位置在遍历之前的元素时肯定已经遍历过了
    static int[][] offsets = {{1, -1},{0, 1},{1, 1},{1, 0}};
    static int m;
    static int n;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] mnArray = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        //m行
        m = mnArray[0];
        //n列
        n = mnArray[1];
        int maxLen = 0;
        matrix = new String[m][n];
        for(int i=0; i<m; i++){
            matrix[i] = scanner.nextLine().split(",");
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int len = bfs(i, j);
                maxLen = Math.max(maxLen, len);
            }
        }

        System.out.println(maxLen);
    }

    /**
     * 这是求解在一条直线上广度遍历的方法
     * @param i
     * @param j
     * @return
     */
    private static int bfs(int i, int j) {
        int maxStep = 0;
        if("M".equals(matrix[i][j])){


            for(int[] offset : offsets){

                int old_x = i-offset[0];
                int old_y = j-offset[1];

                if(old_x>=0 && old_x<m && old_y>=0 && old_y<n && "M".equals(matrix[old_x][old_y])){
                    //说明当前位置与offset相反的方向是M，那么相反方向那个位置在经过此步骤时肯定已经统计过了，所以在这里无需统计，直接跳过
                    continue;
                }
                int step = 1;
                int new_x = i+offset[0];
                int new_y = j+offset[1];

                while(new_x>=0 && new_x<m && new_y>=0 && new_y<n && "M".equals(matrix[new_x][new_y])){
                    step++;
                    new_x += offset[0];
                    new_y += offset[1];
                }
                maxStep = Math.max(maxStep, step);

            }
        }
        return maxStep;
    }
}

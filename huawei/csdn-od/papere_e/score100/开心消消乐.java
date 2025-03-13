import java.util.ArrayDeque;
import java.util.Scanner;

/**

 * 给定一个 N 行 M 列的二维矩阵，矩阵中每个位置的数字取值为 0 或 1，矩阵示例如：
 * 1 1 0 0
 * 0 0 0 1
 * 0 0 1 1
 * 1 1 1 1
 * 现需要将矩阵中所有的 1 进行反转为 0，规则如下：
 * 1) 当点击一个 1 时，该 1 被反转为 0，同时相邻的上、下、左、右，以及左上、左下、右上、右下 8 个方向的 1 （如果存在 1）均会自动反转为 0；
 * 2) 进一步地，一个位置上的 1 被反转为 0 时，与其相邻的 8 个方向的 1 （如果存在 1）均会自动反转为 0；
 * 按照上述规则示例中的矩阵只最少需要点击 2 次后，所有均值 0 。请问，给定一个矩阵，最少需要点击几次后，所有数字均为 0？
 *
 * 输入描述
 * 第一行输入两个整数，分别表示矩阵的行数 N 和列数 M，取值范围均为 [1,100] 接下来 N 行表示矩阵的初始值，每行均为 M 个数，取值范围 [0,1]。
 *
 * 输出描述
 * 输出一个整数，表示最少需要点击的次数。
 *
 * 示例1
 * 输入
 * 3 3
 * 1 0 1
 * 0 1 0
 * 1 0 1
 *
 * 输出	1
 * 说明	上述样例中，四个角上的 1 均在中间的 1 的相邻 8 个方向上，因此只需要点击一次即可。
 *
 * 示例2
 * 输入
 * 4 4
 * 1 1 0 0
 * 1 0 0 0
 * 0 0 0 1
 * 0 0 1 1
 *
 * 输出	2
 * 说明	在上述 4 * 4 的矩阵中，只需要点击 2 次（左上角 和 右下角），即可将所有的 1 消除。
 */
public class 开心消消乐 {

    static int n, m;
    static int[][] matrix;
    // 八个方向的偏移量
    static int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        matrix = new int[n][m];
        int ans = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                matrix[i][j] = scanner.nextInt();
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j]==1){
                    ans++;
                    bfs(i, j);
                }
            }
        }
        System.out.println(ans);
    }

    private static void bfs(int x, int y){
        ArrayDeque<Integer> visited = new ArrayDeque<>();
        //m是总列数，x*m+y表示到达总列数之后，继续向后排，bfs这种解法的横纵坐标轴为
        //--------->y轴
        //|
        //|
        //|
        //|
        //x轴
        visited.add(x*m+y);
        matrix[x][y]=0;
        while (!visited.isEmpty()){
            int value = visited.removeFirst();
            int curX = value/m;
            int curY = value%m;

            for(int[] offset : offsets){
                int newX = curX+offset[0];
                int newY = curY+offset[1];
                if(newX>=0 && newX<n && newY>=0 && newY<m && matrix[newX][newY]==1){
                    //说明消除了这个位置的1，这个位置周围的8个位置也要继续查看是否有1
                    matrix[newX][newY]=0;
                    visited.add(newX*m + newY);
                }
            }
        }
    }
}

//标准答案
//import java.util.*;
//
//public class Main {
//    static int n, m;
//    static int[][] matrix;
//
//    // 八个方向的偏移量
//    static int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        n = sc.nextInt();
//        m = sc.nextInt();
//
//        matrix = new int[n][m];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                matrix[i][j] = sc.nextInt();
//            }
//        }
//
//        int count = 0;
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (matrix[i][j] == 1) {
//                    // 点击(i,j)位置1
//                    count++;
//                    // 扩散
//                    bfs(i, j);
//                }
//            }
//        }
//
//        System.out.println(count);
//    }
//
//    public static void bfs(int i, int j) {
//        ArrayDeque<Integer> queue = new ArrayDeque<>();
//        queue.add(i * m + j);
//        matrix[i][j] = 0;
//
//        while (!queue.isEmpty()) {
//            int pos = queue.removeFirst();
//
//            int x = pos / m;
//            int y = pos % m;
//
//            // 八个方向相邻1进行反转（变0）
//            for (int[] offset : offsets) {
//                int newX = x + offset[0];
//                int newY = y + offset[1];
//
//                if (newX >= 0 && newX < n && newY >= 0 && newY < m && matrix[newX][newY] == 1) {
//                    queue.add(newX * m + newY);
//                    matrix[newX][newY] = 0;
//                }
//            }
//        }
//    }
//}

//标准答案2
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//
//        int[][] matrix = new int[n][m];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                matrix[i][j] = sc.nextInt();
//            }
//        }
//
//        UnionFindSet ufs = new UnionFindSet(n * m);
//
//        // 八个方向的偏移量
//        int[][] offsets = {
//                {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
//        };
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (matrix[i][j] != 1) {
//                    // 去除0对应的孤岛
//                    ufs.count--;
//                    continue;
//                }
//
//                // 八个方向相邻1进行合并
//                for (int[] offset : offsets) {
//                    int newI = i + offset[0];
//                    int newJ = j + offset[1];
//
//                    if (newI >= 0 && newI < n && newJ >= 0 && newJ < m && matrix[newI][newJ] == 1) {
//                        ufs.union(i * m + j, newI * m + newJ);
//                    }
//                }
//            }
//        }
//
//        System.out.println(ufs.count);
//    }
//}
//
//// 并查集
//class UnionFindSet {
//    int[] fa;
//    int count;
//
//    public UnionFindSet(int n) {
//        this.fa = new int[n];
//        this.count = n;
//        for (int i = 0; i < n; i++) this.fa[i] = i;
//    }
//
//    public int find(int x) {
//        if (x != this.fa[x]) {
//            return (this.fa[x] = this.find(this.fa[x]));
//        }
//        return x;
//    }
//
//    public void union(int x, int y) {
//        int x_fa = this.find(x);
//        int y_fa = this.find(y);
//
//        if (x_fa != y_fa) {
//            this.fa[y_fa] = x_fa;
//            this.count--;
//        }
//    }
//}
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 给一个字符串和一个二维字符数组，如果该字符串存在于该数组中，则按字符串的字符顺序输出字符串
 * 每个字符所在单元格的位置下标字符串，如果找不到返回字符串“N”。
 * 1.需要按照字符串的字符组成顺序搜索，且搜索到的位置必须是相邻单元格，其中“相邻单元格”是指那些水平相邻或垂直相邻的单元格。
 * 2.同一个单元格内的字母不允许被重复使用。
 * 3.假定在数组中最多只存在一个可能的匹配。
 *
 * 输入描述：
 * 第1行为一个数字N指示二维数组在后续输入所占的行数。
 * 第2行到第N+1行输入为一个二维大写字符数组，每行字符用半角,分割。
 * 第N+2行为待查找的字符串，由大写字符组成。
 * 二维数组的大小为N*N，0<N<=100。
 * 单词长度K，0<K<1000。
 *
 * 输出描述：
 * 输出一个位置下标字符串，拼接格式为：第1个字符行下标+”,”+第1个字符列下标+”,”+第2个字符行下标+”,”+第2个字符列下标… +”,”+第N个字符行下标+”,”+第N个字符列下标。
 *
 * 用例输入：
 *  4
 *  A,C,C,F
 *  C,D,E,D
 *  B,E,S,S
 *  F,E,C,A
 *  ACCESS
 *
 * 输出：
 * 0,0,0,1,0,2,1,2,2,2,2,3
 *
 * 解释：
 * ACCESS分别对应二维数组的[0,0] [0,1] [0,2] [1,2] [2,2] [2,3]下标位置。
 */
public class 找单词 {
//    static int n;
//    static boolean[][] used;
//    static int[][] offsets = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        n = Integer.parseInt(scanner.nextLine());
//        String[][] matrix = new String[n][n];
//        used = new boolean[n][n];
//
//        for(int i=0; i<n; i++){
//            matrix[i] = scanner.nextLine().split(",");
//        }
//
//        String targerStr = scanner.nextLine();
//
//        StringBuffer stringBuffer = new StringBuffer();
//
//        used[0][0]=true;
//        if(dfs(matrix, 0, 0, targerStr, 0, stringBuffer)) {
//
//            System.out.println(String.join(",", stringBuffer));
//        }
//    }
//
//    private static boolean dfs(String[][] matrix, int x, int y, String targetStr, int targetStrIdx, StringBuffer stringJoiner){
//        if(targetStr.length() == targetStrIdx){
//            //深度遍历结束，已经找到答案
//            return true;
//        }
//
//
//        if(targetStr.substring(targetStrIdx, targetStrIdx+1).equals(matrix[x][y])){
//            stringJoiner.append(x+""+y);
//            for(int[] offset : offsets){
//                int offset_x = offset[0]+x;
//                int offset_y = offset[1]+y;
//                if(offset_x<0 || offset_x>=n || offset_y<0 || offset_y>=n || used[offset_x][offset_y]){
//                    continue;
//                }
//                used[offset_x][offset_y] = true;
//                if(dfs(matrix, offset_x, offset_y, targetStr, targetStrIdx+1, stringJoiner)){
//                    return true;
//                }else {
//                    if(stringJoiner.length()>1){
//                        stringJoiner.delete(stringJoiner.length()-2, stringJoiner.length());
//                    }
//                };
//            }
//        }
//
//        return false;
//
//    }

    //以下为标准答案给出的，是深度搜索的另一种写法
        static int n;
        static char[][] matrix;
        static String word;

        static boolean[][] used;
        static LinkedList<String> path = new LinkedList<>();

        public static void main(String[] args) {
            // 将输入分隔符改为“,”和换行
            Scanner sc = new Scanner(System.in).useDelimiter("[,\n]");

            n = sc.nextInt();

            matrix = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = sc.next().charAt(0);
                }
            }

            word = sc.next();

            used = new boolean[n][n];

            System.out.println(getResult());
        }

        public static String getResult() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (dfs(i, j, 0)) {
                        StringJoiner sj = new StringJoiner(",");
                        for (String pos : path) sj.add(pos);
                        return sj.toString();
                    }
                }
            }
            return "N";
        }

        public static boolean dfs(int i, int j, int k) {
            if (i < 0 || i >= n || j < 0 || j >= n || word.charAt(k) != matrix[i][j] || used[i][j]) {
                return false;
            }

            path.add(i + "," + j);

            if (path.size() == word.length()) {
                return true;
            }

            used[i][j] = true;

            boolean res =
                    dfs(i - 1, j, k + 1)
                            || dfs(i + 1, j, k + 1)
                            || dfs(i, j - 1, k + 1)
                            || dfs(i, j + 1, k + 1);

            if (!res) {
                used[i][j] = false;
                path.removeLast();
            }

            return res;
        }

}

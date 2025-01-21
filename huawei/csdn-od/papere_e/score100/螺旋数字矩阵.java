import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 疫情期间，小明隔离在家，百无聊赖，在纸上写数字玩。他发明了一种写法：
 * 小明对这个矩阵有些要求：
 * 每行数字的个数一样多
 * 列的数量尽可能少
 * 填充数字时优先填充外部
 * 数字不够时，使用单个 * 号占位
 *
 * 输入描述：
 * 两个整数，空格隔开，依次表示 n、m
 *
 * 输出描述：
 * 符合要求的唯一矩阵
 */
public class 螺旋数字矩阵 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //行
        int m = scanner.nextInt();
        //列 这里一定要乘以1.0，是为了保留小数，向上取整，不然整数除以整数会得到一个向下取整的数
        int w = (int) Math.ceil(n*1.0/m);

        int[][] matrix = new int[m][w];

        //行的坐标
        int x =0;
        //列的坐标
        int y =0;
        int num = 1;

        while(num<=n){
            while (num<=n && y<w && matrix[x][y]==0){
                matrix[x][y++] = num++;
            }
            //到矩阵右边界了
            x++;
            y--;

            while (num<=n && x<m && matrix[x][y]==0){
                matrix[x++][y] = num++;
            }
            //到矩阵下边界了
            x--;
            y--;

            while (num<=n && y>=0 && matrix[x][y]==0){
                matrix[x][y--] = num++;
            }
            //到矩阵左边界了
            x--;
            y++;

            while (num<=n && x>=0 && matrix[x][y]==0){
                matrix[x--][y] = num++;
            }
            //到矩阵上边界了
            x++;
            y++;
        }

        for(int i=0; i<matrix.length; i++){
            StringJoiner stringJoiner = new StringJoiner(" ");
            for(int j=0; j<matrix[i].length; j++){
                if(matrix[i][j]==0){
                    stringJoiner.add("*");
                }else {
                    stringJoiner.add(matrix[i][j] + "");
                }
            }
            System.out.println(stringJoiner);
        }
    }
}

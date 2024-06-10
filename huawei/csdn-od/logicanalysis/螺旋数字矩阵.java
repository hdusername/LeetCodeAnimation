import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 疫情期间，小明隔离在家，百无聊赖，在纸上写数字玩。他发明了一种写法：
 * 给出数字个数 n （0 < n ≤ 999）和行数 m（0 < m ≤ 999），从左上角的 1 开始，按照顺时针螺旋向内写方式，依次写出2,3,....,n，最终形成一个 m 行矩阵。
 * 小明对这个矩阵有些要求：
 * 每行数字的个数一样多
 * 列的数量尽可能少
 * 填充数字时优先填充外部
 * 数字不够时，使用单个 * 号占位
 */
public class 螺旋数字矩阵 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //获取矩阵值的总数
        int count = scanner.nextInt();
        //获取行数
        int row = scanner.nextInt();
        //计算列数
        int column = (int)Math.ceil(count * 1.0 / row);

        int[][] ints = new int[row][column];
        //为行列赋初始值
        int x = 0;
        int y = 0;
        //记录是第几圈，因为在while循环里有这个判断ints[row][column]==0所以不用记录是第几圈了
        //int circleNum = 0;
        //当前遍历到的数字
        int currCount = 1;

        while(currCount<=count){
            while (y<column && ints[x][y]==0 && currCount<=count){
                ints[x][y++]=currCount++;
            }
            x++;
            y--;
            while (x<row && ints[x][y]==0 && currCount<=count){
                ints[x++][y]=currCount++;
            }
            x--;
            y--;
            while (y>=0 && ints[x][y]==0 && currCount<=count){
                ints[x][y--]=currCount++;
            }
            x--;
            y++;
            while (x>=0 && ints[x][y]==0 && currCount<=count){
                ints[x--][y]=currCount++;
            }
            x++;
            y++;
        }

        for(int i =0;i<row;i++){
            StringJoiner stringJoiner = new StringJoiner(" ");
            for (int j=0;j<column;j++){
                if(ints[i][j]==0){
                    stringJoiner.add("*");

                }else {
                    stringJoiner.add(ints[i][j]+"");

                }
            }
            System.out.println(stringJoiner);
        }

    }
}

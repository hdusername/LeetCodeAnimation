import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 输入 N 个互不相同的二维整数坐标;求这 N 个坐标可以构成的正方形数量;内积为零的的两个向量垂直;
 *
 * 输入描述:
 * 第一行输入为 N;N 代表坐标数量;N 为正整数
 * N ≤ 100
 * 之后的 N 行输入为坐标 x y 以空格分隔;x,y 为整数
 * -10 ≤ x, y ≤ 10
 *
 * 输出描述:
 * 输出可以构成的正方形数量
 *
 */
public class 构成正方形的数量 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //保存正方型的点，用set是为了后续好进行比较，有contains方法
        HashSet<String> pointsSet = new HashSet<>();


        //保存正方形的数量
        int quarNum = 0;

        int pointsNum = Integer.parseInt(scanner.nextLine());
        String[] pointArray = new String[pointsNum];
        for(int i=0;i<pointsNum;i++){
            String s = scanner.nextLine();
            pointsSet.add(s);
            pointArray[i]=s;
        }

        for(int i = 0;i<pointsNum;i++){
            int[] next1 = Arrays.stream(pointArray[i].split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = next1[0];
            int y1 = next1[1];

            for(int j = i+1;j<pointsNum;j++){
                int[] next2 = Arrays.stream(pointArray[j].split(" ")).mapToInt(Integer::parseInt).toArray();
                int x2 = next2[0];
                int y2 = next2[1];

                int x3 = x1-(y1-y2);
                int y3 = y1+(x1-x2);
                int x4 = x2-(y1-y2);
                int y4 = y2+(x1-x2);
                if(pointsSet.contains(x3+" "+y3)&&pointsSet.contains(x4+" "+y4)){
                    quarNum++;
                }
                int x5 = x1+(y1-y2);
                int y5 = y1-(x1-x2);
                int x6 = x2+(y1-y2);
                int y6 = y2-(x1-x2);
                if(pointsSet.contains(x5+" "+y5)&&pointsSet.contains(x6+" "+y6)){
                    quarNum++;
                }
            }

        }
        System.out.println(quarNum/4);
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 从前有个村庄，村民们喜欢在各种田地上插上小旗子，旗子上标识了各种不同的数字
 * 某天集体村民决定将覆盖相同数字的最小矩阵形的土地分配给村里做出巨大贡献的村民，请问此次分配土地，做出贡献的村民中最大会分配多大面积?
 * 输入描述
 * 第一行输入 m 和 n，m 代表村子的土地的长，n 代表土地的宽
 * 第二行开始输入地图上的具体标识
 * 输出描述
 * 此次分配土地，做出贡献的村民种最大会分配多大面积
 */
public class 分配土地 {
    static class React{
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;

        private void setRow(int row){
            xMin=Math.min(row, xMin);
            xMax=Math.max(row, xMax);
        }

        private void setColumn(int column){
            yMin=Math.min(column, yMin);
            yMax=Math.max(column, yMax);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        //存放插旗区域的所有坐标
        HashMap<Integer, React> reacts = new HashMap();

        int maxReact = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int flagVal = scanner.nextInt();

                //只有插了旗子才记录坐标
                if(flagVal > 0) {
                    reacts.putIfAbsent(flagVal, new React());
                    reacts.get(flagVal).setRow(i);
                    reacts.get(flagVal).setColumn(j);
                }

            }
        }
        for(React react : reacts.values()){
            int x = react.xMax - react.xMin+1;
            int y = react.yMax - react.yMin+1;
            maxReact = Math.max(x*y,maxReact);
        }

        System.out.println(maxReact);
    }
}

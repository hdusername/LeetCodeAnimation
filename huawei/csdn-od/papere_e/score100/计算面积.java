import java.util.Scanner;

/**
 * 绘图机器的绘图笔初始位置在原点(0,0)机器启动后按照以下规则来进行绘制直线。
 * 1. 尝试沿着横线坐标正向绘制直线直到给定的终点E
 * 2. 期间可以通过指令在纵坐标轴方向进行偏移，offsetY为正数表示正向偏移,为负数表示负向偏移
 * 给定的横坐标终点值E 以及若干条绘制指令，
 * 请计算绘制的直线和横坐标轴以及x=E的直线组成的图形面积。
 *
 * 输入描述：
 * 首行为两个整数 N 和 E
 * 表示有N条指令,机器运行的横坐标终点值E
 * 接下来N行 每行两个整数表示一条绘制指令
 * 用例保证横坐标x以递增排序的方式出现
 * 且不会出现相同横坐标x
 *
 * 取值范围
 * 0<N<=10000
 * 0<=x<=E<=20000
 * -10000<=offsetY<=10000
 *
 * 输出描述：
 * 一个整数表示计算得到的面积 用例保证结果范围在0到4294967295之内。
 */
public class 计算面积 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int instructionNum = scanner.nextInt();

        int endOffset_x= scanner.nextInt();

        //坐标中输入的是offsety的值，需要定义一个y真正值的变量
        int y = 0;
        int ans = 0;

        //多定义一个位置是为了存最后一个点的
        int[][] offsets = new int[instructionNum+1][2];

        //保存第一个输入的点
        offsets[0][0]=scanner.nextInt();
        offsets[0][1]=scanner.nextInt();

        for(int i=1;i<instructionNum+1;i++){

            if(i==instructionNum){
                offsets[i][0] = endOffset_x;

            }else {
                offsets[i][0] = scanner.nextInt();
                offsets[i][1] = scanner.nextInt();

            }
            y+=offsets[i-1][1];

            int offset_x = offsets[i][0]-offsets[i-1][0];

            ans+=Math.abs(offset_x*y);
        }

        System.out.println(ans);
    }
}

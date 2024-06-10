import java.util.Arrays;
import java.util.Scanner;

/**
 * 在一颗树中，每个节点代表一个家庭成员，节点的数字表示其个人的财富值，一个节点及其直接相连的子节点被定义为一个小家庭。
 * 现给你一颗树，请计算出最富裕的小家庭的财富和。
 *输入描述：
 * 第一行为一个数 N，表示成员总数，成员编号 1~N。1 ≤ N ≤ 1000
 * 第二行为 N 个空格分隔的数，表示编号 1~N 的成员的财富值。0 ≤ 财富值 ≤ 1000000
 * 接下来 N -1 行，每行两个空格分隔的整数（N1, N2），表示 N1 是 N2 的父节点
 * 输出最富裕的小家庭的财富和
 */
public class 最富裕的小家庭 {
    public static void main(String[] args) {
        /**
         * 说明：下面这个注释掉的写法是不太正确的，只有像输入用例是顺序输入的，才可以，例如：
         * 5
         * 868 745 172 567 764
         * 1 2
         * 2 3
         * 3 4
         * 4 5
         *
         * 这种看第一列可以看出，是按照从小到大输入的，可以保证孙子辈的不会加到爷爷辈
         *
         *
         * 如果是这种用例：
         * 5
         * 100 200 300 400 500
         * 3 4
         * 3 5
         * 1 3
         * 1 2
         *
         * 先给3这个父节点加上了子节点的值，这样3节点值就变了，就会将增加了的3节点的值给1节点，导致了孙子辈的值给了爷爷辈，造成错误，所以要多写一个数组分别保存父子的值才可以
        Scanner scanner = new Scanner(System.in);
        //获取成员总数
        int count = scanner.nextInt();

//        int max = 0;

        //每个成员财富值数组
        int[] everyCost = new int[count+1];
        for (int i=1;i<=count;i++) {
            everyCost[i]=scanner.nextInt();
        }

        //这里是为了获取接下来输入的N-1个两个空格分隔的整数
        for(int i =1;i<count;i++){
            int faNum = scanner.nextInt();
            int sonNum = scanner.nextInt();
            //将父子的数赋值给父
            everyCost[faNum] = everyCost[faNum]+everyCost[sonNum];

            //如果这样求最大值，如果只有一个节点，是无法进入这个for循环的，所以也就取不到那一个节点的价值，最后只能输出0，例子：
            //1
            //100
            //max = Math.max(everyCost[i],max);
        }

        System.out.println(Arrays.stream(everyCost).max().orElse(0));
         */

        Scanner scanner = new Scanner(System.in);
        //获取成员总数
        int count = scanner.nextInt();

        //每个成员财富值数组
        int[] everyCost = new int[count+1];
        //用于相加的财富值数组
        int[] addCost = new int[count+1];
        for (int i=1;i<=count;i++) {
            everyCost[i]=scanner.nextInt();
            addCost[i]=everyCost[i];
        }

        //这里是为了获取接下来输入的N-1个两个空格分隔的整数
        for(int i =1;i<count;i++){
            int faNum = scanner.nextInt();
            int sonNum = scanner.nextInt();
            //将父子的数赋值给父
            addCost[faNum] = addCost[faNum]+everyCost[sonNum];

        }

        System.out.println(Arrays.stream(addCost).max().orElse(0));
    }
}

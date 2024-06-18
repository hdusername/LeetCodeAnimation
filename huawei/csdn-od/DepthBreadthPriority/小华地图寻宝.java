import java.util.HashSet;
import java.util.Scanner;

/**
 * 小华按照地图去寻宝，地图上被划分成 m 行和 n 列的方格，横纵坐标范围分别是 [0, n-1] 和 [0, m-1]。
 * 在横坐标和纵坐标的数位之和不大于 k 的方格中存在黄金（每个方格中仅存在一克黄金），但横坐标和纵坐标之和大于 k 的方格存在危险不可进入。小华从入口 (0,0) 进入，任何时候只能向左，右，上，下四个方向移动一格。
 * 请问小华最多能获得多少克黄金？
 *
 * 输入描述：
 * 坐标取值范围如下：
 * 0 ≤ m ≤ 50
 * 0 ≤ n ≤ 50
 * k 的取值范围如下：
 * 0 ≤ k ≤ 100
 * 输入中包含3个字数，分别是m, n, k
 *
 * 输出描述：
 * 输出小华最多能获得多少克黄金
 */
public class 小华地图寻宝 {


    static int m;
    static int n;
    static int k;

    // 记录题解
    static int ans = 0;

    // 记录已访问过的位置，避免重复统计
    static HashSet<Integer> visited = new HashSet<>();

    // 上下左右偏移量
    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // 数位和数组
    static int[] digitSums;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        k = scanner.nextInt();

        //深度优先算法
        deepFirst();
        //breadthFirst();
    }

    private static void deepFirst() {

        //初始化数位数组，角标表示数字，值表示数位之和，11的数位之和就是1+1=2
        initDigitalArr(Math.max(m,n));

        getGold(0,0);

        System.out.println(ans);


    }

    private static void getGold(int x, int y) {

        //x*n+y的方式将坐标系中的xy值转变为一维参数的值，其实这种方式可能会存在冲突的情况，但是这里还是这样写了
        if(x<0 || x>=m || y<0 || y>=n || visited.contains(x*n+y) || digitSums[x]+digitSums[y]>k ){
            //不符合条件继续走下一条路
            return;
        }

        visited.add(x*n+y);
        ans++;

        for(int[] offset : offsets){
            //x轴移动
            int xOffset = x + offset[0];
            //y轴移动
            int yOffset = y + offset[1];

            getGold(xOffset,yOffset);
        }
    }

    private static void initDigitalArr(int number) {
        digitSums = new int[number];
        for (int i = 0;i<number;i++) {
            int tmpNum = i;
            while (tmpNum>0) {
                digitSums[i] += tmpNum % 10;
                tmpNum  /= 10;
            }
        }
    }


}

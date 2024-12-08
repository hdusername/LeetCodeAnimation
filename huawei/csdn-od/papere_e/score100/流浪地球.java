import java.lang.reflect.Array;
import java.util.*;

/**
 * 流浪地球计划在赤道上均匀部署了 N 个转向发动机，按位置顺序编号为 0 ~ N
 * 1.流浪地球计划在赤道上均匀部署了 N 个转向发动机，按位置顺序编号为 0 ~ N流浪地球计划在赤道上均匀部署了 N 个转向发动机，按位置顺序编号为 0 ~ N
 * 2.发动机启动的方式分为“手动启动”和“关联启动”两种方式
 * 3.如果在时刻 1 一个发动机被启动，下一个时刻 2 与之相邻的两个发动机就会被“关联启动”
 * 4.如果准备启动某个发动机时，它已经被启动了，则什么都不用做
 * 5.发动机 0 与发动机 N-1 是相邻的
 *
 * 地球联合政府准备挑选某些发动机在某些时刻进行“手动启动”。当然最终所有的发动机都会被启动。哪些发动机最晚被启动呢？
 *
 * 输入描述:
 * 第一行两个数字 N 和 E，中间有空格
 *  N 代表部署发动机的总个数，1 < N ≤ 1000
 *  E 代表计划手动启动的发动机总个数，1 ≤ E ≤ 1000，E ≤ N
 *
 * 接下来共 E 行，每行都是两个数字 T 和 P，中间有空格
 *  T 代表发动机的手动启动时刻，0 ≤ T ≤ N
 *  P 代表次发动机的位置编号，0 ≤ P < N
 *
 * 输出描述：
 * 第一行一个数字 N， 以回车结束
 *  N 代表最后被启动的发动机个数
 * 第二行 N 个数字，中间有空格，以回车结束
 *  每个数字代表发动机的位置编号，从小到大排序
 */
public class 流浪地球 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        //int[] mechInput1s = Arrays.asList(scanner.nextLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();

        int mechNum = scanner.nextInt();

        int mechHandNum = scanner.nextInt();

        //存放每个发动机什么时间启动的数组
        int[] mechTime = new int[mechNum];
        //给数组中启动时间值存为最大，方便后续便利取符合条件的启动时刻值
        //这里取2001的原因是启动时刻最大值为1000，发动机最多为1000，所以最后一个发动机启动时刻肯定到不了2001，这里就是为了写入一个最大值，方便后续取真正的启动时刻
        Arrays.fill(mechTime,2001);

        //存放每个手动启动发动机时刻的数组
        //String[] mechHandInput2s = new String[mechHandNum];

        for(int i=0;i<mechHandNum;i++){
            //把手动启动的机器放入数组中
            //int[] s = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int t = scanner.nextInt();
            int p = scanner.nextInt();

            mechTime[p] = t;
        }

        for(int i=0;i<mechNum;i++){

            for(int j=0;j<mechNum;j++){
                //从i到j内圈间隔
                int innRange = Math.abs(i - j);
                //从i到j外圈间隔
                int outRange = mechNum-innRange;
                //取内外圈最小启动时刻
                int minRange = Math.min(innRange, outRange);

                //因为当前判断的机器有可能是手动启动的，所以要比较下当前启动时刻和内外圈最小值的最小值
                mechTime[j] = Math.min(mechTime[i]+minRange, mechTime[j]);

            }
        }
        int lastMechTime = 0;
        int lastNum = 0;
        StringJoiner stringJoiner = new StringJoiner(" ");

        for(int i=0;i<mechNum;i++){
            if(lastMechTime>mechTime[i]){

            } else if (lastMechTime == mechTime[i]) {
                stringJoiner.add(i+"");
                lastNum++;
            }else {
                lastMechTime = mechTime[i];
                stringJoiner=new StringJoiner(" ");
                stringJoiner.add(i + "");
                lastNum=1;
            }
        }

        System.out.println(lastNum);
        System.out.println(stringJoiner);
    }
}
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//import java.util.StringJoiner;
//
//public class 流浪地球 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int n = sc.nextInt(); // 发动机的总个数
//        int e = sc.nextInt(); // 计划手动启动的发动机总个数
//
//        int[] launches = new int[n]; // 记录每个发动机的最终启动时刻
//        Arrays.fill(launches, 2001); // 初始化为极大值，方便后面取最早启动时刻
//
//        for (int i = 0; i < e; i++) {
//            int t = sc.nextInt(); // 发动机的手动启动时刻
//            int p = sc.nextInt(); // 发动机的位置编号
//
//            launches[p] = t; // p号发动机在t时刻手动启动
//        }
//
//        // 从编号 i 的发动机手动启动后, 关联启动到编号 j
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                // 内关联距离
//                int innerDis = Math.abs(i - j);
//                // 外关联距离
//                int outerDis = n - innerDis;
//                // 最短关联距离
//                int dis = Math.min(innerDis, outerDis);
//                launches[j] = Math.min(launches[j], launches[i] + dis);
//            }
//        }
//
//        int maxT = 0; // 最晚启动时刻
//        ArrayList<Integer> last = new ArrayList<>(); // 最晚启动的发动机编号集合
//
//        for (int p = 0; p < launches.length; p++) {
//            int t = launches[p]; // 当前发动机启动时刻
//
//            if (t < maxT) continue; // 不是最晚启动的发动机
//
//            if (t > maxT) { // 更晚启动的时刻
//                maxT = t;
//                last.clear();
//            }
//
//            last.add(p); // 记录该发动机编号
//        }
//
//        StringJoiner sj = new StringJoiner(" ");
//        last.stream().sorted((a, b) -> a - b).forEach(p -> sj.add(p + ""));
//
//        System.out.println(last.size());
//        System.out.println(sj);
//    }
//}
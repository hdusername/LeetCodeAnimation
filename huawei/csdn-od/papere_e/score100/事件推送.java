import java.util.Arrays;
import java.util.Scanner;
/**
 * 同一个数轴X上有两个点的集合A={A1,A2,..,Am}和B={B1,B2,…,Bn},Ai和Bj均为正整数，A、B已经按照从小到大排好序，
 * A、B均不为空，给定一个距离R(正整数),列出同时满足如下条件的所有(Ai,Bj)数对：
 * 1.Ai<= Bj
 * 2.Ai,Bj之间的距离小于等于R
 * 3.在满足1,2的情况下，每个Ai只需输出距离最近的Bj
 * 4.输出结果按Ai从小到大的顺序排序
 *
 * 输入描述
 * 第一行三个正整数m,n,R
 * 第二行m个正整数，表示集合A
 * 第三行n个正整数，表示集合B
 *
 * 输入限制：
 * 1<=R<=100000,1<=n,m<=100000,1<=Ai,Bj<=1000000000
 *
 * 输出描述：
 * 每组数对输出一行Ai和Bj,以空格隔开
 *
 * 用例输入：
 * 4 5 5
 * 1 5 5 10
 * 1 3 8 8 20
 *
 * 输出
 * 1 1
 * 5 8
 * 5 8
 */
public class 事件推送 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        int r = sc.nextInt();

        sc.nextLine();

        int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int aIdx = 0;
        int bIdx = 0;

        while (aIdx<m && bIdx<n){
            if(a[aIdx]<=b[bIdx]){
                if (b[bIdx] - a[aIdx] <= r){
                    System.out.println(a[aIdx] + " " + b[bIdx]);

                }
                aIdx++;
            }else {
                bIdx++;
            }

        }
    }
}
//标准答案
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int m = sc.nextInt();
//        int n = sc.nextInt();
//        int r = sc.nextInt();
//
//        sc.nextLine();
//
//        int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        int[] b = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//
//        int i = 0;
//        int j = 0;
//
//        while (i < m && j < n) {
//            if (a[i] <= b[j]) {
//                if (b[j] - a[i] <= r) System.out.println(a[i] + " " + b[j]);
//                i++;
//            } else {
//                j++;
//            }
//        }
//    }
//}
import java.util.*;
/**
 * 小明有 n 块木板，第 i 块木板长度为 ai
 * 1 ≤ i ≤ n
 * 小明买了一块长度为 m 的木料，这块木料可以切割成任意块，拼接到已有的木板上，用来加长木板。
 * 小明想让最短的木板尽量长。请问小明加长木板后，最短木板的长度可以为多少？
 *
 * 输入描述：
 * 输入的第一行包含两个正整数 n，m
 *  n 表示木板数，1 ≤ n ≤ 10^3
 *  m 表示木板长度，1 ≤ m ≤ 10^6
 * 输入的第二行包含 n 个正整数 a1, a2, …, an
 *  1 ≤ ai ≤ 10^6
 *
 * 输出描述：
 * 输出的唯一一行包含一个正整数，表示加长木板后，最短木板的长度最大可以为多少？
 *
 * 输入：
 * 5 3
 * 4 5 3 5 5
 *
 * 输出：
 * 5
 *
 * 解释：
 * 给第1块木板长度增加1，给第3块木板长度增加2后，
 *  这5块木板长度变为[5,5,5,5,5]，最短的木板的长度最大为5。
 */
public class 最短木板的长度最大可以为多少 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 小顶堆
        for (int i = 0; i < n; i++) {
            pq.add(sc.nextInt());
        }

        for (int i = 0; i < m; i++) { // 循环m次，每次截取长度1的木板
            pq.offer(pq.poll() + 1); // 每次取出最短木板+1后再加入优先队列
        }

        System.out.println(pq.peek()); // 打印最短木板
    }

}

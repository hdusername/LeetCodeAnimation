import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个二叉树，每个节点上站一个人，节点数字表示父节点到该节点传递悄悄话需要花费的时间。
 * 初始时，根节点所在位置的人有一个悄悄话想要传递给其他人，求二叉树所有节点上的人都接收到悄悄话花费的时间。
 *
 * 输入描述：
 * 给定二叉树
 * 0 9 20 -1 -1 15 7 -1 -1 -1 -1 3 2
 * 注：-1表示空节点
 *
 * 输出描述：
 * 返回所有节点都接收到悄悄话花费的时间
 * 38
 */
public class 悄悄话 {
    public static void main(String[] args) {
        int[] trees = Arrays.stream(new Scanner(System.in).nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int maxAns = 0;
        for(int i =0;i<trees.length;i++){
            //计算当前节点左右节点的索引
            int leftIdx = 2*i+1;
            int rigthIdx = 2*i+2;
            if(trees[i]!=-1){
                if(leftIdx<trees.length && leftIdx != -1){
                    trees[leftIdx]+=trees[i];
                    maxAns = Math.max(maxAns, trees[leftIdx]);
                }
                if(rigthIdx<trees.length && rigthIdx != -1){
                    trees[rigthIdx]+=trees[i];
                    maxAns = Math.max(maxAns, trees[rigthIdx]);
                }
            }
        }
        System.out.println(maxAns);
    }

//    public static void anotherMethod(){
//
//        Scanner sc = new Scanner(System.in);
//
//        int[] times = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//
//        // 记录题解
//        int ans = 0;
//
//        // 根节点的索引是0
//        LinkedList<Integer> queue = new LinkedList<>();
//        queue.addLast(0);
//
//        while (queue.size() > 0) {
//            int fa = queue.removeFirst(); // 父节点索引
//
//            int ch1 = 2 * fa + 1; // 左子节点索引
//            int ch2 = 2 * fa + 2; // 右子节点索引
//
//            // fa是否存在左子节点
//            boolean ch1_exist = ch1 < times.length && times[ch1] != -1;
//            // fa是否存在右子节点
//            boolean ch2_exist = ch2 < times.length && times[ch2] != -1;
//
//            // fa如果存在左子节点
//            if (ch1_exist) {
//                times[ch1] += times[fa];
//                queue.addLast(ch1);
//            }
//
//            // fa如果存在右子节点
//            if (ch2_exist) {
//                times[ch2] += times[fa];
//                queue.addLast(ch2);
//            }
//
//            // fa是叶子节点
//            if (!ch1_exist && !ch2_exist) {
//                // 保留叶子节点中最大时延
//                ans = Math.max(ans, times[fa]);
//            }
//        }
//
//        System.out.println(ans);
//
//
//    }

}

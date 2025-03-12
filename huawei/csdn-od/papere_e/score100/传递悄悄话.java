import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 */
public class 传递悄悄话 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] trees = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int maxTime = 0;
        LinkedList<Integer> linkedList = new LinkedList<>();
        //将根节点放入集合中
        linkedList.add(0);
        while (!linkedList.isEmpty()){
            Integer index = linkedList.pop();
            int leftIndex = 2*index+1;
            int rightIndex = 2*index+2;

            //判断父节点的左右子节点是否存在
            boolean leftNodeExist = leftIndex<trees.length && trees[leftIndex] !=-1;
            boolean rightNodeExist = rightIndex<trees.length && trees[rightIndex] !=-1;

            if(leftNodeExist){
                linkedList.add(leftIndex);
                trees[leftIndex] += trees[index];
            }

            if(rightNodeExist){
                linkedList.add(rightIndex);
                trees[rightIndex] += trees[index];
            }
            //如果左右子节点都不存在，需要取出需要的传递时间并比较最小值
            if(!leftNodeExist && !rightNodeExist){
                maxTime = Math.max(maxTime, trees[index]);
            }
        }
        System.out.println(maxTime);
    }
}

//标准答案：
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Scanner;
//
//public class Main {
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//
//    int[] times = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//
//    // 记录题解
//    int ans = 0;
//
//    // 根节点的索引是0
//    LinkedList<Integer> queue = new LinkedList<>();
//    queue.addLast(0);
//
//    while (queue.size() > 0) {
//      int fa = queue.removeFirst(); // 父节点索引
//
//      int ch1 = 2 * fa + 1; // 左子节点索引
//      int ch2 = 2 * fa + 2; // 右子节点索引
//
//      // fa是否存在左子节点
//      boolean ch1_exist = ch1 < times.length && times[ch1] != -1;
//      // fa是否存在右子节点
//      boolean ch2_exist = ch2 < times.length && times[ch2] != -1;
//
//      // fa如果存在左子节点
//      if (ch1_exist) {
//        times[ch1] += times[fa];
//        queue.addLast(ch1);
//      }
//
//      // fa如果存在右子节点
//      if (ch2_exist) {
//        times[ch2] += times[fa];
//        queue.addLast(ch2);
//      }
//
//      // fa是叶子节点
//      if (!ch1_exist && !ch2_exist) {
//        // 保留叶子节点中最大时延
//        ans = Math.max(ans, times[fa]);
//      }
//    }
//
//    System.out.println(ans);
//  }
//}

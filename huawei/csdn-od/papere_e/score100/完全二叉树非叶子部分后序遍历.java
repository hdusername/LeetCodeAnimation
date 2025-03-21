import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 给定一个以顺序储存结构存储整数值的完全二叉树序列（最多1000个整数），请找出此完全二叉树的所有非叶子节点部分，然后采用后序遍历方式将此部分树（不包含叶子）输出。
 * 1、只有一个节点的树，此节点认定为根节点（非叶子）。
 * 2、此完全二叉树并非满二叉树，可能存在倒数第二层出现叶子或者无右叶子的情况
 * 其他说明：二叉树的后序遍历是基于根来说的，遍历顺序为：左-右-根
 *
 * 输入描述：
 * 一个通过空格分割的整数序列字符串
 *
 * 输出描述：
 * 非叶子部分树结构
 *
 * 备注：
 * 输出数字以空格分隔
 *
 * 用例输入：
 * 1 2 3 4 5 6 7
 *
 * 输出：
 * 2 3 1
 *
 * 解释：
 * 找到非叶子部分树结构，然后采用后序遍历输出。
 */
public class 完全二叉树非叶子部分后序遍历 {
    static int[] array;

    static StringJoiner stringJoiner = new StringJoiner(" ");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if(array.length==1){
            System.out.println(array[0]);
        }else {
            dfs(0);
            System.out.println(stringJoiner);
        }
    }
    private static void dfs(int idx){
        int lIdx = 2*idx+1;
        int rIdx = 2*idx+2;

        if(lIdx < array.length){
            //说明二叉树有左子节点
            dfs(lIdx);

            if(lIdx < array.length){
                //说明二叉树有右子节点
                dfs(rIdx);
            }
            stringJoiner.add(array[idx]+"");
        }
    }
}

//标准答案
//import java.util.Scanner;
//import java.util.StringJoiner;
//
//public class Main {
//    static String[] arr;
//    static StringJoiner non_leafs = new StringJoiner(" "); // 非叶子节点后序遍历序列
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        arr = sc.nextLine().split(" ");
//
//        if (arr.length == 1) {
//            // 只有一个节点时，直接输出
//            System.out.println(arr[0]);
//        } else {
//            // 按照后序遍历规则，递归完全二叉树
//            dfs(0);
//            System.out.println(non_leafs);
//        }
//    }
//
//    public static void dfs(int root) {
//        int left = root * 2 + 1;
//        int right = root * 2 + 2;
//
//        if (arr.length > left) {
//            // 左：如果左孩子不为空，则说明第i个元素不是叶子，因此继续递归其左孩子，即将左孩子当成新的根来递归
//            dfs(left);
//            if (arr.length > right) {
//                // 右：如果右孩子也不为空，则根据后序遍历原则，还要对右孩子进行递归
//                dfs(right);
//            }
//            // 根
//            non_leafs.add(arr[root]);
//        }
//    }
//}

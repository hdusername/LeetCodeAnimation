import java.util.*;

/**
 * 给定参数 n，从 1 到 n 会有 n 个整数：1,2,3,…,n，这 n 个数字共有 n! 种排列。
 * 按大小顺序升序列出所有排列的情况，并一一标记，当 n = 3 时，所有排列如下：
 * “123”
 * “132”
 * “213”
 * “231”
 * “312”
 * “321”
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 输入描述：
 * 输入两行：
 * 第一行为 n，给定 n 的范围是 [1，9]
 * 第二行为 k，给定 k 的范围是 [1，n!]
 *
 * 输出描述：
 * 输出排在第 k 位置的数字。
 *
 */
public class 第k个排列 {

    static List<List<Integer>> ansList = new ArrayList<>();
    static LinkedList<Integer> linkList = new LinkedList<>();

    static boolean used[];
    /**
     * 使用回溯算法
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        used = new boolean[n+1];

        //从1开始寻找，找到n，
        getArrange(n);

        for (Integer integer : ansList.get(k - 1)) {
            System.out.print(integer);
        }
    }

    /**
     * 功能：回溯法得到k个字符的组合数字，这个方法并不会得到全排列数字，组合数字的含义是比如给出1，2，3，组合中只包含两个元素组合后是[1,2],[1,3],[2,3]
     * 而全排列得到的组合是[1,2],[1,3],[2,1],[2,3],[3,1],[3,3]
     * @param index 从这个数字开始
     * @param n 从这个数字结束
     * @param k 每个全排列结果需要达到的长度
     */
//    private static void getRank(int index, int n) {
//
//        if(linkList.size()==n){
//            //当达到长度时，放到结果集中,因为linkList还要在后续使用，进行回溯，所以这里新创建一个集合加入进去
//            ansList.add(new ArrayList<>(linkList));
//            //linkList.clear();
//            return;
//        }
//        for(int i=index; i<=n-(n-linkList.size())+1; i++){
//            linkList.add(i);
//            getRank(i+1, n);
//            linkList.removeLast();
//        }
//    }

    /**
     * 功能：获取全排列的方法
     * @param n
     */
    private static void getArrange(int n){
        if (linkList.size() == n){
            ansList.add(new ArrayList<>(linkList));
            return;
        }
        for (int i = 1; i <= n; i++){
            if (used[i]){
                continue;
            }
            used[i] = true;
            linkList.add(i);
            getArrange(n);
            linkList.removeLast();
            used[i] = false;
        }
    }
}

//标准答案
//import java.util.*;
//
//public class Main {
//    static int count = 0;
//    static String ans = "";
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int n = sc.nextInt();
//        int k = sc.nextInt();
//
//        dfs(n, k, new boolean[n + 1], new LinkedList<>());
//
//        System.out.println(ans);
//    }
//
//    public static boolean dfs(int n, int k, boolean[] used, LinkedList<String> path) {
//        if (path.size() == n) {
//            if (++count == k) {
//                ans = String.join("", path);
//            }
//
//            return count == k;
//        }
//
//        for (int i = 1; i <= n; i++) {
//            if (used[i]) continue;
//
//            used[i] = true;
//            path.addLast(i + "");
//
//            if (dfs(n, k, used, path)) {
//                return true;
//            }
//
//            path.removeLast();
//            used[i] = false;
//        }
//
//        return false;
//    }
//}

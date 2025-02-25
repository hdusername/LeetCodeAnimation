import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 通常使用多行的节点、父节点表示一棵树，比如
 * 西安 陕西
 * 陕西 中国
 * 江西 中国
 * 中国 亚洲
 * 泰国 亚洲
 *
 * 输入一个节点之后，请打印出来树中他的所有下层节点
 *
 * 输入描述：
 * 第一行输入行数，下面是多行数据，每行以空格区分节点和父节点
 * 接着是查询节点
 *
 * 输出描述：
 * 输出查询节点的所有下层节点。以字典序排序
 *
 * 备注：
 * 树中的节点是唯一的，不会出现两个节点，是同一个名字
 *
 * 用例输入：
 * 5
 * b a
 * c a
 * d c
 * e c
 * f d
 * c
 *
 * 输出：
 * d
 * e
 * f
 *
 */
public class 树状结构查询 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();

        for(int i=0; i<n; i++){
            String[] array = scanner.nextLine().split(" ");
            String ch = array[0];
            String fa = array[1];
            hashMap.putIfAbsent(fa, new ArrayList<>());
            ArrayList<String> fa_array = hashMap.get(fa);
            fa_array.add(ch);
            hashMap.put(fa, fa_array);
        }

        String findStr = scanner.nextLine();

        ArrayList<String> ansList = new ArrayList<>();
        dfs(hashMap, findStr, ansList);
        ansList.sort((a, b)->a.compareTo(b));
        ansList.forEach(a->System.out.println(a));
    }

    static private void dfs(HashMap<String, ArrayList<String>> hashMap, String findStr, ArrayList<String> ansList){
        if(hashMap.containsKey(findStr)){
            for (String s : hashMap.get(findStr)) {
                dfs(hashMap, s, ansList);
                ansList.add(s);
            }

        }
    }

    //标准答案
    //import java.util.*;
    //
    //public class Main {
    //    public static void main(String[] args) {
    //        Scanner sc = new Scanner(System.in);
    //
    //        int n = sc.nextInt();
    //
    //        // 构建树
    //        HashMap<String, HashSet<String>> tree = new HashMap<>();
    //        for (int i = 0; i < n; i++) {
    //            String ch = sc.next();
    //            String fa = sc.next();
    //
    //            tree.putIfAbsent(fa, new HashSet<>());
    //            tree.get(fa).add(ch);
    //        }
    //
    //        // 目标节点
    //        String target = sc.next();
    //
    //        System.out.println(getResult(tree, target));
    //    }
    //
    //    public static String getResult(HashMap<String, HashSet<String>> tree, String target) {
    //        // 如果目标节点没有子节点，则输出空
    //        if (!tree.containsKey(target)) {
    //            return "";
    //        }
    //
    //        // 记录目标节点下的所有子节点
    //        ArrayList<String> ans = new ArrayList<>();
    //
    //        // bfs队列
    //        LinkedList<String> queue = new LinkedList<>(tree.get(target));
    //
    //        // bfs广搜
    //        while (!queue.isEmpty()) {
    //            String node = queue.removeFirst();
    //            ans.add(node);
    //
    //            if (tree.containsKey(node)) queue.addAll(tree.get(node));
    //        }
    //
    //        StringJoiner sj = new StringJoiner("\n");
    //        // 目标节点的所有子节点以字典序排序
    //        ans.stream().sorted().forEach(sj::add);
    //        return sj.toString();
    //    }
    //}
}

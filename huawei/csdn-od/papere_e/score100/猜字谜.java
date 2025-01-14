import java.util.*;

/**
 * 小王设计了一个简单的猜字谜游戏，游戏的谜面是一个错误的单词，比如 nesw，玩家需要猜出谜底库中正确的单词。猜中的要求如下：
 * 对于某个谜面和谜底单词，满足下面任一条件都表示猜中：
 * 变换顺序以后一样的，比如通过变换 w 和 e 的顺序，“nwes” 跟 “news” 是可以完全对应的；
 * 字母去重以后是一样的，比如 “woood” 和 “wood” 是一样的，它们去重后都是 “wod”
 *
 * 请你写一个程序帮忙在谜底库中找到正确的谜底。
 * 谜面是多个单词，都需要找到对应的谜底，如果找不到的话，返回 “not found”
 *
 * 输入描述：
 * 谜面单词列表，以 “,” 分隔
 * 谜底库单词列表，以 "," 分隔
 *
 * 输出描述：
 * 匹配到的正确单词列表，以 "," 分隔
 * 如果找不到，返回 "not found"
 *
 * 备注：
 * 单词的数量 N 的范围：0 < N < 1000
 * 词汇表的数量 M 的范围：0 < M < 1000
 * 单词的长度 P 的范围：0 < P < 20
 * 输入的字符只有小写英文字母，没有其他字符
 */
public class 猜字谜 {
    //todo:这种方式对于输入为：
    //wod
    //wood,dow,wod
    //是不行的，因为用map存储会导致只保存了最后一个wod为key，答案也只有输出了wod，用下面题目解析给出的解法，会输出wood,dow,wod
    //因为这三个谜底都符合条件
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String[] queryStrs = scanner.nextLine().split(",");
//        String[] mapStrs = scanner.nextLine().split(",");
//
//        HashMap<String, String> map = new HashMap<>();
//        for(int i=0;i<mapStrs.length;i++){
//            map.put(transStr(mapStrs[i]),mapStrs[i]);
//        }
//
//        StringJoiner stringJoiner = new StringJoiner(",");
//        for(int i=0; i<queryStrs.length; i++){
//            String transStr = transStr(queryStrs[i]);
//            if(map.keySet().contains(transStr)){
//                stringJoiner.add(map.get(transStr));
//            }else {
//                stringJoiner.add("not found");
//            }
//        }
//        System.out.println(stringJoiner);
//    }
//    public static String transStr(String s){
//        TreeSet<Character> treeSet = new TreeSet<>();
//        for(char c : s.toCharArray()){
//            treeSet.add(c);
//        }
//        return treeSet.toString();
//    }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            String[] issues = sc.nextLine().split(",");
            String[] answers = sc.nextLine().split(",");

            ArrayList<String> ans = new ArrayList<>();

            for (String issue : issues) {
                String s1 = getSortedAndDistinctStr(issue);
                boolean isNotFind = true;

                for (String answer : answers) {
                    String s2 = getSortedAndDistinctStr(answer);

                    if (s1.equals(s2)) {
                        ans.add(answer);
                        isNotFind = false;
                    }
                }

                if (isNotFind) {
                    ans.add("not found");
                }
            }

            System.out.println(String.join(",", ans));
        }

        public static String getSortedAndDistinctStr(String s) {
            TreeSet<Character> set = new TreeSet<>();
            for (int i = 0; i < s.length(); i++) set.add(s.charAt(i));
            return set.toString();
        }

}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * C 语言有一个库函数： char *strstr(const char *haystack, const char *needle) ，实现在字符串 haystack 中查找第一次出现字符串 needle 的位置，
 * 如果未找到则返回 null。
 * 现要求实现一个strstr的增强函数，可以使用带可选段的字符串来模糊查询，与strstr一样返回首次查找到的字符串位置。
 * 可选段使用“[]”标识，表示该位置是可选段中任意一个字符即可满足匹配条件。比如“a[bc]”表示可以匹配“ab”或“ac”。
 * 注意目标字符串中可选段可能出现多次。
 *
 * 输入描述：
 * 与strstr函数一样，输入参数是两个字符串指针，分别是源字符串和目标字符串。
 *
 * 输出描述：
 * 与strstr函数不同，返回的是源字符串中，匹配子字符串相对于源字符串地址的偏移（从0开始算），如果没有匹配返回-1。
 * 补充说明：源字符串中必定不包含‘[]’；目标字符串中‘[]’必定成对出现，且不会出现嵌套。
 * 输入的字符串长度在[1,100]之间。
 */
public class 增强的strstr {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sourceStr = scanner.nextLine();
        String targetStr = scanner.nextLine();

        boolean isOpen = false;
//        String targetStr_fix="";
//        String targetStr_change="";
        List<HashSet<String>> list = new ArrayList<>();
        HashSet hashSet = new HashSet();

        for(int i=0; i<targetStr.length(); i++){
            char c = targetStr.charAt(i);
            if(c=='['){
                isOpen=true;
                hashSet = new HashSet();
            } else if(c==']'){
                isOpen=false;
                list.add(hashSet);
            }else if(isOpen){
                hashSet.add(c);
            }else {
                hashSet = new HashSet();
                hashSet.add(c);
                list.add(hashSet);
            }
        }

        //一定要有这个等于号，不然如果sorceStr.length为1，list.size为1，就不会执行下去了
        //A
        //[ABCD]
        out:for(int i=0;i<=sourceStr.length()-list.size();i++){
            int j=0;
            for(;j<list.size();j++){
                //滑动窗口方式向后匹配
                if(!list.get(j).contains(sourceStr.charAt(i+j))){
                    continue out;
                }
            }
//            if(j==list.size()-1){
//                System.out.println(i);
//            }
            System.out.println(i);
            return;
        }
        System.out.println("-1");
    }



//        public static void main(String[] args) {
//            String regex = "\\d+";  // 匹配一个或多个数字
//            String input = "Price: 45, Discount: 10, Total: 35";
//
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(input);
//
//            while (matcher.find()) {
//                System.out.println("Found: " + matcher.group());  // 输出: 45, 10, 35
//            }
//        }


}
//标准答案
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        String src = sc.nextLine();
//        String tar = sc.nextLine();
//
//        System.out.println(getResult(src, tar));
//    }
//
//    public static int getResult(String src, String tar) {
//        // 将tar字符串转化为levels多层结构，转化逻辑为：tar字符串中，每个[]包含的所有字符作为一层，未被[]包含的单个字符作为一层
//        ArrayList<HashSet<Character>> levels = new ArrayList<>();
//        // level用于记录[]中的字符
//        HashSet<Character> level = new HashSet<>();
//
//        boolean isOpen = false;
//        for (int i = 0; i < tar.length(); i++) {
//            char c = tar.charAt(i);
//
//            if (c == '[') {
//                isOpen = true;
//            } else if (c == ']') {
//                isOpen = false;
//                levels.add(level);
//                level = new HashSet<>();
//            } else if (isOpen) {
//                level.add(c);
//            } else {
//                HashSet<Character> tmp = new HashSet<>();
//                tmp.add(c);
//                levels.add(tmp);
//            }
//        }
//
//        return indexOf(src, levels);
//    }
//
//    public static int indexOf(String src, ArrayList<HashSet<Character>> levels) {
//        // 滑动匹配levels.length长度的子串
//        for (int i = 0; i <= src.length() - levels.size(); i++) {
//            int j = 0;
//            for (; j < levels.size(); j++) {
//                if (!levels.get(j).contains(src.charAt(i + j))) {
//                    break;
//                }
//            }
//
//            if (j == levels.size()) {
//                return i;
//            }
//        }
//
//        return -1;
//    }
//}
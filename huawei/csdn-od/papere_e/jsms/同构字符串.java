import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 *
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 *
 *
 *
 * 示例 1:
 *
 * 输入：s = "egg", t = "add"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "foo", t = "bar"
 * 输出：false
 * 示例 3：
 *
 * 输入：s = "paper", t = "title"
 * 输出：true
 */
public class 同构字符串 {

    public boolean isIsomorphic1(String s, String t) {

        //这个解法不行，如用例s="bbbaaaba"  t="aaabbbba"
        //题意是说如果s中的a对应t中的b，s中的b对应t中的a,那么对应过去应该是aaabbbab
//        HashSet<Character> sSet = new HashSet<>();
//        for (char c : s.toCharArray()) {
//            sSet.add(c);
//        }
//
//        HashSet<Character> tSet = new HashSet<>();
//        for (char c : t.toCharArray()) {
//            tSet.add(c);
//        }
//
//        if(tSet.size()==sSet.size()){
//            return true;
//        }else {
//            return false;
//        }
        return false;

    }


    public boolean isIsomorphic(String s, String t) {



        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }


}

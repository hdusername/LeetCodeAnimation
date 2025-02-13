import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 现有一字符串仅由 ‘(‘，’)’，'{‘，’}’，'[‘，’]’六种括号组成。若字符串满足以下条件之一，则为无效字符串：
 * ①任一类型的左右括号数量不相等；
 * ②存在未按正确顺序（先左后右）闭合的括号。
 * 输出括号的最大嵌套深度，若字符串无效则输出0。
 * 0≤字符串长度≤100000
 *
 * 输入描述：
 * 一个只包括 ‘(‘，’)’，'{‘，’}’，'[‘，’]’的字符串
 *
 * 输出描述：
 * 一个整数，最大的括号深度
 *
 * 用例输入：
 * []
 *
 * 输出：
 * 1
 *
 * 用例输入：
 * ([]{()})
 *
 * 输出：
 * 3
 */
public class 最大括号深度 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();

        LinkedList<Character> linkedList = new LinkedList<>();

        HashMap<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        //其实可以不定义这个变量，直接使用链表长度即可
        int ans = 0;
        int maxans = 0;

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(linkedList.size()>0 && linkedList.peekFirst()==map.get(c)){
                linkedList.pollFirst();
                ans--;
            }else {
                linkedList.addFirst(c);
                ans++;
                maxans = Math.max(maxans, ans);
            }
        }
        if(linkedList.size()==0){
            System.out.println(maxans);
        }else {
            System.out.println(0);
        }

    }
}

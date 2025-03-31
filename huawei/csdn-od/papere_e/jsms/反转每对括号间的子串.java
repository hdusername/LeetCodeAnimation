import java.util.Deque;
import java.util.LinkedList;

/**
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 *
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 *
 * 注意，您的结果中 不应 包含任何括号。
 *
 * 本题要求按照从括号内到外的顺序进行处理。如字符串 (u(love)i)，首先处理内层括号，变为 (uevoli)，然后处理外层括号，变为 iloveu。
 *
 * 对于括号序列相关的题目，通用的解法是使用递归或栈。本题中我们将使用栈解决。
 *
 * 我们从左到右遍历该字符串，使用字符串 str 记录当前层所遍历到的小写英文字母。对于当前遍历的字符：
 *
 * 如果是左括号，将 str 插入到栈中，并将 str 置为空，进入下一层；
 *
 * 如果是右括号，则说明遍历完了当前层，需要将 str 反转，返回给上一层。具体地，将栈顶字符串弹出，然后将反转后的 str 拼接到栈顶字符串末尾，将结果赋值给 str。
 *
 * 如果是小写英文字母，将其加到 str 末尾。
 *
 * 注意到我们仅在遇到右括号时才进行字符串处理，这样可以保证我们是按照从括号内到外的顺序处理字符串。
 */
public class 反转每对括号间的子串 {

    public String reverseParentheses(String s) {
//        LinkedList<String> strings = new LinkedList<>();
//        strings.removeFirst()
        LinkedList<String> stack = new LinkedList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.addLast(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.removeLast());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();

    }

}

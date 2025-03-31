import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 */
public class 基本计算器 {

    public static void main(String[] args) {
        int i = calculate("1 - (2 + 3)");
        System.out.println(i);
    }
    public static int calculate(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }

//错误
//    public int calculate1(String s) {
//        Stack<Integer> stack = new Stack<Integer>();// sign 代表正负
//        int sign = 1, res = 0;
//        int length = s.length();
//        for (int i = 0; i < length; i++) {
//            char ch = s.charAt(i);
//            if (Character.isDigit(ch)) {
//                int cur = ch - 'o';
//                while (i + 1 < length && Character.isDigit(s.charAt(i + 1)))
//                    cur = cur * 10 + s.charAt(++i) - '0';
//                res = res + sign * cur;
//            } else if (ch == '+') {
//                sign = 1;
//            } else if (ch == '-') {
//                sign = -1;
//            } else if (ch == '(') {
//                stack.push(res);
//                res = 0;
//                stack.push(sign);
//                sign = 1;
//            } else if (ch == ')') {
//                res = stack.pop() * res + stack.pop();
//            }
//        }
//        return res;
//    }
}

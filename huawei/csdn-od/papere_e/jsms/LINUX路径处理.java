import java.util.*;

/**
 *
 */
public class LINUX路径处理 {

    public static void main(String[] args) {
//        ArrayDeque<String> deque = new ArrayDeque();
//        deque.push("a");
//        deque.push("b");
//        deque.push("c");
//        System.out.println(deque.pop());
//        for(String dequ : deque){
//            System.out.println(dequ);
//        }

        System.out.println(Math.round(11.5));
        System.out.println(Math.round(-11.5));
double f = 3.14d;
//        if((7)&&(9)){
//
//        };
//        byte b1 = 1 , b2 = 2 , b3 , b6 ; final byte b4 = 4 , b5 = 6 ; b6 = b4 + b5 ; b3 = ( b1 + b2 ) ; System . out . println ( b3 + b6 )
//        gcd(7, 3);
//
//        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((pair1, pair2)->{
//
//                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
//
//        });
//
//        System.out.println(simplifyPath("/home//foo"));
//        System.out.println(simplifyPath("/a/./b/../../c/"));
    }
    public static long gcd(long a, long b) {
        long remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }

    public static String simplifyPath(String path) {
            Deque<String> stack = new LinkedList<>();
            for (String item : path.split("/")) {
                if (item.equals("..")) {
                    if (!stack.isEmpty()) stack.pop();
                } else if (!item.isEmpty() && !item.equals(".")) stack.push(item);
            }
            String res = "";
            for (String d : stack) res = "/" + d + res;
            return res.isEmpty() ? "/" : res;
        }

}

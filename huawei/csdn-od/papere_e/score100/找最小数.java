import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * 给一个正整数NUM1，计算出新正整数NUM2，NUM2为NUM1中移除N位数字后的结果，需要使得NUM2的值最小。
 *
 * 输入描述：
 * 1.输入的第一行为一个字符串由0-9字符组成，记录正整数NUM1，NUM1长度小于32。
 * 2.输入的第二行为需要移除的数字的个数，小于NUM1长度。
 *
 * 输出描述：
 * 输出一个数字字符串，记录最小值NUM2。
 */
public class 找最小数 {

    /**
     * 要想获得最小的num2值，需要构造一组递增数字才可以，只有这样才是最小的数字，如用例：
     * 3213747
     * 4
     * 走到while (k>0){逻辑之前，arrayDeque中保存了1，3，4，7，因为还没有删完4个数字，到这个逻辑这里只删除了三个数字，因为到这里已经给排成了递增的序列，
     * 所以依次删除后面最大的数字直到k=0得到的就是符合条件的num2
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num1 = scanner.nextLine();
        char[] num1s = num1.toCharArray();

        int k = Integer.parseInt(scanner.nextLine());

        //创建一个队列
        ArrayDeque<Integer> arrayDeque = new ArrayDeque();

        arrayDeque.add(Integer.parseInt(num1s[0]+""));

        for(int i=1; i<num1s.length; i++){
            //这个逻辑会将队列组成一个递增序列
            while (k>0 && !arrayDeque.isEmpty() && arrayDeque.getLast()>Integer.parseInt(num1s[i]+"")){
                arrayDeque.pollLast();
                k--;
            }
            arrayDeque.addLast(Integer.parseInt(num1s[i]+""));

        }

        while (k>0){
            arrayDeque.pollLast();
            k--;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (!arrayDeque.isEmpty()){
            Integer integer = arrayDeque.pollFirst();
            //删除num2前面的0，因为00123这种数字是不合法的
            if(!(stringBuffer.length()==0 && integer==0)){
                stringBuffer.append(integer);
            }

        }

        //如果都删除了，只剩下了空字符串，就输出0
        System.out.println(stringBuffer.length()==0?"0":stringBuffer);
    }

//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//
//            String num = sc.next();
//            int n = sc.nextInt();
//
//            System.out.println(solution(num, n));
//        }
//
//        public static String solution(String num, int k) {
//            if (k >= num.length()) {
//                return "0";
//            }
//
//            ArrayDeque<Character> stack = new ArrayDeque<>();
//            int remain = num.length() - k;
//
//            for (int i = 0; i < num.length(); i++) {
//                char c = num.charAt(i);
//
//                while (!stack.isEmpty() && k > 0 && stack.getLast() > c) {
//                    stack.removeLast();
//                    k--;
//                }
//
//                stack.addLast(c);
//            }
//
//            while (stack.size() > remain) {
//                stack.removeLast();
//            }
//
//            while (stack.getFirst() == '0' && stack.size() > 1) {
//                stack.removeFirst();
//            }
//
//            StringBuilder sb = new StringBuilder();
//            for (Character c : stack) {
//                sb.append(c);
//            }
//
//            return sb.toString();
//        }

}
